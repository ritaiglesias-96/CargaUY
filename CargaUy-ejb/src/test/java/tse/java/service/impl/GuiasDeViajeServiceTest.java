package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.*;
import tse.java.persistance.IAsignacionDAO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IPesajesDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.IPesajesService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class GuiasDeViajeServiceTest {
    @InjectMocks
    private GuiasDeViajeService guiasDeViajeService;

    @Mock
    private IGuiaDeViajeDAO guiaviajeDao;

    @Mock
    private IPesajesService pesajesService;

    @Mock
    private IPesajesDAO pesajesDAO;

    @Mock
    private IAsignacionesService asignacionesService;

    @Mock
    private IAsignacionDAO asignacionDAO;

    @Test
    public void testCrearGuiaDeViaje() {
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        ChoferDTO chofer = new ChoferDTO();
        EmpresaDTO empresa = new EmpresaDTO();
        VehiculoDTO vehiculo = new VehiculoDTO();

        guiasDeViajeService.crearGuiaDeViaje(guia, chofer, empresa, vehiculo);

        Mockito.verify(guiaviajeDao).altaGuiaDeViaje(guia, chofer, empresa, vehiculo);
    }

    @Test
    public void testBorrarGuiaDeViaje() {
        int id = 1;
        int idEmpresa = 2;

        guiasDeViajeService.borrarGuiaDeViaje(id, idEmpresa);

        Mockito.verify(guiaviajeDao).borrarGuiaDeViaje(id, idEmpresa);
    }

    @Test
    public void testModificarGuiaDeViaje() {
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        ChoferDTO chofer = new ChoferDTO();
        EmpresaDTO empresa = new EmpresaDTO();
        VehiculoDTO vehiculo = new VehiculoDTO();

        guiasDeViajeService.modificarGuiaDeViaje(guia, chofer, empresa, vehiculo);

        Mockito.verify(guiaviajeDao).modificarGuiaDeViaje(guia, chofer, empresa, vehiculo);
    }

    @Test
    public void testModificarGuiaDeViajeSinAsignacion() {
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();

        guiasDeViajeService.modificarGuiaDeViajeSinAsignacion(guia);

        Mockito.verify(guiaviajeDao).modificarGuiaDeViajeSinAsignacion(guia);
    }

    @Test
    public void testListarGuiasDeViajes() {
        List<GuiaDeViajeDTO> expected = new ArrayList<>();

        when(guiaviajeDao.listarGuiasDeViaje()).thenReturn(expected);

        List<GuiaDeViajeDTO> result = guiasDeViajeService.listarGuiasDeViajes();

        assertEquals(expected, result);
    }

    @Test
    public void testListarGuiasDeViajesPorFecha() {
        List<GuiaDeViajeDTO> guiasViaje = new ArrayList<>();
        LocalDate fecha = LocalDate.now();
        List<PesajeDTO> expected = new ArrayList<>();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        guiaDeViajeDTO.setInicio(new Date(2000).toLocalDate());
        guiasViaje.add(guiaDeViajeDTO);
        List<PesajeDTO> result = guiasDeViajeService.listarGuiasDeViajesPorFecha(guiasViaje, fecha);

        assertEquals(expected, result);
    }

    @Test
    public void testListarGuiasDeViajesPorFechaReturnNewArray() {
        List<GuiaDeViajeDTO> guiasViaje = new ArrayList<>();
        LocalDate fecha = new Date(2000).toLocalDate();
        List<PesajeDTO> expected = new ArrayList<>();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        guiaDeViajeDTO.setInicio(LocalDate.now());
        guiasViaje.add(guiaDeViajeDTO);
        List<PesajeDTO> result = guiasDeViajeService.listarGuiasDeViajesPorFecha(guiasViaje, fecha);

        assertEquals(expected, result);
    }

    @Test
    public void testAsignarPesajes() {
        int numeroViaje = 1;
        List<PesajeDTO> pesajes = new ArrayList<>();
        AsignacionDTO asignacion = new AsignacionDTO();
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        asignacion.setGuia(guia);
        PesajeDTO pesajeDTO = new PesajeDTO();

        pesajes.add(pesajeDTO);

        when(asignacionDAO.buscarAsignacion(anyInt())).thenReturn(asignacion);

        guiasDeViajeService.asignarPesajes(numeroViaje, pesajes);

        Mockito.verify(pesajesDAO, times(pesajes.size())).altaPesaje(any(PesajeDTO.class));
        Mockito.verify(pesajesDAO, times(pesajes.size())).buscarPesaje(anyLong());
        Mockito.verify(guiaviajeDao).modificarGuiaDeViajeSinAsignacion(guia);
    }

    @Test
    public void testCantidadViajesPorAnioRubro() {
        int anio = 2023;
        String rubro = "Transporte";
        int expected = 5;

        when(guiaviajeDao.cantidadViajesPorAnioRubro(anio, rubro)).thenReturn(expected);

        int result = guiasDeViajeService.cantidadViajesPorAnioRubro(anio, rubro);

        assertEquals(expected, result);
    }

    @Test
    public void testGetNextNumeroViaje() {
        int expected = 10;

        when(guiaviajeDao.getNextNumeroViaje()).thenReturn(expected);

        int result = guiasDeViajeService.getNextNumeroViaje();

        assertEquals(expected, result);
    }

    @Test
    public void testBuscarGuiaViajePorNumero() {
        int numeroGuia = 1;
        GuiaDeViajeDTO expected = new GuiaDeViajeDTO();

        when(guiaviajeDao.buscarGuiaViajePorNumero(numeroGuia)).thenReturn(expected);

        GuiaDeViajeDTO result = guiasDeViajeService.buscarGuiaViajePorNumero(numeroGuia);

        assertEquals(expected, result);
    }

    @Test
    public void testBuscarGuiaViajePorId() {
        int numeroGuia = 1;
        GuiaDeViajeDTO expected = new GuiaDeViajeDTO();

        when(guiaviajeDao.buscarGuiaViajePorId(numeroGuia)).thenReturn(expected);

        GuiaDeViajeDTO result = guiasDeViajeService.buscarGuiaViajePorId(numeroGuia);

        assertEquals(expected, result);
    }
}