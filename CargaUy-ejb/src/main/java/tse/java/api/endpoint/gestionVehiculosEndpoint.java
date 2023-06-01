package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.ResponsableDTO;
import tse.java.dto.VehiculoAltaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Vehiculo;
import tse.java.model.Vehiculos;
import tse.java.persistance.IResponsableDAO;
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
    IResponsableDAO rd;

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
    public Response agregarVehiculo(VehiculoAltaDTO dtAlta){
        EmpresaDTO e = es.obtenerEmpresa(dtAlta.getIdEmpresa());
        if(e == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con la id " + dtAlta.getIdEmpresa()).build();
        }

        ResponsableDTO r = rd.buscarResponsablePorCedula(dtAlta.getCedula_responsable());
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + dtAlta.getCedula_responsable()).build();
        }

        Long idVehiculo = vd.getNextIdVehiculo();
        VehiculoDTO v = new VehiculoDTO(null, dtAlta.getMatricula(), dtAlta.getPais(), dtAlta.getMarca(), dtAlta.getModelo(), dtAlta.getPeso(), dtAlta.getCapacidadCarga(),
                dtAlta.getFechaFinITV(), dtAlta.getFechaFinPNC(), dtAlta.getFechaInicioPNC(), null);
        vs.agregarVehiculo(v);
        es.agregarVehiculoAEmpresa(dtAlta.getIdEmpresa(), v);
        VehiculoDTO vehiculoDTO = vd.obtenerVehiculoId(idVehiculo);
        es.agregarVehiculoAEmpresa(e.getId(), vehiculoDTO);
        return Response.status(Response.Status.CREATED).build();
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
    @Path("/{id}")
    public Response eliminarVehiculo(@PathParam("id") Long id, Vehiculo vehiculo){
        try{
            vs.eliminarVehiculo(id);
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException e){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
