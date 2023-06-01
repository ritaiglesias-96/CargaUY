package tse.java.api.endpoint;


import tse.java.dto.CiudadanoDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.entity.Funcionario;
import tse.java.entity.Responsable;
import tse.java.exception.ErrorException;
import tse.java.model.Ciudadanos;
import tse.java.service.ICiudadanosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/ciudadanos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class gestionCiudadanosEndpoint {

    @EJB
    ICiudadanosService ciudadanosService;

    @GET
    public Response getCiudadanos(){
        try{
            Ciudadanos c = ciudadanosService.obtenerCiudadanos();
            return Response.status(Response.Status.OK).entity(c).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @POST
    @Path("/funcionario")
    public Response agregarFuncionario(Funcionario funcionario){
        try {
            ciudadanosService.agregarCiudadano((Funcionario) funcionario);
            return Response.status(Response.Status.OK).entity(funcionario).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @POST
    @Path("/responsable")
    public Response agregarResponsable(Responsable responsable){
        try {
            ciudadanosService.agregarCiudadano(responsable);
            return Response.status(Response.Status.OK).entity(responsable).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @POST
    @Path("/chofer")
    public Response agregarChofer(Chofer chofer){
        try {
            ciudadanosService.agregarCiudadano(chofer);
            return Response.status(Response.Status.OK).entity(chofer).build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @PUT
    @Path("/funcionario/{id}")
    public Response modificarFuncionario(Funcionario funcionario,@PathParam("id")int id){
        try{
            funcionario.setIdCiudadano(id);
            ciudadanosService.modificarCiudadano(funcionario);
            return Response.status(Response.Status.OK).entity(funcionario).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/responsable/{id}")
    public Response modificarResponsable(Responsable responsable,@PathParam("id")int id){
        try{
            responsable.setIdCiudadano(id);
            ciudadanosService.modificarCiudadano(responsable);
            return Response.status(Response.Status.OK).entity(responsable).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/chofer/{id}")
    public Response modificarCiudadano(Chofer chofer,@PathParam("id")int id){
        try{
            chofer.setIdCiudadano(id);
            ciudadanosService.modificarCiudadano(chofer);
            return Response.status(Response.Status.OK).entity(chofer).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/funcionario/{id}")
    public Response eliminarFuncionario(Funcionario funcionario,@PathParam("id")int id){
        try{
            funcionario.setIdCiudadano(id);
            ciudadanosService.eliminarCiudadano(funcionario);
            return Response.status(Response.Status.OK).entity(funcionario).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/responsable/{id}")
    public Response eliminarResponsable(Responsable responsable,@PathParam("id")int id){
        try{
            responsable.setIdCiudadano(id);
            ciudadanosService.eliminarCiudadano(responsable);
            return Response.status(Response.Status.OK).entity(responsable).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/chofer/{id}")
    public Response eliminarChofer(Chofer chofer,@PathParam("id")int id){
        try{
            chofer.setIdCiudadano(id);
            ciudadanosService.eliminarCiudadano(chofer);
            return Response.status(Response.Status.OK).entity(chofer).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
