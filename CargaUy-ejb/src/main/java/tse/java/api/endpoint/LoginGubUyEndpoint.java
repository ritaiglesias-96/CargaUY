package tse.java.api.endpoint;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import tse.java.dto.RubroDTO;
import tse.java.service.IGubUyService;
import tse.java.service.impl.GubUyService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
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
    private static final String FRONTOFFICE_URI = "" ; //TODO URI DE FRONT
    @GET
    @Path("/auth")
    public Response gubUyAuth() throws URISyntaxException, IOException {
        String url =  gubUyService.getAuthGubUy();
        URI uri=new URI(url);
        return Response.temporaryRedirect(uri).build();
    }
/*        System.out.println(r.getHeaders());
        return r;*/
  /*  @GET
    public Response getTokens(@QueryParam("url") String url ) {
        System.out.println("URL DE MIERDA QUE NO ANDA NI PA TRAS: " + url);
        RubroDTO dto = new RubroDTO();
        dto.setId(1L);
        dto.setNombre("test");
        return Response.status(200).entity(dto).build();
    }*/

    @GET
    @Path("/tokens")
    public Response getToken(@QueryParam("code") String accessCode, @QueryParam("state") String state) throws URISyntaxException {
        String jwt = gubUyService.loginGubUy(accessCode, state);
        return Response.status(Response.Status.SEE_OTHER).location(new URI(FRONTOFFICE_URI + "/login?code=" + jwt)).build();
    }
}
//TODO Despues de redirigir a la pagina de auth gub uy, hay que ver si existe o no el usuario, si existe se redirige y si no se crea una instancia del usuario con un rol cualquiera que despues se puede cambiar, el tema es como pedir los datos del usuario a gub uy ya sea UserInfo Reuqest o ver si se pueden sacar del token