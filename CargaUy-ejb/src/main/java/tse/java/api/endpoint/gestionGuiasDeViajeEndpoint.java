package tse.java.api.endpoint;

import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.persistance.IChoferDAO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IResponsableDAO;
import tse.java.persistance.IVehiculosDAO;
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
    IVehiculosDAO vd;

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

        int nueva_guia = gd.getNextNumeroViaje();
        GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(null, nueva_guia, dtalta.getRubroCliente(), dtalta.getVolumenCarga(), new Date(), dtalta.getOrigen(), null, null, dtalta.getDestino(), new ArrayList<PesajeDTO>());
        gs.crearGuiaDeViaje(dtguia);
        GuiaDeViajeDTO guiadto = gd.buscarGuiaViajePorNumero(nueva_guia);
        vs.asignarGuia(v.getId(),guiadto);
        is.asingarViajeResponsable(r.getIdCiudadano(),guiadto);
        is.asingarViajeChofer(c.getIdCiudadano(),guiadto);
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
        g.setInicio(new Date());
        gs.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/borrar/{numero}")
    public Response borrarGuia(@PathParam("numero") int numero_viaje){
        GuiaDeViajeDTO g = gd.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }
        gs.borrarGuiaDeViaje(g.getId());
        return Response.status(Response.Status.OK).build();
    }

    @GET
    public Response cargarDatosTest(){
        Chofer c = new Chofer("pepe@gmail.com","1234");
        c.setGuiasDeViaje(new ArrayList<GuiaDeViaje>());
        cd.agregarChofer(c);
        Responsable r = new Responsable("cavani@gmail.com","1445");
        r.setGuiasDeViaje(new ArrayList<GuiaDeViaje>());
        rd.agregarResponsable(r);
        VehiculoDTO v = new VehiculoDTO(null, "ACA112", "URY", "Fiat", "Saveiro", Float.valueOf("200"), Float.valueOf("80"), new Date(), new Date(), new Date(), new ArrayList<GuiaDeViajeDTO>());
        vd.agregarVehiculo(v);
        /*
        int num = gd.getNextNumeroViaje();
        GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(null, num, "Carnes", 100, new Date(),"MVD",new Date(),new Date(),"CAN",new ArrayList<PesajeDTO>());
        gs.crearGuiaDeViaje(dtguia);
        ChoferDTO c2 = cd.buscarChoferPorCedula("1234");
        List<GuiaDeViajeDTO> guias = c2.getGuiasDeViaje();
        GuiaDeViajeDTO g = gd.buscarGuiaViajePorNumero(num);
        guias.add(g);
         */
        return Response.status(Response.Status.OK).build();
    }

}
