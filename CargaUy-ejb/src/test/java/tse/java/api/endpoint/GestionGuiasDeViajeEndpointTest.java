package tse.java.api.endpoint;



import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.*;
import tse.java.entity.Administrador;
import tse.java.entity.GuiaDeViaje;
import tse.java.service.*;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class GestionGuiasDeViajeEndpointTest {

    @Mock
    private IGuiaDeViajesService guiaDeViajesService;

    @Mock
    private IVehiculosService vehiculosService;

    @Mock
    private ICiudadanosService ciudadanosService;

    @Mock
    private IAsignacionesService asignacionesService;

    @Mock
    private IEmpresasService empresasService;


    @InjectMocks
    private GestionGuiasDeViajeEndpoint gestionGuiasDeViajeEndpoint;

    @Test
    public void testListarViajesAsignadosNoResults() {
        String cedula = "abcd" , paisMatricula = "URU", matricula = "AXC123";
        ChoferDTO choferDTO = new ChoferDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(choferDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisMatricula)).thenReturn(vehiculoDTO);

        Response response = gestionGuiasDeViajeEndpoint.listarViajesAsignados(cedula, paisMatricula, matricula);

        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testListarViajesAsignadosChoferNotFound() {
        String cedula = "abcd" , paisMatricula = "URU", matricula = "AXC123";
        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.listarViajesAsignados(cedula, paisMatricula, matricula);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testListarViajesAsignadosVehiculoNotFound() {
        String cedula = "abcd" , paisMatricula = "URU", matricula = "AXC123";
        ChoferDTO choferDTO = new ChoferDTO();
        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(choferDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisMatricula)).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.listarViajesAsignados(cedula, paisMatricula, matricula);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testListarViajesAsignadosWithResults() {
        String cedula = "abcd", paisMatricula = "URU", matricula = "AXC123";
        ChoferDTO choferDTO = new ChoferDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        int guiaNumero = 1;
        int ultimaAsignacionViaje = 3;
        AsignacionDTO asignacion = new AsignacionDTO();
        asignacion.setGuia(guiaDeViajeDTO);
        asignacion.getGuia().setNumero(guiaNumero);
        asignacion.getGuia().setFin(null);
        asignacion.setId(ultimaAsignacionViaje);
        choferDTO.getAsignaciones().add(asignacion);
        vehiculoDTO.setAsignaciones(List.of(asignacion));

        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(choferDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula, paisMatricula)).thenReturn(vehiculoDTO);
        when(asignacionesService.ultimaAsignacionViaje(guiaNumero)).thenReturn(ultimaAsignacionViaje);
        when(vehiculosService.viajeContieneGuia(vehiculoDTO,guiaDeViajeDTO)).thenReturn(true);


        Response response = gestionGuiasDeViajeEndpoint.listarViajesAsignados(cedula, paisMatricula, matricula);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        // Verificar cualquier otra aserción adicional que necesites para la respuesta
    }

    @Test
    public void testlistarGuiasDeEmpresa() {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        int id = 1891;
        empresaDTO.setId(id);
        when(empresasService.obtenerEmpresa(id)).thenReturn(empresaDTO);

        Response response = gestionGuiasDeViajeEndpoint.listarGuiasDeEmpresa(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testListarGuiasDeEmpresa2() {
        int idEmpresa = 1;
        EmpresaDTO empresaDTO = new EmpresaDTO();
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        AsignacionDTO asignacion1 = new AsignacionDTO();
        asignacion1.setGuia(new GuiaDeViajeDTO());
        asignacion1.getGuia().setId(1);
        AsignacionDTO asignacion2 = new AsignacionDTO();
        asignacion2.setGuia(new GuiaDeViajeDTO());
        asignacion2.getGuia().setId(2);
        asignaciones.add(asignacion1);
        asignaciones.add(asignacion2);
        empresaDTO.setAsignaciones(asignaciones);

        when(empresasService.obtenerEmpresa(idEmpresa)).thenReturn(empresaDTO);


        Response response = gestionGuiasDeViajeEndpoint.listarGuiasDeEmpresa(idEmpresa);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void testListarViajesChofer() {
        ChoferDTO choferDTO = new ChoferDTO();
        String cedula = "235665";
        choferDTO.setCedula(cedula);
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        AsignacionDTO asignacion1 = new AsignacionDTO();
        asignacion1.setGuia(new GuiaDeViajeDTO());
        asignacion1.getGuia().setId(1);
        AsignacionDTO asignacion2 = new AsignacionDTO();
        asignacion2.setGuia(new GuiaDeViajeDTO());
        asignacion2.getGuia().setId(2);
        asignaciones.add(asignacion1);
        asignaciones.add(asignacion2);
        choferDTO.setAsignaciones(asignaciones);

        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(choferDTO);

        Response response = gestionGuiasDeViajeEndpoint.listarViajesChofer(cedula);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }
    @Test
    public void testListarViajesChoferChoferNotFound() {
        ChoferDTO choferDTO = new ChoferDTO();
        String cedula = "235665";
        choferDTO.setCedula(cedula);

        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.listarViajesChofer(cedula);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

    }

    @Test
    public void testListarViajesChoferWithoutViajes() {
        ChoferDTO choferDTO = new ChoferDTO();
        String cedula = "235665";
        choferDTO.setCedula(cedula);

        when(ciudadanosService.obtenerChofer(cedula)).thenReturn(choferDTO);

        Response response = gestionGuiasDeViajeEndpoint.listarViajesChofer(cedula);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void testCrearGuiaDeViaje() {
      GuiaDeViajeAltaDTO guiaDeViajeModificacionDTO = new GuiaDeViajeAltaDTO();
      GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
      VehiculoDTO vehiculoDTO = new VehiculoDTO();
      vehiculoDTO.setId(2);
      EmpresaDTO empresaDTO = new EmpresaDTO();
      empresaDTO.setVehiculos(List.of(vehiculoDTO));
      ChoferDTO choferDTO = new ChoferDTO();
      choferDTO.setIdCiudadano(4);
      int numeroViaje = 3, idEmpresa = 1891, id = 1;
      String matricula = "AX13", paisVehiculo = "URU", cedulaChofer = "587498";
      guiaDeViajeModificacionDTO.setMatriculaVehiculo(matricula);
      guiaDeViajeModificacionDTO.setPaisVehiculo(paisVehiculo);
      guiaDeViajeModificacionDTO.setIdEmpresa(idEmpresa);
      guiaDeViajeModificacionDTO.setCedulaChofer(cedulaChofer);
      guiaDeViajeModificacionDTO.setDestino("destino");
      //when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
      when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisVehiculo)).thenReturn(vehiculoDTO);
      when(empresasService.obtenerEmpresa(guiaDeViajeModificacionDTO.getIdEmpresa())).thenReturn(empresaDTO);
      when(ciudadanosService.obtenerChofer(guiaDeViajeModificacionDTO.getCedulaChofer())).thenReturn(choferDTO);

      when(empresasService.contieneChofer(choferDTO.getIdCiudadano(),empresaDTO)).thenReturn(true);
      when(empresasService.contieneVehiculo(vehiculoDTO.getId(), empresaDTO)).thenReturn(true);

      Response response = gestionGuiasDeViajeEndpoint.crearGuiaDeViaje(guiaDeViajeModificacionDTO);
      System.out.println(response.getEntity());
      assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
  }
    @Test
    public void testCrearGuiaDeViajeChoferNotFound() {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        GuiaDeViajeAltaDTO guiaDeViajeAltaDTO = new GuiaDeViajeAltaDTO();

        String cedula = "abcd" , paisMatricula = "URU", matricula = "AXC123";
        int id = 1891, numero = 3;
        empresaDTO.setId(id);
        guiaDeViajeAltaDTO.setIdEmpresa(id);
        guiaDeViajeAltaDTO.setIdEmpresa(id);
        guiaDeViajeAltaDTO.setCedulaChofer(cedula);
        guiaDeViajeAltaDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeAltaDTO.setPaisVehiculo(paisMatricula);

        when(empresasService.obtenerEmpresa((guiaDeViajeAltaDTO.getIdEmpresa()))).thenReturn(empresaDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(guiaDeViajeAltaDTO.getMatriculaVehiculo(),guiaDeViajeAltaDTO.getPaisVehiculo())).thenReturn(vehiculoDTO);
        when(ciudadanosService.obtenerChofer(guiaDeViajeAltaDTO.getCedulaChofer())).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.crearGuiaDeViaje(guiaDeViajeAltaDTO);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCrearGuiaDeViajeVehiculoNotFound() {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        GuiaDeViajeAltaDTO guiaDeViajeAltaDTO = new GuiaDeViajeAltaDTO();

        String cedula = "abcd" , paisMatricula = "URU", matricula = "AXC123";
        int id = 1891, numero = 3;
        empresaDTO.setId(id);
        guiaDeViajeAltaDTO.setIdEmpresa(id);
        guiaDeViajeAltaDTO.setIdEmpresa(id);
        guiaDeViajeAltaDTO.setCedulaChofer(cedula);
        guiaDeViajeAltaDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeAltaDTO.setPaisVehiculo(paisMatricula);

        when(empresasService.obtenerEmpresa((guiaDeViajeAltaDTO.getIdEmpresa()))).thenReturn(empresaDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(guiaDeViajeAltaDTO.getMatriculaVehiculo(),guiaDeViajeAltaDTO.getPaisVehiculo())).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.crearGuiaDeViaje(guiaDeViajeAltaDTO);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCrearGuiaDeViajeEmpresaNotFound() {
        GuiaDeViajeAltaDTO guiaDeViajeAltaDTO = new GuiaDeViajeAltaDTO();
        guiaDeViajeAltaDTO.setIdEmpresa(2);

        Response response = gestionGuiasDeViajeEndpoint.crearGuiaDeViaje(guiaDeViajeAltaDTO);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testCrearGuiaDeViajeVehiculoThrowError() {
        GuiaDeViajeAltaDTO guiaDeViajeModificacionDTO = new GuiaDeViajeAltaDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(2);
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setVehiculos(List.of(vehiculoDTO));
        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.setIdCiudadano(4);
        int numeroViaje = 3, idEmpresa = 1891, id = 1;
        String matricula = "AX13", paisVehiculo = "URU", cedulaChofer = "587498";
        guiaDeViajeModificacionDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeModificacionDTO.setPaisVehiculo(paisVehiculo);
        guiaDeViajeModificacionDTO.setIdEmpresa(idEmpresa);
        guiaDeViajeModificacionDTO.setCedulaChofer(cedulaChofer);
        guiaDeViajeModificacionDTO.setDestino("destino");
        //when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisVehiculo)).thenReturn(vehiculoDTO);
        when(empresasService.obtenerEmpresa(guiaDeViajeModificacionDTO.getIdEmpresa())).thenReturn(empresaDTO);
        when(ciudadanosService.obtenerChofer(guiaDeViajeModificacionDTO.getCedulaChofer())).thenReturn(choferDTO);


        Response response = gestionGuiasDeViajeEndpoint.crearGuiaDeViaje(guiaDeViajeModificacionDTO);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    @Test
    public void testModificarGuiaDeViaje() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.setIdCiudadano(4);
        int numeroViaje = 3, idEmpresa = 1891, id = 1;
        String matricula = "AX13", paisVehiculo = "URU", cedulaChofer = "587498";
        guiaDeViajeModificacionDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeModificacionDTO.setPaisVehiculo(paisVehiculo);
        guiaDeViajeModificacionDTO.setNumeroViaje(numeroViaje);
        guiaDeViajeModificacionDTO.setIdEmpresa(idEmpresa);
        guiaDeViajeModificacionDTO.setCedulaChofer(cedulaChofer);
        guiaDeViajeModificacionDTO.setDestino("destino");
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisVehiculo)).thenReturn(vehiculoDTO);
        when(empresasService.obtenerEmpresa(guiaDeViajeModificacionDTO.getIdEmpresa())).thenReturn(empresaDTO);
        when(ciudadanosService.obtenerChofer(guiaDeViajeModificacionDTO.getCedulaChofer())).thenReturn(choferDTO);


      doAnswer((Answer<Object>) invocation -> {
          return null;
      }).when(guiaDeViajesService).modificarGuiaDeViaje(guiaDeViajeDTO,choferDTO,empresaDTO,vehiculoDTO);

      Response response = gestionGuiasDeViajeEndpoint.modificarGuiaDeViaje(guiaDeViajeModificacionDTO);
      System.out.println(response.getEntity());
      assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
    }

    @Test
    public void testModificarGuiaDeViajeGuiaNotFound() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();

        int numeroViaje = 3;

        //when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.modificarGuiaDeViaje(guiaDeViajeModificacionDTO);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testModificarGuiaDeViajeVehiculoNotFound() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        int numeroViaje = 3;
        String matricula = "AX13", paisVehiculo = "URU";
        guiaDeViajeModificacionDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeModificacionDTO.setPaisVehiculo(paisVehiculo);
        guiaDeViajeModificacionDTO.setNumeroViaje(numeroViaje);

        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisVehiculo)).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.modificarGuiaDeViaje(guiaDeViajeModificacionDTO);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testModificarGuiaDeViajeEmpresaNotFound() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        int numeroViaje = 3, idEmpresa = 1891, id = 1;
        String matricula = "AX13", paisVehiculo = "URU", cedulaChofer = "587498";
        guiaDeViajeModificacionDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeModificacionDTO.setPaisVehiculo(paisVehiculo);
        guiaDeViajeModificacionDTO.setNumeroViaje(numeroViaje);
        guiaDeViajeModificacionDTO.setIdEmpresa(idEmpresa);

        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisVehiculo)).thenReturn(vehiculoDTO);
        when(empresasService.obtenerEmpresa(guiaDeViajeModificacionDTO.getIdEmpresa())).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.modificarGuiaDeViaje(guiaDeViajeModificacionDTO);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testModificarGuiaDeViajeChoferNotFound() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        int numeroViaje = 3, idEmpresa = 1891, id = 1;
        String matricula = "AX13", paisVehiculo = "URU", cedulaChofer = "587498";
        guiaDeViajeModificacionDTO.setMatriculaVehiculo(matricula);
        guiaDeViajeModificacionDTO.setPaisVehiculo(paisVehiculo);
        guiaDeViajeModificacionDTO.setNumeroViaje(numeroViaje);
        guiaDeViajeModificacionDTO.setIdEmpresa(idEmpresa);
        guiaDeViajeModificacionDTO.setCedulaChofer(cedulaChofer);
        guiaDeViajeModificacionDTO.setDestino("destino");
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula,paisVehiculo)).thenReturn(vehiculoDTO);
        when(empresasService.obtenerEmpresa(guiaDeViajeModificacionDTO.getIdEmpresa())).thenReturn(empresaDTO);
        when(ciudadanosService.obtenerChofer(guiaDeViajeModificacionDTO.getCedulaChofer())).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.modificarGuiaDeViaje(guiaDeViajeModificacionDTO);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

     @Test
       public void finalizarViaje() throws IOException {
         GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
         GuiaDeViajeDTO guiaAuxiliar = new GuiaDeViajeDTO();
         ChoferDTO choferDTO = new ChoferDTO();
         VehiculoDTO vehiculoDTO = new VehiculoDTO();
         PesajeDTO pesajeDTO = new PesajeDTO();
         List<PesajeDTO> pesajes = new ArrayList<>();
         pesajes.add(pesajeDTO);
         int numeroViaje = 3,  id = 1, numAux = 2321;
         String cedulaChofer = "587498";
         AsignacionDTO asignacionDTO = new AsignacionDTO();
         guiaDeViajeDTO.setNumero(numeroViaje);
         asignacionDTO.setGuia(guiaDeViajeDTO);
         List<AsignacionDTO> asignaciones = new ArrayList<>();
         choferDTO.setCedula(cedulaChofer);
         choferDTO.setAsignaciones(asignaciones);
         guiaDeViajeDTO.setId(numAux);
         guiaAuxiliar.setNumero(id);
         when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
         when(guiaDeViajesService.buscarGuiaViajePorNumero(guiaDeViajeDTO.getNumero())).thenReturn(guiaDeViajeDTO).thenReturn(guiaDeViajeDTO);
         when(ciudadanosService.contieneGuiaViajeChofer(choferDTO.getCedula(),guiaDeViajeDTO.getNumero())).thenReturn(true);
         when(vehiculosService.buscarVehiculoPorGuia(guiaDeViajeDTO.getNumero())).thenReturn(vehiculoDTO);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(guiaDeViajesService).modificarGuiaDeViajeSinAsignacion(guiaDeViajeDTO);

        Response response = gestionGuiasDeViajeEndpoint.finalizarViaje(cedulaChofer,numeroViaje);

        System.out.println(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

    }
    @Test
    public void testFinalizarViajeChoferNotFound(){
        int numeroViaje = 3,  id = 1;
        String cedulaChofer = "587498";

        Response response = gestionGuiasDeViajeEndpoint.finalizarViaje(cedulaChofer,numeroViaje);

        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());

    }
    @Test
    public void testFinalizarViajeGuiaNotFound(){
        int numeroViaje = 3,  id = 1;
        String cedulaChofer = "587498";
        ChoferDTO choferDTO = new ChoferDTO();
        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        Response response = gestionGuiasDeViajeEndpoint.finalizarViaje(cedulaChofer,numeroViaje);

        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }
    @Test
    public void testFinalizarViajeViajeAsignadoChoferNotFound(){
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        PesajeDTO pesajeDTO = new PesajeDTO();
        List<PesajeDTO> pesajes = new ArrayList<>();
        pesajes.add(pesajeDTO);
        int numeroViaje = 3,  id = 1;
        String cedulaChofer = "587498";
        guiaDeViajeDTO.setNumero(id);
        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);

        Response response = gestionGuiasDeViajeEndpoint.finalizarViaje(cedulaChofer,numeroViaje);

        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testFinalizarViajeViajeFinalizadoError(){
        int numeroViaje = 3,  id = 1;
        String cedulaChofer = "587498";
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        PesajeDTO pesajeDTO = new PesajeDTO();
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        guiaDeViajeDTO.setNumero(numeroViaje);
        asignacionDTO.setGuia(guiaDeViajeDTO);
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(asignacionDTO);
        List<PesajeDTO> pesajes = new ArrayList<>();
        pesajes.add(pesajeDTO);
        LocalDate date = new Date(2000,8,16).toLocalDate();

        choferDTO.setCedula(cedulaChofer);
        choferDTO.setAsignaciones(asignaciones);
        guiaDeViajeDTO.setNumero(id);
        guiaDeViajeDTO.setFin(date);
        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(ciudadanosService.contieneGuiaViajeChofer(choferDTO.getCedula(),guiaDeViajeDTO.getNumero())).thenReturn(true);

        Response response = gestionGuiasDeViajeEndpoint.finalizarViaje(cedulaChofer,numeroViaje);

        System.out.println(response.getEntity());
        assertEquals(Response.Status.CONFLICT.getStatusCode(),response.getStatus());
    }

    @Test
    public void iniciarViaje() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        int numeroViaje = 3,  id = 1, numero = 1891;
        String cedulaChofer = "587498";
        guiaDeViajeDTO.setNumero(numero);
        guiaDeViajeDTO.setId(4);
        choferDTO.setIdCiudadano(id);
        choferDTO.setCedula(cedulaChofer);
        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(ciudadanosService.contieneGuiaViajeChofer(choferDTO.getCedula(),numero)).thenReturn(true);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                return null;
            }
        }).when(guiaDeViajesService).modificarGuiaDeViajeSinAsignacion(any(GuiaDeViajeDTO.class));
        Response response = gestionGuiasDeViajeEndpoint.iniciarViaje(cedulaChofer,numeroViaje);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void iniciarViajeChoferNotFound() {
        int numeroViaje = 3;
        String cedulaChofer = "587498";
        Response response = gestionGuiasDeViajeEndpoint.iniciarViaje(cedulaChofer,numeroViaje);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void iniciarViajeGuiaNotFound() {
        ChoferDTO choferDTO = new ChoferDTO();
        int numeroViaje = 3,  id = 1, numero = 1891;
        String cedulaChofer = "587498";
        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        Response response = gestionGuiasDeViajeEndpoint.iniciarViaje(cedulaChofer,numeroViaje);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void iniciarViajeError() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        int numeroViaje = 3,  id = 1, numero = 1891;
        String cedulaChofer = "587498";
        guiaDeViajeDTO.setNumero(numero);
        guiaDeViajeDTO.setId(4);
        choferDTO.setIdCiudadano(id);
        choferDTO.setCedula(cedulaChofer);

        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(ciudadanosService.contieneGuiaViajeChofer(choferDTO.getCedula(),numero)).thenReturn(false);

        Response response = gestionGuiasDeViajeEndpoint.iniciarViaje(cedulaChofer,numeroViaje);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    @Test
    public void iniciarViajeErrorYaIniciado() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        ChoferDTO choferDTO = new ChoferDTO();
        int numeroViaje = 3,  id = 1, numero = 1891;
        String cedulaChofer = "587498";
        guiaDeViajeDTO.setNumero(numero);
        guiaDeViajeDTO.setId(4);
        guiaDeViajeDTO.setInicio(new Date(2000).toLocalDate());
        choferDTO.setIdCiudadano(id);
        choferDTO.setCedula(cedulaChofer);
        when(ciudadanosService.obtenerChofer(cedulaChofer)).thenReturn(choferDTO);
        when(guiaDeViajesService.buscarGuiaViajePorNumero(numeroViaje)).thenReturn(guiaDeViajeDTO);
        when(ciudadanosService.contieneGuiaViajeChofer(choferDTO.getCedula(),numero)).thenReturn(true);

        Response response = gestionGuiasDeViajeEndpoint.iniciarViaje(cedulaChofer,numeroViaje);
        System.out.println(response.getEntity());
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
    }


    @Test
    public void borrarGuia() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        int idEmpresa = 3,  id = 1;
        String cedulaChofer = "587498";
        guiaDeViajeDTO.setId(id);
        empresaDTO.setId(idEmpresa);
        when(guiaDeViajesService.buscarGuiaViajePorId(id)).thenReturn(guiaDeViajeDTO);
        when(empresasService.obtenerEmpresa(idEmpresa)).thenReturn(empresaDTO);
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(guiaDeViajesService).borrarGuiaDeViaje(id, idEmpresa);

        Response response = gestionGuiasDeViajeEndpoint.borrarGuia(id, idEmpresa);

        System.out.println(response.getEntity());

        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }
    @Test
    public void borrarGuiaThrowError() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        int idEmpresa = 3,  id = 1;

        when(guiaDeViajesService.buscarGuiaViajePorId(id)).thenReturn(guiaDeViajeDTO);
        when(empresasService.obtenerEmpresa(idEmpresa)).thenReturn(empresaDTO);

        Response response = gestionGuiasDeViajeEndpoint.borrarGuia(id, idEmpresa);
        System.out.println(response.getEntity());

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),response.getStatus());
    }
    @Test
    public void borrarGuiaGuiaNotFound() {
        int idEmpresa = 3,  id = 1;

        Response response = gestionGuiasDeViajeEndpoint.borrarGuia(id, idEmpresa);

        System.out.println(response.getEntity());

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void borrarGuiaEmpresaNotFound() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        int idEmpresa = 3,  id = 1;
        guiaDeViajeDTO.setId(id);
        when(guiaDeViajesService.buscarGuiaViajePorId(id)).thenReturn(guiaDeViajeDTO);
        when(empresasService.obtenerEmpresa(idEmpresa)).thenReturn(null);

        Response response = gestionGuiasDeViajeEndpoint.borrarGuia(id, idEmpresa);

        System.out.println(response.getEntity());

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

}