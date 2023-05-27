package tse.java.api.endpoint;

import tse.java.dto.GuiaDeViajeAltaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Vehiculo;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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

    @GET
    @Path("/listar")
    public Response listarViajesAsignados(@QueryParam("cedula") String cedula_chofer, @QueryParam("pais") String pais_matricula, @QueryParam("matricula") String matricula){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        // TODO Buscar chofer por cedula_chofer
        VehiculoDTO v = vs.obtenerVehiculoMatriculaPais(matricula,pais_matricula);
        List<GuiaDeViajeDTO> guias_de_viaje = v.getGuiasDeViaje();
        for(GuiaDeViajeDTO g:guias_de_viaje){
            // TODO si el chofer contiene la guia de viaje y si esta sin finalizar la guia
            if(g.getFin()==null)
                result.add(g);
        }
        if(result.size()>0)
            return Response.status(Response.Status.OK).entity(result).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Path("/crear")
    public Response crearGuiaDeViaje(GuiaDeViajeAltaDTO dtalta){
        VehiculoDTO v = vs.obtenerVehiculoMatriculaPais(dtalta.getMatricula_vehiculo(),dtalta.getPais_vehiculo());
        // TODO Buscar chofer por dtalta.getCedula_chofer() y Buscar responsable por dtalta.getCedula_responsable()
        GuiaDeViajeDTO dtguia = (GuiaDeViajeDTO) dtalta;
        if(v!=null && vs.viajeContieneGuia(v,dtguia)) {
            gs.crearGuiaDeViaje(dtguia);
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

    @DELETE
    @Path("/borrar")



}
