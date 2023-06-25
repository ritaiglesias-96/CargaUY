package tse.java.api.endpoint;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.persistance.*;
import tse.java.service.IAsignacionesService;
import tse.java.service.ICiudadanosService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Path("/guias")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class gestionGuiasDeViajeEndpoint {

    @EJB
    IGuiaDeViajesService guiaDeViajesService;

    @EJB
    IVehiculosService vehiculosService;

    @EJB
    IGuiaDeViajeDAO guiaDeViajeDAO;

    @EJB
    IChoferDAO choferDAO;

    @EJB
    IResponsableDAO responsableDAO;

    @EJB
    ICiudadanosService ciudadanosService;

    @EJB
    IAsignacionDAO asignacionDAO;

    @EJB
    IAsignacionesService asignacionesService;

    @GET
    @Path("/listar")
    public Response listarViajesAsignados(@QueryParam("cedula") Integer cedula_chofer, @QueryParam("pais") String pais_matricula, @QueryParam("matricula") String matricula){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(matricula,pais_matricula);
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + matricula + " y pais " + pais_matricula).build();
        }
        List<AsignacionDTO> asignaciones = c.getAsignaciones();
        for(AsignacionDTO a:asignaciones){
            Long id = asignacionesService.ultimaAsignacionViaje(a.getGuia().getNumero());
            if(a.getGuia().getFin()==null && vehiculosService.viajeContieneGuia(v,a.getGuia()) && a.getId()==id)
                result.add(a.getGuia());
        }
        if(result.size()>0)
            return Response.status(Response.Status.OK).entity(result).build();
        else
            return Response.status(Response.Status.OK).entity("No tiene viajes asignados").build();
    }
    @GET
    @Path("/listar/chofer")
    public Response listarViajesChofer(@QueryParam("cedula") Integer cedula_chofer){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        List<AsignacionDTO> asignaciones = c.getAsignaciones();
        for(AsignacionDTO a:asignaciones){
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
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(dtalta.getMatricula_vehiculo(),dtalta.getPais_vehiculo());
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + dtalta.getMatricula_vehiculo() + " y pais " + dtalta.getPais_vehiculo()).build();
        }

        ResponsableDTO r = responsableDAO.buscarResponsablePorCedula(dtalta.getCedula_responsable());
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + dtalta.getCedula_responsable()).build();
        }

        ChoferDTO c = choferDAO.buscarChoferPorCedula(dtalta.getCedula_chofer());
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + dtalta.getCedula_chofer()).build();
        }

        int nueva_guia = guiaDeViajeDAO.getNextNumeroViaje();
        GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(null, nueva_guia, dtalta.getRubroCliente(), dtalta.getTipoCarga(), dtalta.getVolumenCarga(), new Date(), dtalta.getOrigen(), null, null, dtalta.getDestino(), new ArrayList<PesajeDTO>());
        guiaDeViajesService.crearGuiaDeViaje(dtguia);
        GuiaDeViajeDTO guiadto = guiaDeViajeDAO.buscarGuiaViajePorNumero(nueva_guia);
        GuiaDeViaje galta = new GuiaDeViaje(guiadto);
        AsignacionDTO adt = new AsignacionDTO(null, guiadto, LocalDateTime.now());
        Long id_asignacion = asignacionDAO.altaAsignacion(adt);
        adt = asignacionDAO.buscarAsignacion(id_asignacion);
        vehiculosService.asignarGuia(v.getId(), adt);
        Asignacion anew = new Asignacion(adt);
    //    ciudadanosService.asingarViajeResponsable(r.getIdCiudadano(), anew);
        ciudadanosService.asingarViajeChofer(c.getIdCiudadano(), anew);
        return Response.status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/modificar")
    public Response modificarGuiaDeViaje(GuiaDeViajeModificacionDTO dtmodificacion){
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(dtmodificacion.getMatricula_vehiculo(),dtmodificacion.getPais_vehiculo());
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + dtmodificacion.getMatricula_vehiculo() + " y pais " + dtmodificacion.getPais_vehiculo()).build();
        }

        ResponsableDTO r = responsableDAO.buscarResponsablePorCedula(dtmodificacion.getCedula_responsable());
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + dtmodificacion.getCedula_responsable()).build();
        }

        ChoferDTO c = choferDAO.buscarChoferPorCedula(dtmodificacion.getCedula_chofer());
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + dtmodificacion.getCedula_chofer()).build();
        }

        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(dtmodificacion.getNumero_viaje());
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe viaje con el identificador " + dtmodificacion.getNumero_viaje()).build();
        }

        g.setOrigen(dtmodificacion.getOrigen());
        g.setDestino(dtmodificacion.getDestino());
        g.setRubroCliente(dtmodificacion.getRubroCliente());
        g.setTipoCarga(dtmodificacion.getTipoCarga());
        g.setVolumenCarga(dtmodificacion.getVolumenCarga());
        guiaDeViajesService.modificarGuiaDeViaje(g);
        AsignacionDTO a = new AsignacionDTO(null, g, LocalDateTime.now());
        Long id_asignacion = asignacionDAO.altaAsignacion(a);
        a = asignacionDAO.buscarAsignacion(id_asignacion);
        vehiculosService.asignarGuia(v.getId(), a);
        Asignacion anew = new Asignacion(a);
    //    ciudadanosService.asingarViajeResponsable(r.getIdCiudadano(), anew);
        ciudadanosService.asingarViajeChofer(c.getIdCiudadano(), anew);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/finalizar/{cedula}/{numero}")
    public Response finalizarViaje(@PathParam("cedula") Integer cedula_chofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }

        if(!ciudadanosService.contieneGuiaViajeChofer(c.getCedula(),g.getNumero())){
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numero_viaje).build();
        }

        if(g.getFin()!=null){
            return Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta finalizado").build();
        }

        try{
            CloseableHttpClient hc = HttpClientBuilder.create().build();
            VehiculoDTO v = vehiculosService.buscarVehiculoPorGuia(g.getNumero());
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            HttpGet hg = new HttpGet("http://localhost:9096/vehiculos/listarPesajesPorFecha?matricula=" + v.getMatricula() + "&pais=" + v.getPais() + "&fecha=" + simpleDateFormat.format(new Date()) + "&numeroviaje=" + g.getNumero());
            CloseableHttpResponse hr = hc.execute(hg);
            if(hr.getStatusLine().getStatusCode()==200){
                HttpEntity entity = hr.getEntity();
                String responseBody = EntityUtils.toString(entity);
                ObjectMapper objectMapper = new ObjectMapper();
                JsonNode jsonNode = objectMapper.readTree(responseBody);
                Iterator<JsonNode> iter = jsonNode.elements();
                List<PesajeDTO> pesajes = new ArrayList<PesajeDTO>();
                while (iter.hasNext()) {
                    JsonNode node = iter.next();
                    String hora = node.get("hora").asText();
                    double latitud = node.get("latitud").asDouble();
                    double longuitud = node.get("longuitud").asDouble();
                    float carga = Float.valueOf(node.get("carga").asText());
                    LocalDateTime fecha = LocalDateTime.of(LocalDate.now(), LocalTime.parse(hora));
                    PesajeDTO p = new PesajeDTO(null, latitud, longuitud, fecha, carga);
                    pesajes.add(p);
                    String msg = "Datos pesaje, fecha:" + hora + ", latitud: " + latitud + ", longuitud: " + longuitud + ", carga: " + carga;
                    Logger.getLogger(gestionGuiasDeViajeEndpoint.class.getName()).log(Level.INFO, msg);
                }
                guiaDeViajesService.asignarPesajes(g.getNumero(), pesajes);
            } else {
                Logger.getLogger(gestionGuiasDeViajeEndpoint.class.getName()).log(Level.INFO, "No se encontraron pesajes con los parametros ingresados...");
            }
        } catch (Exception e) {
            Logger.getLogger(gestionGuiasDeViajeEndpoint.class.getName()).log(Level.SEVERE, null, e);
        }

        g = guiaDeViajeDAO.buscarGuiaViajePorNumero(g.getNumero());
        g.setFin(new Date());
        guiaDeViajesService.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/iniciar/{cedula}/{numero}")
    public Response iniciarViaje(@PathParam("cedula") Integer cedula_chofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = choferDAO.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }

        if(!ciudadanosService.contieneGuiaViajeChofer(c.getCedula(),g.getNumero())){
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numero_viaje).build();
        }

        if(g.getInicio()!=null){
            return Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta inicializado").build();
        }

        g.setInicio(new Date());
        guiaDeViajesService.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/borrar/{cedula}/{numero}")
    public Response borrarGuia(@PathParam("numero") int numero_viaje, @PathParam("cedula") String cedula_responsable){
        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }
        ResponsableDTO r = responsableDAO.buscarResponsablePorCedula(cedula_responsable);
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + cedula_responsable).build();
        }
        vehiculosService.borrarGuia(numero_viaje);
        ciudadanosService.borrarGuia(numero_viaje);
        asignacionesService.borrarGuiaEnAsignacion(numero_viaje);
        guiaDeViajesService.borrarGuiaDeViaje(g.getId());
        return Response.status(Response.Status.OK).build();
    }
}
