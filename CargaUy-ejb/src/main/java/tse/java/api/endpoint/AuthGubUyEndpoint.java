package tse.java.api.endpoint;


import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/auth")
public class AuthGubUyEndpoint {


    @GET
    @Path("/gubuy")
    public Response authenticationRequest(){
        //TODO Acá habria que llamar al servicio de gubuy?
        return null;
    }

    @GET
    @Path("/gubuy/login")
    public Response gubUyLogin(/*TODO ACA IRIA EN TEORIA EL ACCESS CODE Y ESAS COSAS(preguntar)*/){
        //TODO Despues de tener los tokens quedaría armar el usuario
        return null;
    }

    @GET
    @Path("/gubuy/login/mobile")
    public Response gubUyLoginMobile(/*TODO Idem que arriba (creo)*/){
        //TODO Idem arriba
        return null;
    }
}
