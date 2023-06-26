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
import tse.java.service.*;

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
    ICiudadanosService ciudadanosService;

    @EJB
    IAsignacionesService asignacionesService;

    @EJB
    IEmpresasService empresasService;

    @GET
    @Path("/listar")
    public Response listarViajesAsignados(@QueryParam("cedula") String cedulaChofer, @QueryParam("pais") String pais_matricula, @QueryParam("matricula") String matricula){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
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
    public Response listarViajesChofer(@QueryParam("cedula") String cedulaChofer){
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
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
    public Response crearGuiaDeViaje(GuiaDeViajeAltaDTO nuevaGuia){
        VehiculoDTO vehiculoDTO = vehiculosService.obtenerVehiculoMatriculaPais(nuevaGuia.getMatriculaVehiculo(), nuevaGuia.getPaisVehiculo());
        if(vehiculoDTO != null){
            EmpresaDTO empresaDTO = empresasService.obtenerEmpresa(nuevaGuia.getIdEmpresa());
            if(empresaDTO != null) {
                ChoferDTO c = ciudadanosService.obtenerChofer(nuevaGuia.getCedulaChofer());
                if(c != null) {
                    GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(nuevaGuia);
                    dtguia.setNumero(guiaDeViajesService.getNextNumeroViaje());
                    guiaDeViajesService.crearGuiaDeViaje(dtguia);
                    GuiaDeViajeDTO guiaNueva = guiaDeViajesService.buscarGuiaViajePorNumero(dtguia.getNumero());
                    AsignacionDTO nuevaAsignacion = new AsignacionDTO(null, guiaNueva, LocalDateTime.now());
                    asignacionesService.agregarAsignacion(nuevaAsignacion);
                    AsignacionDTO asignacion = asignacionesService.ultimaIngresada();
                    c.getAsignaciones().add(asignacion);
                    Chofer chofer = new Chofer(c);
                    ciudadanosService.modificarHijoCiudadano(chofer);
                    empresaDTO.getAsignaciones().add(asignacion);
                    empresasService.modificarEmpresa(empresaDTO);
                    vehiculoDTO.getAsignaciones().add(asignacion);
                    vehiculosService.modificarVehiculo(vehiculoDTO);

                    return Response.status(Response.Status.CREATED).entity(guiaNueva).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + nuevaGuia.getCedulaChofer()).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con el numero " + nuevaGuia.getIdEmpresa()).build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + nuevaGuia.getMatriculaVehiculo() + " y pais " + nuevaGuia.getPaisVehiculo()).build();
        }
    }

    @POST
    @Path("/modificar")
    public Response modificarGuiaDeViaje(GuiaDeViajeModificacionDTO dtmodificacion){
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(dtmodificacion.getMatriculaVehiculo(),dtmodificacion.getPaisVehiculo());
        if(v == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + dtmodificacion.getMatriculaVehiculo() + " y pais " + dtmodificacion.getPaisVehiculo()).build();
        }

        EmpresaDTO e = empresasService.obtenerEmpresa(dtmodificacion.getIdEmpresa());
        if(e == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con el numero " + dtmodificacion.getIdEmpresa()).build();
        }

        ChoferDTO c = ciudadanosService.obtenerChofer(dtmodificacion.getCedulaChofer());
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + dtmodificacion.getCedulaChofer()).build();
        }

        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(dtmodificacion.getNumeroViaje());
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe viaje con el identificador " + dtmodificacion.getNumeroViaje()).build();
        }

        g.setOrigen(dtmodificacion.getOrigen());
        g.setDestino(dtmodificacion.getDestino());
        g.setRubroCliente(dtmodificacion.getRubroCliente());
        g.setTipoCarga(dtmodificacion.getTipoCarga());
        g.setVolumenCarga(dtmodificacion.getVolumenCarga());

        guiaDeViajesService.modificarGuiaDeViaje(g);
        AsignacionDTO a = new AsignacionDTO(null, g, LocalDateTime.now());
//        Long id_asignacion = asignacionesService.(a);
//        a = asignacionDAO.buscarAsignacion(id_asignacion);
        vehiculosService.asignarGuia(v.getId(), a);
        empresasService.agregarAsignacionAEmpresa(e.getId(), a);
        Asignacion anew = new Asignacion(a);
    //    ciudadanosService.asingarViajeResponsable(r.getIdCiudadano(), anew);
        ciudadanosService.asingarViajeChofer(c.getIdCiudadano(), anew);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/finalizar/{cedula}/{numero}")
    public Response finalizarViaje(@PathParam("cedula") String cedulaChofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
        }

        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(numero_viaje);
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

        g = guiaDeViajesService.buscarGuiaViajePorNumero(g.getNumero());
        g.setFin(new Date());
        guiaDeViajesService.modificarGuiaDeViaje(g);
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/iniciar/{cedula}/{numero}")
    public Response iniciarViaje(@PathParam("cedula") String cedulaChofer,@PathParam("numero") int numero_viaje){
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if(c == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
        }

        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(numero_viaje);
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
    @Path("/borrar/{numempresa}/{numviaje}")
    public Response borrarGuia(@PathParam("numviaje") int numeroViaje, @PathParam("numempresa") int numeroEmpresa){
        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje);
        if(g == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numeroViaje).build();
        }
        EmpresaDTO e = empresasService.obtenerEmpresa(numeroEmpresa);
        if(e == null){
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con el numero " + numeroEmpresa).build();
        }
//        vehiculosService.borrarGuia(numeroViaje);
//        ciudadanosService.borrarGuia(numeroViaje, numeroEmpresa);
//        empresasService.borrarGuia(numeroViaje);
//        asignacionesService.borrarGuiaEnAsignacion(numeroViaje);
        guiaDeViajesService.borrarGuiaDeViaje(g.getId(), e.getId());
        return Response.status(Response.Status.OK).build();
    }

}
