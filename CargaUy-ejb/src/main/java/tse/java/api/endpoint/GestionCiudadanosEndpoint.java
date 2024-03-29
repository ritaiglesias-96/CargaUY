package tse.java.api.endpoint;


import tse.java.dto.CiudadanoDTO;
import tse.java.entity.*;
import tse.java.enumerated.RolCiudadano;
import tse.java.model.Ciudadanos;
import tse.java.service.ICiudadanosService;
import tse.java.service.IEmpresasService;
import tse.java.soappdi.EmpresaServicePort;
import tse.java.soappdi.EmpresaServicePortService;
import tse.java.soappdi.GetCiudadanoRequest;
import tse.java.soappdi.GetCiudadanoResponse;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Path("/ciudadanos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GestionCiudadanosEndpoint {

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
    public Response agregarCiudadano(Ciudadano ciudadano){
        try{
            Ciudadano c = ciudadanosService.obtenerCiudadanoPorCedula(ciudadano.getCedula());
           // if (c != null) { //TODO Esto esta como el orto o soy yo?
                ciudadanosService.agregarCiudadano(ciudadano);
                return Response.status(Response.Status.OK).entity(ciudadano).build();
            //} else {
                //return Response.status(Response.Status.CONFLICT).entity("El ciudadano que quiere agregar ya existe en la base de datos").build(); //TODO NOT ENTITY
            //}
        }catch (NoResultException e){
            return Response.status(Response.Status.CONFLICT).entity("El ciudadano que quiere agregar ya existe en la base de datos").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modificarCiudadano(Ciudadano ciudadano,@PathParam("id")int id){
        try{
           // Ciudadano c = ciudadanosService.obtenerCiudadanoPorCedula(ciudadano.getCedula());
           // if (c != null) {
                ciudadano.setIdCiudadano(id);
                ciudadanosService.modificarCiudadano(ciudadano);
                return Response.status(Response.Status.OK).entity(ciudadano).build();
            //}else {
              //  return Response.status(Response.Status.NOT_FOUND).entity("El ciudadano ingresado no existe").build();
           // }
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarCiudadano(@PathParam("id")int id){
        try{
            ciudadanosService.eliminarCiudadano(id);
            return Response.status(Response.Status.OK).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/funcionario")
    public Response agregarFuncionario(Funcionario funcionario){
        funcionario.setRol(RolCiudadano.FUNCIONARIO);
        try {
            ciudadanosService.agregarHijoCiudadano(funcionario);
            return Response.status(Response.Status.OK).entity(funcionario).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
    @POST
    @Path("/responsable")
    public Response agregarResponsable(Responsable responsable){
        responsable.setRol(RolCiudadano.RESPONSABLE);
        try {
            ciudadanosService.agregarHijoCiudadano(responsable);
            return Response.status(Response.Status.OK).entity(responsable).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
    @POST
    @Path("/chofer")
    public Response agregarChofer(Chofer chofer){
        chofer.setRol(RolCiudadano.CHOFER);
        try {
            ciudadanosService.agregarHijoCiudadano(chofer);
            return Response.status(Response.Status.OK).entity(chofer).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }


    @PUT
    @Path("/funcionario/{id}")
    public Response modificarFuncionario(Funcionario funcionario,@PathParam("id")int id){
        try{
            funcionario.setIdCiudadano(id);
            ciudadanosService.modificarHijoCiudadano(funcionario);
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
            ciudadanosService.modificarHijoCiudadano(responsable);
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
            ciudadanosService.modificarHijoCiudadano(chofer);
            return Response.status(Response.Status.OK).entity(chofer).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/funcionario/{id}")
    public Response eliminarFuncionario(@PathParam("id")int id){
        try{
            ciudadanosService.eliminarHijoCiudadano(id);
            return Response.status(Response.Status.OK).entity(id).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/responsable/{id}")
    public Response eliminarResponsable(@PathParam("id")int id){
        try{
            ciudadanosService.eliminarHijoCiudadano(id);
            return Response.status(Response.Status.OK).entity(id).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/chofer/{id}")
    public Response eliminarChofer(@PathParam("id")int id){
        try{
            ciudadanosService.eliminarHijoCiudadano(id);
            return Response.status(Response.Status.OK).entity(id).build();
        }catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Path("/responsable/empresa/{id}/{empresa_id}")
    public Response asignarEmpresa(Empresa empresa, @PathParam("id")int id,@PathParam("empresa_id") int empresaId ){
        try{
            empresa.setId(empresaId);
            ciudadanosService.asignarEmpresa(id, empresa);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/responsable/empresa/{id}/{empresa_id}")
    public Response eliminarEmpresa(Empresa empresa, @PathParam("id")int id,@PathParam("empresa_id") int empresaId ){
        try{
            empresa.setId(empresaId);
            ciudadanosService.eliminarEmpresa(id, empresa);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @PUT
    @Path("/chofer/empresa/{id}/{empresa_id}")
    public Response asignarEmpresaChofer(Empresa empresa, @PathParam("id")int id,@PathParam("empresa_id") int empresaId ){
        try{
            empresa.setId(empresaId);
            ciudadanosService.asignarEmpresaChofer(id, empresa);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    @DELETE
    @Path("/chofer/empresa/{id}/{empresa_id}")
    public Response eliminarEmpresaChofer(Empresa empresa, @PathParam("id")int id,@PathParam("empresa_id") int empresaId ){
        try{
            empresa.setId(empresaId);
            ciudadanosService.eliminarEmpresaChofer(id, empresa);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/test/{cedula}")
    public Response buscarCiudadanoPdi(@PathParam("cedula") String cedula) {
        try {
            EmpresaServicePortService empresaService = new EmpresaServicePortService();
            EmpresaServicePort empresaPort = empresaService.getEmpresaServicePortSoap11();
            GetCiudadanoRequest ciudadanoRequest = new GetCiudadanoRequest();
            ciudadanoRequest.setCedula(cedula);
            GetCiudadanoResponse ciudadanoResponse = empresaPort.getCiudadano(ciudadanoRequest);
            tse.java.soappdi.Ciudadano ciudadano = ciudadanoResponse.getCiudadano();
            if (ciudadano == null) {

                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.status(Response.Status.OK).build();
            }
        } catch (Exception var7) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
