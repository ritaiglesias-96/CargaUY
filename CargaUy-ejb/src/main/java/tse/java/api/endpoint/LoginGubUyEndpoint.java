package tse.java.api.endpoint;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.core.io.ClassPathResource;
import tse.java.dto.CiudadanoJwtDTO;
import tse.java.dto.RubroDTO;
import tse.java.service.IGubUyService;
import tse.java.service.impl.GubUyService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequestScoped
@Path("/gubuy")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginGubUyEndpoint {

    @EJB
    IGubUyService gubUyService;
    private static final String FRONTOFFICE_URI = "https://carga-uy-13.web.elasticloud.uy/";
    private static final String pathToServiceAccountJson = "google-services.json";
    private static final String registrationToken = "e9Pqtl7GRJaiRPuL22Uw_m:APA91bFKZPhVcnkRAcMCXTaWTc7lwXl9U9TXrZyu-M-WzlJ7B8A-EdfuJRC9F1ncQIJLctgBBmVSou_Y0m4RBLLgdO0LapLF_VYwN4iPoKd4mfv2XEgOGQlh4M3nW1P9PsDJ7vqyXBiz";




    private static boolean isMobile;

    public boolean getisMobile() {
        return isMobile;
    }

    public void setIsMobile(boolean isMobile) {
        LoginGubUyEndpoint.isMobile = isMobile;
    }

    public void FirebaseConfig() throws IOException {
        //Configuracion para inicializar la app, el if controla que se haga una sola vez
        if(FirebaseApp.getApps().isEmpty()) {
            File googlepath = new ClassPathResource("firebase-config.json").getFile(); //
            FileInputStream serviceAccount =
                    new FileInputStream(googlepath);

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        }
    }

    @GET
    @Path("/auth")
    public Response gubUyAuth(@QueryParam("mobile") String mobile) throws URISyntaxException, IOException {
        if(mobile!=null){
            System.out.println("Linea 45 else para true de mobile");
             isMobile=true;
            System.out.println("mobile linea 48 : " + isMobile);
        }else{
            System.out.println("linea 47 para false de mobile");
            isMobile=false;
            System.out.println("mobile linea 52:" + isMobile);
        }
        System.out.println("mobile? " + isMobile);
        String url =  gubUyService.getAuthGubUy();
        URI uri=new URI(url);
        return Response.temporaryRedirect(uri).build();
    }

    @GET
    @Path("/tokens")
    public Response getToken(@QueryParam("code") String accessCode, @QueryParam("state") String state) throws URISyntaxException, FirebaseMessagingException, IOException {
        CiudadanoJwtDTO ciudadanoJwtDTO = gubUyService.loginGubUy(accessCode, state);
        System.out.println("mobile linea 70 :" + isMobile);
        if(isMobile){
            System.out.println("entro aca");
            FirebaseConfig();
            System.out.println("Entra al message builder");
            Message message = Message.builder()
                    .putData("title", "Redirect Notification")
                    .putData("body", "Click para volver a la aplicación")
                    .putData("jwt", ciudadanoJwtDTO.getJwt())
                    .putData("cedula", ciudadanoJwtDTO.getCedula())
                    .setToken(registrationToken)
                    .build();
            FirebaseMessaging.getInstance().send(message);
            return Response.status(Response.Status.OK).build();
        }else{
            System.out.println("Entra en el else linea 84 ");
            return Response.status(Response.Status.SEE_OTHER).location(new URI(FRONTOFFICE_URI + "?code=" + ciudadanoJwtDTO)).build();
        }

    }

   /* @GET
    @Path("/test-push")
    public Response getPush() throws FirebaseMessagingException, IOException {
        // This registration token comes from the client FCM SDKs.
// See documentation on defining a message payload.
        FirebaseConfig();
        Message message = Message.builder()
                .setNotification(
                        Notification.builder()
                                .setTitle("Redirect Notification")
                                .setBody("Click para volver a la aplicación")
                                .build())
                .setToken(registrationToken)
                .build();
        FirebaseMessaging.getInstance().send(message);
        return Response.status(Response.Status.OK).build();
    }*/
    @GET
    @Path("/jwt-control")
    public Response tokenControl(@QueryParam("jwt") String jwt){
        gubUyService.verificarJwt(jwt);
        return Response.status(Response.Status.OK).build();
    }

    @GET
    @Path("/logout")
    public Response logout(@QueryParam("token") String token) {
        gubUyService.logout(token);
        return Response.status(Response.Status.OK).build();
    }
}
//TODO Despues de redirigir a la pagina de auth gub uy, hay que ver si existe o no el usuario, si existe se redirige y si no se crea una instancia del usuario con un rol cualquiera que despues se puede cambiar, el tema es como pedir los datos del usuario a gub uy ya sea UserInfo Reuqest o ver si se pueden sacar del token