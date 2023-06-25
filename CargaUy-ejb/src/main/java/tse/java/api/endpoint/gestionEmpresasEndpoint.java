package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.model.Empresas;
import tse.java.service.IEmpresasService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


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
    public Response agregarEmpresa(Empresa empresa){
        try{
            empresasService.agregarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
            return Response.status(Response.Status.OK).entity(empresa).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
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
    public Response eliminarEmpresa(@PathParam("id")int id){
        try{
            EmpresaDTO empresaDTO = empresasService.obtenerEmpresa(id);
            empresasService.eliminarEmpresa(empresaDTO);
            return Response.status(Response.Status.OK).build();
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
