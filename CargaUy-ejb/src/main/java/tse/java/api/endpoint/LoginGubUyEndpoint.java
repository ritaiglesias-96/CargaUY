package tse.java.api.endpoint;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.core.io.ClassPathResource;
import tse.java.api.SessionBean;
import tse.java.dto.CiudadanoFrontDTO;
import tse.java.dto.CiudadanoJwtDTO;
import tse.java.service.IGubUyService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

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

    public LoginGubUyEndpoint() {
    }

    public LoginGubUyEndpoint(IGubUyService gubUyService) {
        this.gubUyService = gubUyService;
    }


    public boolean getisMobile() {
        return isMobile;
    }

    public void setMobile(boolean mobile) {
        isMobile = mobile;
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
        isMobile = mobile != null;
        String url =  gubUyService.getAuthGubUy();
        URI uri = new URI(url);
        System.out.println(uri);
        return Response.status(Response.Status.FOUND).location(uri).build();

    }

    @GET
    @Path("/tokens")
    public Response getToken(@QueryParam("code") String accessCode, @QueryParam("state") String state) throws Exception {
        CiudadanoJwtDTO ciudadanoJwtDTO = gubUyService.loginGubUy(accessCode, state);
        boolean mobile = getisMobile();
        if(mobile){
            FirebaseConfig();
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
            return Response.status(Response.Status.FOUND).location(new URI(FRONTOFFICE_URI + "?code=" + ciudadanoJwtDTO.getJwt())).build();
        }

    }

    @GET
    @Path("/test-push")
    public Response getPush() throws FirebaseMessagingException, IOException {
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
    }

    @POST
    @Path("/user-info")
    public Response userInfo(@QueryParam("jwtToken") String jwt){
        System.out.println("endpoint:" + jwt);
        gubUyService.verificarJwt(jwt);
        CiudadanoFrontDTO ciudadano = gubUyService.getCurrentUser(jwt);
        return Response.status(Response.Status.OK).entity(ciudadano).build();
    }
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