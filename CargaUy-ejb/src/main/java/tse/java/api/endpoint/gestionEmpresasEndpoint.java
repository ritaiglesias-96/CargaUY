package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.entity.Empresa;
import tse.java.model.Empresas;
import tse.java.service.IEmpresasService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RequestScoped
@Path("/empresas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class gestionEmpresasEndpoint {

    @EJB
    IEmpresasService empresasService;

    @GET
    public Response getEmpresas(){
        try{
            Empresas e = empresasService.obtenerEmpresas();
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
    public Response agregarEmpresa(Empresa empresa){
        try{
            empresasService.agregarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    public Response modificarEmpresa(Empresa empresa){
        try{
            EmpresaDTO empresaDTO = new EmpresaDTO(empresa);
            empresasService.modificarEmpresa(empresaDTO);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public Response eliminarEmpresa(Empresa empresa){
        try{
            EmpresaDTO empresaDTO = new EmpresaDTO(empresa);
            empresasService.eliminarEmpresa(empresaDTO);
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
