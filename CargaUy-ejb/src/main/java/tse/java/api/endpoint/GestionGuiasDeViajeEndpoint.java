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
import java.time.ZoneId;
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
public class GestionGuiasDeViajeEndpoint {

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
    public Response listarViajesAsignados(@QueryParam("cedula") String cedulaChofer, @QueryParam("pais") String pais_matricula, @QueryParam("matricula") String matricula) {
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
        }
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais_matricula);
        if (v == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + matricula + " y pais " + pais_matricula).build();
        }
        List<AsignacionDTO> asignaciones = c.getAsignaciones();
        for (AsignacionDTO a : asignaciones) {
            int id = asignacionesService.ultimaAsignacionViaje(a.getGuia().getNumero());
            if (a.getGuia().getFin() == null && vehiculosService.viajeContieneGuia(v, a.getGuia()) && a.getId() == id)
                result.add(a.getGuia());
        }
        if (result.size() > 0)
            return Response.status(Response.Status.OK).entity(result).build();
        else
            return Response.status(Response.Status.OK).entity("No tiene viajes asignados").build();
    }

    @GET
    @Path("/listar/{idEmpresa}")
    public Response listarGuiasDeEmpresa(@PathParam("idEmpresa") int idEmpresa) {
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        EmpresaDTO e = empresasService.obtenerEmpresa(idEmpresa);
        if (e.getAsignaciones() != null) {
            List<AsignacionDTO> asignaciones = e.getAsignaciones();
            for (AsignacionDTO a : asignaciones) {
                if (!result.contains(a.getGuia())) {
                    result.add(a.getGuia());
                }
            }
            if (result.size() > 0)
                return Response.status(Response.Status.OK).entity(result).build();
        }
        return Response.status(Response.Status.OK).entity("No tiene guias de viaje en su empresa!").build();
    }



    @GET
    @Path("/listar/chofer")
    public Response listarViajesChofer(@QueryParam("cedula") String cedulaChofer) {
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
        }

        List<AsignacionDTO> asignaciones = c.getAsignaciones();
        for (AsignacionDTO a : asignaciones) {
            result.add(a.getGuia());
        }
        if (result.size() > 0)
            return Response.status(Response.Status.OK).entity(result).build();
        else
            return Response.status(Response.Status.OK).entity("No tiene viajes asignados").build();
    }

    @POST
    @Path("/crear")
    public Response crearGuiaDeViaje(GuiaDeViajeAltaDTO nuevaGuia) {
        EmpresaDTO empresaDTO = empresasService.obtenerEmpresa(nuevaGuia.getIdEmpresa());
        if (empresaDTO != null) {
            VehiculoDTO vehiculoDTO = vehiculosService.obtenerVehiculoMatriculaPais(nuevaGuia.getMatriculaVehiculo(), nuevaGuia.getPaisVehiculo());
            ChoferDTO choferDTO = ciudadanosService.obtenerChofer(nuevaGuia.getCedulaChofer());
            if (vehiculoDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + nuevaGuia.getMatriculaVehiculo() + " y pais " + nuevaGuia.getPaisVehiculo()).build();
            } else if (choferDTO == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + nuevaGuia.getCedulaChofer()).build();
            } else if (empresasService.contieneChofer(choferDTO.getIdCiudadano(), empresaDTO) && empresasService.contieneVehiculo(vehiculoDTO.getId(), empresaDTO)) {
                GuiaDeViajeDTO dtguia = new GuiaDeViajeDTO(nuevaGuia);
                dtguia.setNumero(guiaDeViajesService.getNextNumeroViaje());
                LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                System.out.println(today);
                dtguia.setFecha(today);
                guiaDeViajesService.crearGuiaDeViaje(dtguia, choferDTO, empresaDTO, vehiculoDTO);
                GuiaDeViajeDTO guiaNueva = guiaDeViajesService.buscarGuiaViajePorNumero(dtguia.getNumero());
                return Response.status(Response.Status.CREATED).entity(guiaNueva).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("El chofer o el vehiculo ingresado no pertenece a la empresa seleccionada").build();
            }
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con el numero " + nuevaGuia.getIdEmpresa()).build();
        }
    }

    @PUT
    @Path("/modificar")
    public Response modificarGuiaDeViaje(GuiaDeViajeModificacionDTO dtmodificacion) {
        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(dtmodificacion.getNumeroViaje());
        if (g == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe viaje con el identificador " + dtmodificacion.getNumeroViaje()).build();
        } else {
            VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(dtmodificacion.getMatriculaVehiculo(), dtmodificacion.getPaisVehiculo());
            if (v == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe vehiculo con la matricula " + dtmodificacion.getMatriculaVehiculo() + " y pais " + dtmodificacion.getPaisVehiculo()).build();
            } else {
                EmpresaDTO e = empresasService.obtenerEmpresa(dtmodificacion.getIdEmpresa());
                if (e == null) {
                    return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con el numero " + dtmodificacion.getIdEmpresa()).build();
                } else {
                    ChoferDTO c = ciudadanosService.obtenerChofer(dtmodificacion.getCedulaChofer());
                    if (c == null) {
                        return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + dtmodificacion.getCedulaChofer()).build();
                    } else {
                        g.setOrigen(dtmodificacion.getOrigen());
                        g.setDestino(dtmodificacion.getDestino());
                        g.setRubroCliente(dtmodificacion.getRubroCliente());
                        g.setTipoCarga(dtmodificacion.getTipoCarga());
                        g.setVolumenCarga(dtmodificacion.getVolumenCarga());
                        guiaDeViajesService.modificarGuiaDeViaje(g, c, e, v);
                        return Response.status(Response.Status.CREATED).build();
                    }
                }
            }
        }
    }

    @PUT
    @Path("/finalizar/{cedula}/{numero}")
    public Response finalizarViaje(@PathParam("cedula") String cedulaChofer, @PathParam("numero") int numeroViaje) {
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
        }
        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje);
        if (g == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numeroViaje).build();
        }

        if (!ciudadanosService.contieneGuiaViajeChofer(c.getCedula(), g.getNumero())) {
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numeroViaje).build();
        }

        if (g.getFin() != null) {
            return Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta finalizado").build();
        }

        VehiculoDTO v = vehiculosService.buscarVehiculoPorGuia(g.getNumero());
        try {
            CloseableHttpClient hc = HttpClientBuilder.create().build();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            HttpGet hg = new HttpGet("https://nodo-balanzas-mbravo95-dev.apps.sandbox-m4.g2pi.p1.openshiftapps.com/nodobalanzas/vehiculos/listarPesajesPorFecha?matricula=" + v.getMatricula() + "&pais=" + v.getPais() + "&fecha=" + simpleDateFormat.format(new Date()) + "&numeroviaje=" + g.getNumero());
            CloseableHttpResponse hr = hc.execute(hg);
            if (hr.getStatusLine().getStatusCode() == 200) {
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
                    Logger.getLogger(GestionGuiasDeViajeEndpoint.class.getName()).log(Level.INFO, msg);
                }
                guiaDeViajesService.asignarPesajes(g.getNumero(), pesajes);
            } else {
                Logger.getLogger(GestionGuiasDeViajeEndpoint.class.getName()).log(Level.INFO, "No se encontraron pesajes con los parametros ingresados...");
            }
        } catch (Exception e) {
            Logger.getLogger(GestionGuiasDeViajeEndpoint.class.getName()).log(Level.SEVERE, null, e);
        }

        g = guiaDeViajesService.buscarGuiaViajePorNumero(g.getNumero());
        LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        System.out.println(today);
        g.setFin(today);
        guiaDeViajesService.modificarGuiaDeViajeSinAsignacion(g);
        return Response.status(Response.Status.OK).entity(g).build();
    }

    @PUT
    @Path("/iniciar/{cedula}/{numero}")
    public Response iniciarViaje(@PathParam("cedula") String cedulaChofer, @PathParam("numero") int numeroViaje) {
        ChoferDTO c = ciudadanosService.obtenerChofer(cedulaChofer);
        if (c == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe chofer con la cedula " + cedulaChofer).build();
        }

        GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje);
        if (g == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el numero " + numeroViaje).build();
        }

        if (!ciudadanosService.contieneGuiaViajeChofer(c.getCedula(), g.getNumero())) {
            return Response.status(Response.Status.NOT_FOUND).entity("El chofer " + c.getCedula() + " no tiene el viaje con el identificador " + numeroViaje).build();
        }

        if (g.getInicio() != null) {
            return Response.status(Response.Status.CONFLICT).entity("El viaje con el identificador " + g.getNumero() + " ya esta inicializado").build();
        }
        LocalDate today = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        g.setInicio(today);
        guiaDeViajesService.modificarGuiaDeViajeSinAsignacion(g);
        return Response.status(Response.Status.OK).entity(g).build();
    }

    @DELETE
    @Path("/borrar/{idEmpresa}/{idGuia}")
    public Response borrarGuia(@PathParam("idGuia") int idGuia, @PathParam("idEmpresa") int idEmpresa) {
        try {
            GuiaDeViajeDTO g = guiaDeViajesService.buscarGuiaViajePorId(idGuia);
            if (g != null) {
                System.out.println("Guia id: " + g.getId());
                EmpresaDTO e = empresasService.obtenerEmpresa(idEmpresa);
                if (e != null) {
                    System.out.println("Empresa id: " + e.getId());
                    guiaDeViajesService.borrarGuiaDeViaje(g.getId(), e.getId());
                    return Response.status(Response.Status.OK).build();
                } else {
                    return Response.status(Response.Status.NOT_FOUND).entity("No existe empresa con el numero " + idEmpresa).build();
                }
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No existe guia con el id " + idGuia).build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Vaya uno a saber que mierda le pinto").build();
        }

    }

}
