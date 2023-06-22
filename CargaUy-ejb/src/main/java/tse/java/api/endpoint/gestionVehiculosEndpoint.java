package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.model.Vehiculos;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/vehiculos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class gestionVehiculosEndpoint {
    @EJB
    IVehiculosService vs;

    @EJB
    IEmpresasService es;

    @EJB
    IVehiculosDAO vd;


    @GET
    @Path("/{id}")
    public Response getVehiculoById(@PathParam("id") Long id){
        try{
            VehiculoDTO vehiculo = vs.obtenerVehiculoPorId(id);
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getVehiculos(){
        try{
            Vehiculos vehiculos = vs.obtenerVehiculos();
            return Response.status(Response.Status.OK).entity(vehiculos).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response agregarVehiculo(VehiculoDTO vehiculo){
        EmpresaDTO e = es.obtenerEmpresa(vehiculo.getEmpresaId());
        if(e == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con la id " + vehiculo.getEmpresaId()).build();
        }
        Empresa empresa = new Empresa(e);
        Vehiculo nuevoVehiculo = new Vehiculo(vehiculo);
        nuevoVehiculo.setEmpresas(empresa);
        vs.agregarVehiculo(nuevoVehiculo);
        vehiculo = new VehiculoDTO(nuevoVehiculo);
        return Response.status(Response.Status.OK).entity(vehiculo).build();
    }

    @PUT
    @Path("/{id}")
    public Response modificarVehiculo(@PathParam("id") Long id, Vehiculo vehiculo){
        try{
            VehiculoDTO vehiculoDTO = new VehiculoDTO(vehiculo);
            vehiculoDTO.setId(id);
            vs.modificarVehiculo(vehiculoDTO);
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/eliminar/{idEmpresa}/{idVehiculo}")
    public Response eliminarVehiculo(@PathParam("idEmpresa") int idEmpresa, @PathParam("idVehiculo") Long idVehiculo){
        VehiculoDTO v = vs.obtenerVehiculoPorId(idVehiculo);
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo").build();
        }
        EmpresaDTO e = es.obtenerEmpresa(idEmpresa);
        if(e == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con id " + idEmpresa).build();
        }
        try{
            es.borrarVehiculo(idVehiculo);
            vs.eliminarVehiculo(idVehiculo);
            return Response.status(Response.Status.OK).entity("El Vehiculo con id " + idVehiculo + " fue borrado").build();
        } catch (NoResultException error){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
