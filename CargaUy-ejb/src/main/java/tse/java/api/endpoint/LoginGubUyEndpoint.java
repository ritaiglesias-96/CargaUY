package tse.java.api.endpoint;

import tse.java.service.IGubUyService;
import tse.java.service.impl.GubUyService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@RequestScoped
@Path("/gubuy")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginGubUyEndpoint {

    @EJB
    IGubUyService gubUyService;

    @GET
    public Response gubUyAuth() throws URISyntaxException {
        //TODO ACA HAY QUE REDIRIGIRLO CON LA URL DE AUTH
        String url =  gubUyService.getAuthGubUy();
        URI uri=new URI(url);
        return Response.temporaryRedirect(uri).build();
    }
}
//TODO Despues de redirigir a la pagina de auth gub uy, hay que ver si existe o no el usuario, si existe se redirige y si no se crea una instancia del usuario con un rol cualquiera que despues se puede cambiar, el tema es como pedir los datos del usuario a gub uy ya sea UserInfo Reuqest o ver si se pueden sacar del token