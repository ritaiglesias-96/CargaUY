package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.*;
import tse.java.entity.Empresa;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.IVehiculosService;
import tse.java.soappdi.EmpresaServicePort;
import tse.java.soappdi.EmpresaServicePortService;

import javax.ejb.EJB;

import java.lang.reflect.Array;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmpresasServiceTest {
    @Mock
    IEmpresasDAO empresasDAO;

    @Mock
    IVehiculosService vehiculosService;
    @Mock
    IAsignacionesService asignacionService;

    @InjectMocks
    EmpresasService empresasService;

    @Test
    public void testObtenerEmpresas() {
        ArrayList<EmpresaDTO> empresas = new ArrayList<>();
        EmpresaDTO empresa = new EmpresaDTO();
        empresas.add(empresa);

        when(empresasDAO.obtenerEmpresas()).thenReturn(empresas);

        ArrayList<EmpresaDTO> empresaDTOSResults = empresasService.obtenerEmpresas();

        assertEquals(empresas.size(), empresaDTOSResults.size());
    }

    @Test
    public void testObtenerEmpresaPorGuia(){
        int numeroGuia = 1;
        ArrayList<EmpresaDTO> empresas = new ArrayList<>();
        ArrayList<AsignacionDTO> asignaciones = new ArrayList<>();
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        asignacionDTO.setId(1);
        asignaciones.add(asignacionDTO);
        EmpresaDTO empresa = new EmpresaDTO();
        empresa.setId(1);
        empresa.setAsignaciones(asignaciones);
        empresas.add(empresa);

        when(empresasDAO.obtenerEmpresas()).thenReturn(empresas);
        when(asignacionService.ultimaAsignacionViaje(numeroGuia)).thenReturn(asignacionDTO.getId());
        EmpresaDTO empresaRetorno = empresasService.obtenerEmpresaPorGuia(numeroGuia);

        assertEquals(empresa.getId(),empresaRetorno.getId());
    }
    @Test
    public void testObtenerEmpresaPorGuiaReturnNull(){
        int numeroGuia = 1;
        ArrayList<EmpresaDTO> empresas = new ArrayList<>();
        ArrayList<AsignacionDTO> asignaciones = new ArrayList<>();
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        asignacionDTO.setId(1);
        asignaciones.add(asignacionDTO);
        EmpresaDTO empresa = new EmpresaDTO();
        empresa.setId(1);
        empresa.setAsignaciones(asignaciones);
        empresas.add(empresa);

        when(empresasDAO.obtenerEmpresas()).thenReturn(empresas);
        when(asignacionService.ultimaAsignacionViaje(numeroGuia)).thenReturn(4);
        EmpresaDTO empresaRetorno = empresasService.obtenerEmpresaPorGuia(numeroGuia);

        assertNull(empresaRetorno);
    }

    @Test
    public void testObtenerEmpresa(){
        int id = 1;
        EmpresaDTO expectedEmpresa = new EmpresaDTO();

        when(empresasDAO.obtenerEmpresaPorId(id)).thenReturn(expectedEmpresa);

        EmpresaDTO actualEmpresa = empresasService.obtenerEmpresa(id);

        assertEquals(expectedEmpresa, actualEmpresa);
    }

   /* @Test TODO FIX
    public void testAgregarEmpresa() {

        String rut = "your-rut";
        int expectedId = 1; // ID esperado de la empresa agregada

        when(empresasService.crearEmpresaPdi(rut)).thenReturn(expectedId);


        int actualId = empresasService.agregarEmpresa(rut);


        assertEquals(expectedId, actualId);
    }
*/
    @Test
    public void testModificarEmpresa() {
        // Arrange
        EmpresaDTO empresaDTO = new EmpresaDTO();
        // Configurar datos de empresaDTO

        // Act
        empresasService.modificarEmpresa(empresaDTO);

        // Assert
        Mockito.verify(empresasDAO).modificarEmpresa(empresaDTO);
    }

    @Test
    public void testEliminarEmpresa() {
        // Arrange
        int id = 1; // ID de la empresa a eliminar

        // Act
        empresasService.eliminarEmpresa(id);

        // Assert
        Mockito.verify(empresasDAO).eliminarEmpresa(id);
    }

    @Test
    public void testListarGuias() {
        int numeroEmpresa = 1;
        LocalDate date = new Date(2000).toLocalDate();
        List<PesajeDTO> pesajes = new ArrayList<>();
        PesajeDTO pesaje = new PesajeDTO();
        pesajes.add(pesaje);
        String matricula = "asdarqw", pais = "URU";
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(23);
        vehiculos.add(vehiculoDTO);
        empresaDTO.setVehiculos(vehiculos);

        when(empresasDAO.obtenerEmpresaPorNumero(numeroEmpresa)).thenReturn(empresaDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais)).thenReturn(vehiculoDTO);

        when(vehiculosService.listarGuiasDeVehiculo(vehiculoDTO.getId(),date)).thenReturn(pesajes);

        List<PesajeDTO> pesajesResult = empresasService.listarGuias(numeroEmpresa,matricula,pais,date);

        assertEquals(pesajes.size(),pesajesResult.size() );
    }

    @Test
    public void testListarGuiasElse() {
        int numeroEmpresa = 1;
        LocalDate date = new Date(2000).toLocalDate();
        List<PesajeDTO> pesajes = new ArrayList<>();
        PesajeDTO pesaje = new PesajeDTO();
        pesajes.add(pesaje);
        String matricula = "asdarqw", pais = "URU";
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        EmpresaDTO empresaDTO = new EmpresaDTO();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(23);
        vehiculos.add(vehiculoDTO);

        when(empresasDAO.obtenerEmpresaPorNumero(numeroEmpresa)).thenReturn(empresaDTO);
        when(vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais)).thenReturn(vehiculoDTO);


        List<PesajeDTO> pesajesResult = empresasService.listarGuias(numeroEmpresa,matricula,pais,date);

        assertEquals(0, pesajesResult.size() );
    }

    @Test
    public void testAgregarVehiculoAEmpresa() {

        int idEmpresa = 1;
        VehiculoDTO vehiculo = new VehiculoDTO();



        empresasService.agregarVehiculoAEmpresa(idEmpresa, vehiculo);


        Mockito.verify(empresasDAO).agregarVehiculo(idEmpresa, vehiculo);
    }
    @Test
    public void testBorrarVehiculo() {
        // Arrange
        int idEmpresa = 1; // ID de la empresa
        int idVehiculo = 1; // ID del vehiculo


        empresasService.borrarVehiculo(idEmpresa, idVehiculo);


        Mockito.verify(empresasDAO).eliminarVehiculo(idEmpresa, idVehiculo);
    }

    @Test
    public void testListarVehiculos() {

            int id = 1; // ID de la empresa
            EmpresaDTO empresa = new EmpresaDTO();

            List<VehiculoDTO> expectedVehiculos = new ArrayList<>();

            empresa.setVehiculos(expectedVehiculos);

            when(empresasService.obtenerEmpresa(id)).thenReturn(empresa);


            List<VehiculoDTO> actualVehiculos = empresasService.listarVehiculos(id);

            assertEquals(expectedVehiculos, actualVehiculos);

    }
    @Test
    public void testAgregarAsignacionAEmpresa() {
        int idEmpresa = 1; // ID de la empresa
        AsignacionDTO asignacion = new AsignacionDTO();

        EmpresaDTO empresa = new EmpresaDTO();
        List<AsignacionDTO> expectedAsignaciones = new ArrayList<>();
        empresa.setAsignaciones(expectedAsignaciones);

        when(empresasService.obtenerEmpresa(idEmpresa)).thenReturn(empresa);

        empresasService.agregarAsignacionAEmpresa(idEmpresa, asignacion);

        Mockito.verify(empresasDAO).modificarEmpresa(empresa);
    }

    @Test
    public void testContieneChofer_WhenChoferPresent() {
        int choferId = 1;
        EmpresaDTO empresa = new EmpresaDTO();
        List<ChoferDTO> choferes = new ArrayList<>();
        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.setIdCiudadano(choferId);
        choferes.add(choferDTO);
        empresa.setChoferes(choferes);

        // Act
        boolean result = empresasService.contieneChofer(choferId, empresa);

        // Assert
        assertTrue(result);
    }
    @Test
    public void testContieneChofer_WhenChoferNotPresent() {

        int choferId = 1;
        EmpresaDTO empresa = new EmpresaDTO();



        boolean result = empresasService.contieneChofer(choferId, empresa);


        assertFalse(result);
    }

    @Test
    public void testContieneVehiculo_WhenVehiculoPresent() {
        // Arrange
        int vehiculoId = 1; // ID del vehiculo
        EmpresaDTO empresa = new EmpresaDTO();
        // Configurar empresa con vehiculos esperados
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(vehiculoId);
        vehiculos.add(vehiculoDTO);
        empresa.setVehiculos(vehiculos);

        // Act
        boolean result = empresasService.contieneVehiculo(vehiculoId, empresa);

        // Assert
        assertTrue(result);
    }

    @Test
    public void testContieneVehiculo_WhenVehiculoNotPresent() {
        // Arrange
        int vehiculoId = 1; // ID del vehiculo
        EmpresaDTO empresa = new EmpresaDTO();
        // Configurar empresa sin vehiculos

        // Act
        boolean result = empresasService.contieneVehiculo(vehiculoId, empresa);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testListarViajesFinalizados() {
        ArrayList<EmpresaDTO> expectedEmpresas = new ArrayList<>();
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        guiaDeViajeDTO.setFin(new Date(2000).toLocalDate());
        asignacionDTO.setGuia(guiaDeViajeDTO);
        asignaciones.add(asignacionDTO);
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setAsignaciones(asignaciones);
        expectedEmpresas.add(empresaDTO);
        when(empresasDAO.obtenerEmpresas()).thenReturn(expectedEmpresas);


        List<EmpresaDTO> actualEmpresas = empresasService.listarViajesFinalizados();


        assertEquals(expectedEmpresas, actualEmpresas);
    }

    @Test
    public void testListarViajesFinalizadosWithoutAsignaciones() {

        ArrayList<EmpresaDTO> expectedEmpresas = new ArrayList<>();


        when(empresasDAO.obtenerEmpresas()).thenReturn(expectedEmpresas);


        List<EmpresaDTO> actualEmpresas = empresasService.listarViajesFinalizados();


        assertEquals(expectedEmpresas, actualEmpresas);
    }


}