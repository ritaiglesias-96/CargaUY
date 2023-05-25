package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.model.Empresas;
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

    @POST
    public Response agregarVehiculo(Vehiculo vehiculo){
        try{
            vs.agregarVehiculo(vehiculo.darDto());
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    public Response modificarVehiculo(Vehiculo vehiculo){
        try{
            VehiculoDTO vehiculoDTO = new VehiculoDTO(vehiculo);
            vs.modificarVehiculo(vehiculoDTO);
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    public Response eliminarVehiculo(Vehiculo vehiculo){
        try{
            vs.eliminarVehiculo(vehiculo.getId());
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
