package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.service.IEmpresasService;
import tse.java.soappdi.*;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
@Path("/empresas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GestionEmpresasEndpoint {

    @EJB
    IEmpresasService empresasService;

    @GET
    public Response getEmpresas(){
        try{
            ArrayList<EmpresaDTO> e = empresasService.obtenerEmpresas();
            return Response.status(Response.Status.OK).entity(e).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getEmpresaById(@PathParam("id") int id){
        try{
            EmpresaDTO empresa = empresasService.obtenerEmpresa(id);
            return Response.status(Response.Status.OK).entity(empresa).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response agregarEmpresa(String rut){
        int resultado = empresasService.agregarEmpresa(rut);
        if(resultado == 1){
            return Response.status(Response.Status.OK).entity("Empresa " + rut + " creada exitosamente.").build();
        } else if (resultado == 0){
            return Response.status(Response.Status.NOT_FOUND).entity("NO existe la empresa " + rut).build();
        } else {
            return Response.status(Response.Status.REQUEST_TIMEOUT).entity("Hubo un error al comunicarse con la plataforma").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response modificarEmpresa(Empresa empresa,@PathParam("id") int id){
        try{
            EmpresaDTO empresaDTO = new EmpresaDTO(empresa);
            empresaDTO.setId(id);
      empresasService.modificarEmpresa(empresaDTO);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response eliminarEmpresa (@PathParam("id")int id){
        try{
            empresasService.eliminarEmpresa(id);
            return Response.status(Response.Status.OK).entity(id).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/{id}/vehiculos")
    public Response listarVehiculos(@PathParam("id") int id){
        try{
            List<VehiculoDTO> vehiculos = empresasService.listarVehiculos(id);
            return Response.status(Response.Status.OK).entity(vehiculos).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
