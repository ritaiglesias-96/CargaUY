package tse.java.api.endpoint;

import org.primefaces.shaded.json.JSONObject;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoAltaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.exception.VehiuloException;
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
import java.util.List;

@RequestScoped
@Path("/vehiculos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class GestionVehiculosEndpoint {
    @EJB
    IVehiculosService vs;

    @EJB
    IEmpresasService es;

    @GET
    @Path("/{id}")
    public Response getVehiculoById(@PathParam("id") int id){
        try{
            VehiculoDTO vehiculo = vs.obtenerVehiculoPorId(id);
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (VehiuloException e) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getVehiculos(){
        try{
            List<VehiculoDTO> vehiculos = vs.obtenerVehiculos();
            return Response.status(Response.Status.OK).entity(vehiculos).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    public Response agregarVehiculo(VehiculoAltaDTO vehiculo){
        System.out.println(vehiculo.getIdEmpresa());
        System.out.println(vehiculo.getMatricula());
        JSONObject v = new JSONObject(vehiculo);
        System.out.println(v);
        EmpresaDTO e = es.obtenerEmpresa(vehiculo.getIdEmpresa());
        if(e == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con la id " + vehiculo.getIdEmpresa()).build();
        }
        vs.agregarVehiculo(new VehiculoDTO(vehiculo));
        return Response.status(Response.Status.OK).entity(vehiculo).build();
    }

    @PUT
    @Path("/{id}")
    public Response modificarVehiculo(@PathParam("id") int id, VehiculoDTO vehiculo){
        try{
            vehiculo.setId(id);
            vs.modificarVehiculo(vehiculo);
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{idVehiculo}")
    public Response eliminarVehiculo(@PathParam("idVehiculo") int idVehiculo){
        try {
            VehiculoDTO v = vs.obtenerVehiculoPorId(idVehiculo);
            try {
                vs.eliminarVehiculo(idVehiculo);
                return Response.status(Response.Status.OK).entity("El Vehiculo con id " + idVehiculo + " fue borrado").build();
            } catch (NoResultException error) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
        }catch (Exception e){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo").build();
        }
    }

}
