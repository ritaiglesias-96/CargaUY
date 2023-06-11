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
import java.util.logging.Logger;

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

        VehiculoDTO v = new VehiculoDTO(null, dtAlta.getMatricula(), dtAlta.getPais(), dtAlta.getMarca(), dtAlta.getModelo(), dtAlta.getPeso(), dtAlta.getCapacidadCarga(),
                dtAlta.getFechaFinITV(), dtAlta.getPnc(),  dtAlta.getFechaFinPNC(), dtAlta.getFechaInicioPNC(), null);
        vs.agregarVehiculo(v);
        v = vs.obtenerVehiculoMatriculaPais(dtAlta.getMatricula(), dtAlta.getPais());
        Long idVehiculo = v.getId();
        es.agregarVehiculoAEmpresa(dtAlta.getIdEmpresa(), v);
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
    @Path("/eliminar/{idEmpresa}/{idVehiculo}")
    public Response eliminarVehiculo(@PathParam("idEmpresa") int idEmpresa, @PathParam("idVehiculo") Long idVehiculo, Vehiculo vehiculo){
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
            return Response.status(Response.Status.OK).entity(vehiculo).build();
        } catch (NoResultException error){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
