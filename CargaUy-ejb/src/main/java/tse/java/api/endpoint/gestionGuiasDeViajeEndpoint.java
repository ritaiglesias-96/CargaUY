package tse.java.api.endpoint;

import tse.java.dto.*;
import tse.java.entity.Chofer;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Responsable;
import tse.java.entity.Vehiculo;
import tse.java.persistance.IChoferDAO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IResponsableDAO;
import tse.java.service.ICiudadanosService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestScoped
@Path("/guias")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class gestionGuiasDeViajeEndpoint {

    @EJB
    IGuiaDeViajesService gs;

    @EJB
    IVehiculosService vs;

    @EJB
    IGuiaDeViajeDAO gd;

    @EJB
    IChoferDAO cd;

    @EJB
    IResponsableDAO rd;

    @EJB
    ICiudadanosService is;

    @GET
    @Path("/listar")
    public Response listarViajesAsignados(@QueryParam("cedula") String cedula_chofer, @QueryParam("pais") String pais_matricula, @QueryParam("matricula") String matricula){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = cd.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }
        VehiculoDTO v = vs.obtenerVehiculoMatriculaPais(matricula,pais_matricula);
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + matricula + " y pais " + pais_matricula).build();
        }
        List<GuiaDeViajeDTO> guias_de_viaje = c.getGuiasDeViaje();
        for(GuiaDeViajeDTO g:guias_de_viaje){
            if(g.getFin()==null && vs.viajeContieneGuia(v,g))
                result.add(g);
        }
        if(result.size()>0)
            return Response.status(Response.Status.OK).entity(result).build();
        else
            return Response.status(Response.Status.OK).entity("No tiene viajes asignados").build();
    }

    @POST
    @Path("/crear")
    public Response crearGuiaDeViaje(GuiaDeViajeAltaDTO dtalta){
        VehiculoDTO v = vs.obtenerVehiculoMatriculaPais(dtalta.getMatricula_vehiculo(),dtalta.getPais_vehiculo());
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + dtalta.getMatricula_vehiculo() + " y pais " + dtalta.getPais_vehiculo()).build();
        }

        ResponsableDTO r = rd.buscarResponsablePorCedula(dtalta.getCedula_responsable());
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + dtalta.getCedula_responsable()).build();
        }

        ChoferDTO c = cd.buscarChoferPorCedula(dtalta.getCedula_chofer());
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + dtalta.getCedula_chofer()).build();
        }

        GuiaDeViajeDTO dtguia = (GuiaDeViajeDTO) dtalta;
        if(v!=null && vs.viajeContieneGuia(v,dtguia)) {
            gs.crearGuiaDeViaje(dtguia);
            Long idGuianew = gd.getLastid();
            GuiaDeViajeDTO guiadto = gs.buscarGuiaDeViaje(idGuianew);
            vs.asignarGuia(v.getId(),guiadto);
            is.asingarViajeResponsable(r.getIdCiudadano(),guiadto);
            is.asingarViajeChofer(c.getIdCiudadano(),guiadto);
            return Response.status(Response.Status.CREATED).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @PUT
    @Path("/finalizar/{cedula}/{fecha}")
    public Response finalizarViaje(@PathParam("cedula") String cedula_chofer,@PathParam("fecha") String fecha_viaje){
        // TODO Busco los viajes sin finalizar para el chofer cedula_chofer y en la fecha fecha_viaje
        return Response.status(Response.Status.OK).build();

    }

    //@DELETE
    //@Path("/borrar")

    @GET
    public Response cargarDatosTest(){
        //Chofer c = new Chofer("pepe@gmail.com","1234");
        //c.setGuiasDeViaje(new ArrayList<GuiaDeViaje>());
        //cd.agregarChofer(c);
        GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(null, "Carnes", 100, new Date(),"MVD",new Date(),new Date(),"CAN",new ArrayList<PesajeDTO>());
        gs.crearGuiaDeViaje(dtguia);
        return Response.status(Response.Status.OK).build();
    }

}
