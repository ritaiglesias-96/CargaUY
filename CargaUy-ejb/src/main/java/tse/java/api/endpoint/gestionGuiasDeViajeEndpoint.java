package tse.java.api.endpoint;

import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.persistance.*;
import tse.java.service.ICiudadanosService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
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
    IVehiculosDAO vd;

    @EJB
    IGuiaDeViajeDAO gd;

    @EJB
    IChoferDAO cd;

    @EJB
    IResponsableDAO rd;

    @EJB
    ICiudadanosService is;

    @EJB
    IAsignacionDAO ad;

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
        List<AsignacionDTO> asignaciones = c.getAsignaciones();
        for(AsignacionDTO a:asignaciones){
            if(a.getGuia().getFin()==null && vs.viajeContieneGuia(v,a.getGuia()))
                result.add(a.getGuia());
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

        int nueva_guia = gd.getNextNumeroViaje();
        GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(null, nueva_guia, dtalta.getRubroCliente(), dtalta.getTipoCarga(), dtalta.getVolumenCarga(), new Date(), dtalta.getOrigen(), null, null, dtalta.getDestino(), new ArrayList<PesajeDTO>());
        gs.crearGuiaDeViaje(dtguia);
        GuiaDeViajeDTO guiadto = gd.buscarGuiaViajePorNumero(nueva_guia);
        GuiaDeViaje galta = new GuiaDeViaje(guiadto);
        AsignacionDTO adt = new AsignacionDTO(null, guiadto, LocalDateTime.now());
        Long id_asignacion = ad.altaAsignacion(adt);
        adt = ad.buscarAsignacion(id_asignacion);
        vs.asignarGuia(v.getId(), adt);
        Asignacion anew = new Asignacion(adt);
        is.asingarViajeResponsable(r.getIdCiudadano(), anew);
        is.asingarViajeChofer(c.getIdCiudadano(), anew);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/finalizar/{cedula}/{numero}")
    public Response finalizarViaje(@PathParam("cedula") String cedula_chofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = cd.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        GuiaDeViajeDTO g = gd.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }

        if(!is.contieneGuiaViajeChofer(c.getCedula(),g.getNumero())){
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numero_viaje).build();
        }

        if(g.getFin()!=null){
            Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta finalizado");
        }

        g.setFin(new Date());
        gs.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/iniciar/{cedula}/{numero}")
    public Response iniciarViaje(@PathParam("cedula") String cedula_chofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = cd.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        GuiaDeViajeDTO g = gd.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }

        if(!is.contieneGuiaViajeChofer(c.getCedula(),g.getNumero())){
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numero_viaje).build();
        }

        if(g.getInicio()!=null){
            Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta inicializado");
        }

        g.setInicio(new Date());
        gs.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/borrar/{cedula}/{numero}")
    public Response borrarGuia(@PathParam("numero") int numero_viaje, @PathParam("cedula") String cedula_responsable){
        GuiaDeViajeDTO g = gd.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }
        ResponsableDTO r = rd.buscarResponsablePorCedula(cedula_responsable);
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + cedula_responsable).build();
        }
        vs.borrarGuia(numero_viaje);
        is.borrarGuia(numero_viaje);
        borrarGuiaEnAsignacion(numero_viaje);
        gs.borrarGuiaDeViaje(g.getId());
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public void cargarDatos(){
        Chofer c = new Chofer("pepe@gmail.com","1234");
        c.setAsignaciones(new ArrayList<Asignacion>());
        cd.agregarChofer(c);
        Responsable r = new Responsable("hola@gmail.com", "1111");
        r.setAsignaciones(new ArrayList<Asignacion>());
        rd.agregarResponsable(r);
        VehiculoDTO veh = new VehiculoDTO(null, "ACA112", "URY", "Fiat", "Saveiro", Float.valueOf("200"), Float.valueOf("1000"), new Date(), new Date(), new Date(), new ArrayList<AsignacionDTO>());
        vd.agregarVehiculo(veh);
    }

    private void borrarGuiaEnAsignacion(int numero_viaje){
        List<Long> asignaciones_borrar = new ArrayList<Long>();
        for(AsignacionDTO a : ad.listarAsignaciones()){
            if(a.getGuia().getNumero() == numero_viaje){
                a.setGuia(null);
                ad.modificarAsignacion(a);
                asignaciones_borrar.add(a.getId());
            }
        }

        for(Long id:asignaciones_borrar)
            ad.borrarAsignacion(id);
    }

}
