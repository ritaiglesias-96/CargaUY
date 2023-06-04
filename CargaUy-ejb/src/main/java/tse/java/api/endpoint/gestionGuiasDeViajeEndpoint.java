package tse.java.api.endpoint;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RequestScoped
@Path("/guias")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class gestionGuiasDeViajeEndpoint {

    @EJB
    IGuiaDeViajesService guiaviajeService;

    @EJB
    IVehiculosService vehiculoService;

    @EJB
    IVehiculosDAO vehiculoDao;

    @EJB
    IGuiaDeViajeDAO guiaviajeDao;

    @EJB
    IChoferDAO choferDao;

    @EJB
    IResponsableDAO responsableDao;

    @EJB
    ICiudadanosService ciudadanoService;

    @GET
    @Path("/listar")
    public Response listarViajesAsignados(@QueryParam("cedula") String cedula_chofer, @QueryParam("pais") String pais_matricula, @QueryParam("matricula") String matricula){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = choferDao.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }
        VehiculoDTO v = vehiculoService.obtenerVehiculoMatriculaPais(matricula,pais_matricula);
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + matricula + " y pais " + pais_matricula).build();
        }
        List<GuiaDeViajeDTO> guias_de_viaje = c.getGuiasDeViaje();
        for(GuiaDeViajeDTO g:guias_de_viaje){
            if(g.getFin()==null && vehiculoService.viajeContieneGuia(v,g))
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
        VehiculoDTO v = vehiculoService.obtenerVehiculoMatriculaPais(dtalta.getMatricula_vehiculo(),dtalta.getPais_vehiculo());
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + dtalta.getMatricula_vehiculo() + " y pais " + dtalta.getPais_vehiculo()).build();
        }

        ResponsableDTO r = responsableDao.buscarResponsablePorCedula(dtalta.getCedula_responsable());
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + dtalta.getCedula_responsable()).build();
        }

        ChoferDTO c = choferDao.buscarChoferPorCedula(dtalta.getCedula_chofer());
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + dtalta.getCedula_chofer()).build();
        }

        int nueva_guia = guiaviajeDao.getNextNumeroViaje();
        GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(null, nueva_guia, dtalta.getRubroCliente(), dtalta.getVolumenCarga(), new Date(), dtalta.getOrigen(), null, null, dtalta.getDestino(), new ArrayList<PesajeDTO>());
        guiaviajeService.crearGuiaDeViaje(dtguia);
        GuiaDeViajeDTO guiadto = guiaviajeDao.buscarGuiaViajePorNumero(nueva_guia);
        vehiculoService.asignarGuia(v.getId(),guiadto);
        ciudadanoService.asingarViajeResponsable(r.getIdCiudadano(),guiadto);
        ciudadanoService.asingarViajeChofer(c.getIdCiudadano(),guiadto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/finalizar/{cedula}/{numero}")
    public Response finalizarViaje(@PathParam("cedula") String cedula_chofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = choferDao.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        GuiaDeViajeDTO g = guiaviajeDao.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }

        if(!ciudadanoService.contieneGuiaViajeChofer(c.getCedula(),g.getNumero())){
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numero_viaje).build();
        }

        if(g.getFin()!=null){
            Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta finalizado");
        }

        g.setFin(new Date());

        try{
            CloseableHttpClient hc = HttpClientBuilder.create().build();
            VehiculoDTO v = vehiculoService.buscarVehiculoPorGuia(g.getNumero());
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
                guiaviajeService.asignarPesajes(g.getNumero(), pesajes);
            } else {
                Logger.getLogger(gestionGuiasDeViajeEndpoint.class.getName()).log(Level.INFO, "No se encontraron pesajes con los parametros ingresados...");
            }
        } catch (Exception e) {
            Logger.getLogger(gestionGuiasDeViajeEndpoint.class.getName()).log(Level.SEVERE, null, e);
        }
        g = guiaviajeDao.buscarGuiaViajePorNumero(g.getNumero());
        guiaviajeService.modificarGuiaDeViaje(g);


        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/iniciar/{cedula}/{numero}")
    public Response iniciarViaje(@PathParam("cedula") String cedula_chofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = choferDao.buscarChoferPorCedula(cedula_chofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedula_chofer).build();
        }

        GuiaDeViajeDTO g = guiaviajeDao.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }

        if(!ciudadanoService.contieneGuiaViajeChofer(c.getCedula(),g.getNumero())){
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numero_viaje).build();
        }

        if(g.getInicio()!=null){
            Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta inicializado");
        }

        g.setInicio(new Date());
        guiaviajeService.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @DELETE
    @Path("/borrar/{cedula}/{numero}")
    public Response borrarGuia(@PathParam("numero") int numero_viaje, @PathParam("cedula") String cedula_responsable){
        GuiaDeViajeDTO g = guiaviajeDao.buscarGuiaViajePorNumero(numero_viaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numero_viaje).build();
        }
        ResponsableDTO r = responsableDao.buscarResponsablePorCedula(cedula_responsable);
        if(r == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe responsable con la cedula " + cedula_responsable).build();
        }
        vehiculoService.borrarGuia(numero_viaje);
        ciudadanoService.borrarGuia(numero_viaje);
        guiaviajeService.borrarGuiaDeViaje(g.getId());
        return Response.status(Response.Status.OK).build();
    }

}
