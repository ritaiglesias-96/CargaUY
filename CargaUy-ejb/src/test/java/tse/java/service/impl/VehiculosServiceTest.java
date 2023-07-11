package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Asignacion;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Vehiculo;
import tse.java.exception.VehiuloException;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.IGuiaDeViajesService;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class VehiculosServiceTest {

    @InjectMocks
    private VehiculosService vehiculosService;

    @Mock
    private IVehiculosDAO vehiculosDAO;

    @Mock
    private IGuiaDeViajesService guiasDeViajeService;

    @Mock
    private IAsignacionesService asignacionService;

    @Test
    public void testObtenerVehiculos() {
        ArrayList<VehiculoDTO> expected = new ArrayList<>();

        when(vehiculosDAO.obtenerVehiculos()).thenReturn(expected);

        List<VehiculoDTO> result = vehiculosService.obtenerVehiculos();

        assertEquals(expected, result);
    }

    @Test
    public void testAgregarVehiculo() {
        VehiculoDTO nuevoVehiculo = new VehiculoDTO();

        vehiculosService.agregarVehiculo(nuevoVehiculo);

        Mockito.verify(vehiculosDAO).agregarVehiculo(nuevoVehiculo);
    }

    @Test
    public void testModificarVehiculo() {
        VehiculoDTO vehiculoModificado = new VehiculoDTO();

        vehiculosService.modificarVehiculo(vehiculoModificado);

        Mockito.verify(vehiculosDAO).modificarVehiculo(vehiculoModificado);
    }

    @Test
    public void testEliminarVehiculo() {
        int id = 1;

        vehiculosService.eliminarVehiculo(id);

        verify(vehiculosDAO).eliminarVehiculo(id);
    }

    @Test
    public void testObtenerVehiculoMatriculaPais() {
        String matricula = "ABC123";
        String pais = "Argentina";
        VehiculoDTO expected = new VehiculoDTO();

        when(vehiculosDAO.obtenerVehiculoMatriculaPais(matricula, pais)).thenReturn(expected);

        VehiculoDTO result = vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais);

        assertEquals(expected, result);
    }

    @Test
    public void testListarGuiasDeVehiculo() {
        int id = 1;
        LocalDate fecha = LocalDate.now();
        List<PesajeDTO> expected = new ArrayList<>();
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setFecha(new Date(2000));
        Asignacion asignacion = new Asignacion();
        asignacion.setId(1);
        asignacion.setGuia(guiaDeViaje);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setFechaFinITV(new Date(2000));
        vehiculo.setFechaFinPNC(new Date(2000));
        vehiculo.setFechaInicioPNC(new Date(2000));
        vehiculo.setAsignaciones(List.of(asignacion));

        when(vehiculosDAO.obtenerVehiculoId(id)).thenReturn(vehiculo);
        when(guiasDeViajeService.listarGuiasDeViajesPorFecha(anyList(), any(LocalDate.class))).thenReturn(expected);

        List<PesajeDTO> result = vehiculosService.listarGuiasDeVehiculo(id, fecha);

        assertEquals(expected, result);
    }

    @Test
    public void testViajeContieneGuia_WhenLastAsignacion() {
        VehiculoDTO vehiculo = new VehiculoDTO();
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        AsignacionDTO asignacion = new AsignacionDTO();
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 11, 15, 30, 0);
        asignacion.setId(1);
        asignacion.setGuia(guia);
        asignacion.setFechaCambio(dateTime);
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion);
        vehiculo.setAsignaciones(asignaciones);

        when(asignacionService.ultimaAsignacionViaje(guia.getNumero())).thenReturn(1);

        boolean result = vehiculosService.viajeContieneGuia(vehiculo, guia);

        assertTrue(result);
    }

    @Test
    public void testViajeContieneGuia_WhenNotLastAsignacion() {

        VehiculoDTO vehiculo = new VehiculoDTO();
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        AsignacionDTO asignacion = new AsignacionDTO();
        asignacion.setId(1);
        asignacion.setGuia(guia);
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion);
        vehiculo.setAsignaciones(asignaciones);

        when(asignacionService.ultimaAsignacionViaje(guia.getNumero())).thenReturn(2);

        boolean result = vehiculosService.viajeContieneGuia(vehiculo, guia);

        assertFalse(result);
    }

    @Test
    public void testViajeContieneGuia_WhenNoMatchingAsignacion() {
        VehiculoDTO vehiculo = new VehiculoDTO();
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        AsignacionDTO asignacion = new AsignacionDTO();
        asignacion.setId(2);
        asignacion.setGuia(guia);
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion);
        vehiculo.setAsignaciones(asignaciones);

        when(asignacionService.ultimaAsignacionViaje(guia.getNumero())).thenReturn(1);

        boolean result = vehiculosService.viajeContieneGuia(vehiculo, guia);

        assertFalse(result);
    }

    @Test
    public void testObtenerVehiculoPorId_WhenVehiculoExists() throws VehiuloException {

        int id = 1;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(id);
        vehiculo.setFechaFinITV(new Date(2000));
        vehiculo.setFechaFinPNC(new Date(2000));
        vehiculo.setFechaInicioPNC(new Date(2000));

        when(vehiculosDAO.obtenerVehiculoId(id)).thenReturn(vehiculo);

        VehiculoDTO result = vehiculosService.obtenerVehiculoPorId(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testObtenerVehiculoPorId_WhenVehiculoDoesNotExist() {
        int id = 1;

        when(vehiculosDAO.obtenerVehiculoId(id)).thenReturn(null);

        assertThrows(VehiuloException.class, () -> {
            vehiculosService.obtenerVehiculoPorId(id);
        });
    }

    @Test
    public void testBuscarVehiculoPorGuia_WhenMatchingAsignacion() {
        // Arrange
        int numero = 1;
        VehiculoDTO expected = new VehiculoDTO();
        AsignacionDTO asignacion = new AsignacionDTO();
        asignacion.setId(asignacionService.ultimaAsignacionViaje(numero));
        expected.setAsignaciones(List.of(asignacion));
        ArrayList<VehiculoDTO> vehiculos = new ArrayList<>();
        vehiculos.add(expected);

        when(vehiculosDAO.obtenerVehiculos()).thenReturn(vehiculos);

        VehiculoDTO result = vehiculosService.buscarVehiculoPorGuia(numero);

        assertEquals(expected, result);
    }

    @Test
    public void testBuscarVehiculoPorGuia_WhenNoMatchingAsignacion() {
        int numero = 1;
        AsignacionDTO asignacion = new AsignacionDTO();
        asignacion.setId(asignacionService.ultimaAsignacionViaje(numero));

        ArrayList<VehiculoDTO> vehiculos = new ArrayList<>();

        when(vehiculosDAO.obtenerVehiculos()).thenReturn(vehiculos);

        VehiculoDTO result = vehiculosService.buscarVehiculoPorGuia(numero);

        assertNull(result);
    }

}