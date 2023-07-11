package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Incubating;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.ChoferDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.*;
import tse.java.enumerated.RolCiudadano;
import tse.java.model.Ciudadanos;
import tse.java.persistance.IChoferDAO;
import tse.java.persistance.ICiudadanoDAO;
import tse.java.persistance.IFuncionarioDAO;
import tse.java.persistance.IResponsableDAO;

import javax.ejb.EJB;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CiudadanoServiceTest {

    @Mock
    ICiudadanoDAO ciudadanoDAO;
    @Mock
    IFuncionarioDAO funcionarioDAO;
    @Mock
    IChoferDAO choferDAO;
    @Mock
    IResponsableDAO responsableDAO;

    @InjectMocks
    CiudadanoService ciudadanoService;

    @Test
    public void testObtenerCiudadano() {
        int id = 1;
        Ciudadano expectedCiudadano = new Ciudadano();

        when(ciudadanoDAO.buscarCiudadanoPorId(id)).thenReturn(expectedCiudadano);

        Ciudadano actualCiudadano = ciudadanoService.obtenerCiudadano(id);

        assertEquals(expectedCiudadano, actualCiudadano);
    }

    @Test
    public void testObtenerCiudadanoPorCedula() {
        String cedula = "123456789";
        Ciudadano expectedCiudadano = new Ciudadano();

        when(ciudadanoDAO.buscarCiudadanoPorCedula(cedula)).thenReturn(expectedCiudadano);

        Ciudadano actualCiudadano = ciudadanoService.obtenerCiudadanoPorCedula(cedula);

        assertEquals(expectedCiudadano, actualCiudadano);
    }
    @Test
    public void testObtenerCiudadanos() {

        Ciudadanos expectedCiudadanos = new Ciudadanos();

        when(ciudadanoDAO.listarCiudadanos()).thenReturn(expectedCiudadanos.getListaCiudadanos());

        Ciudadanos actualCiudadanos = ciudadanoService.obtenerCiudadanos();

        assertEquals(expectedCiudadanos.getClass(), actualCiudadanos.getClass());
    }

    @Test
    public void testObtenerChofer_WhenChoferExists() {
        String cedulaChofer = "123456789";
        ChoferDTO expectedChofer = new ChoferDTO();

        Ciudadano ciudadano = new Chofer();
        when(ciudadanoDAO.buscarCiudadanoPorCedula(cedulaChofer)).thenReturn(ciudadano);

        ChoferDTO actualChofer = ciudadanoService.obtenerChofer(cedulaChofer);

        assertEquals(expectedChofer.getClass(), actualChofer.getClass());
    }

    @Test
    public void testObtenerChofer_WhenChoferDoesNotExist() {

        String cedulaChofer = "123456789"; // Cédula del chofer

        when(ciudadanoDAO.buscarCiudadanoPorCedula(cedulaChofer)).thenReturn(null);

        ChoferDTO actualChofer = ciudadanoService.obtenerChofer(cedulaChofer);

        assertNull(actualChofer);
    }

    @Test
    public void testAgregarCiudadano() {
        // Arrange
        Ciudadano ciudadano = new Ciudadano();
        // Configurar ciudadano con datos necesarios

        // Act
        ciudadanoService.agregarCiudadano(ciudadano);

        // Assert
        Mockito.verify(ciudadanoDAO).agregarCiudadano(ciudadano);
    }

    @Test
    public void testModificarCiudadano() {
        // Arrange
        Ciudadano ciudadano = new Ciudadano();
        // Configurar ciudadano con datos necesarios

        // Act
        ciudadanoService.modificarCiudadano(ciudadano);

        // Assert
        Mockito.verify(ciudadanoDAO).modificarCiudadano(ciudadano);
    }

    @Test
    public void testEliminarCiudadano() {
        // Arrange
        int id = 1; // ID del ciudadano

        // Act
        ciudadanoService.eliminarCiudadano(id);

        // Assert
        Mockito.verify(ciudadanoDAO).eliminiarCiudadano(id);
    }


    @Test
    public void testAgregarHijoCiudadano_WhenFuncionario() {

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.FUNCIONARIO);
        ciudadano.setEmail("test@example.com");
        ciudadano.setCedula("123456789");

        Funcionario funcionario = new Funcionario(ciudadano.getEmail(), ciudadano.getCedula());

        ciudadanoService.agregarHijoCiudadano(funcionario);

        Mockito.verify(funcionarioDAO).agregarFuncionario(any(Funcionario.class));
        Mockito.verifyNoInteractions(responsableDAO, choferDAO);
    }

    @Test
    public void testAgregarHijoCiudadano_WhenResponsable() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.RESPONSABLE);
        ciudadano.setEmail("test@example.com");
        ciudadano.setCedula("123456789");

        Responsable responsable = new Responsable(ciudadano.getEmail(), ciudadano.getCedula());

        ciudadanoService.agregarHijoCiudadano(ciudadano);

        Mockito.verify(responsableDAO).agregarResponsable(any(Responsable.class));
        Mockito.verifyNoInteractions(funcionarioDAO, choferDAO);
    }

    @Test
    public void testAgregarHijoCiudadano_WhenChofer() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.CHOFER);
        ciudadano.setEmail("test@example.com");
        ciudadano.setCedula("123456789");

        Chofer chofer = new Chofer(ciudadano.getEmail(), ciudadano.getCedula());

        ciudadanoService.agregarHijoCiudadano(ciudadano);

        Mockito.verify(choferDAO).agregarChofer(any(Chofer.class));
        Mockito.verifyNoInteractions(funcionarioDAO, responsableDAO);
    }


    @Test
    public void testModificarHijoCiudadano_WhenFuncionario() {
        Ciudadano ciudadano = new Funcionario();

        ciudadanoService.modificarHijoCiudadano(ciudadano);

        Mockito.verify(funcionarioDAO).modificarFuncionario((Funcionario) ciudadano);
        Mockito.verifyNoInteractions(responsableDAO, choferDAO);
    }

    @Test
    public void testModificarHijoCiudadano_WhenResponsable() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.RESPONSABLE);

        Responsable responsable = new Responsable(ciudadano.getEmail(), ciudadano.getCedula());

        ciudadanoService.modificarHijoCiudadano(responsable);

        Mockito.verify(responsableDAO).modificarResponsable(responsable);
        Mockito.verifyNoInteractions(funcionarioDAO, choferDAO);
    }

    @Test
    public void testModificarHijoCiudadano_WhenChofer() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.CHOFER);

        Chofer chofer = new Chofer(ciudadano.getEmail(), ciudadano.getCedula());

        ciudadanoService.modificarHijoCiudadano(chofer);

        Mockito.verify(choferDAO).modificarChofer(chofer);
        Mockito.verifyNoInteractions(funcionarioDAO, responsableDAO);
    }

    @Test
    public void testEliminarHijoCiudadano_WhenFuncionario() {

        int id = 1;
        Ciudadano ciudadano = new Funcionario();

        Funcionario funcionario = new Funcionario(ciudadano.getEmail(), ciudadano.getCedula());
        funcionario.setRol(RolCiudadano.RESPONSABLE);
        funcionario.setIdCiudadano(id);
        when(ciudadanoDAO.buscarCiudadanoPorId(id)).thenReturn(funcionario);

        ciudadanoService.eliminarHijoCiudadano(id);

        Mockito.verify(funcionarioDAO).eliminiarFuncionario(any(Funcionario.class));
        Mockito.verifyNoInteractions(responsableDAO, choferDAO);
    }

    @Test
    public void testEliminarHijoCiudadano_WhenResponsable() {
        int id = 1; // ID del ciudadano
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.RESPONSABLE);

        Responsable responsable = new Responsable(ciudadano.getEmail(), ciudadano.getCedula());
        responsable.setRol(RolCiudadano.RESPONSABLE);
        responsable.setIdCiudadano(id);
        when(ciudadanoDAO.buscarCiudadanoPorId(id)).thenReturn(responsable);

        ciudadanoService.eliminarHijoCiudadano(id);

        Mockito.verify(responsableDAO).eliminiarResponsable(any(Responsable.class));
        Mockito.verifyNoInteractions(funcionarioDAO, choferDAO);
    }

    @Test
    public void testEliminarHijoCiudadano_WhenChofer() {
        int id = 1;
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setRol(RolCiudadano.CHOFER);
        ciudadano.setIdCiudadano(1);

        Chofer chofer = new Chofer(ciudadano.getEmail(), ciudadano.getCedula());
        chofer.setRol(RolCiudadano.CHOFER);
        chofer.setIdCiudadano(id);
        when(ciudadanoDAO.buscarCiudadanoPorId(id)).thenReturn(chofer);



        ciudadanoService.eliminarHijoCiudadano(id);

        Mockito.verify(choferDAO).eliminiarChofer(any(Chofer.class));
        Mockito.verifyNoInteractions(funcionarioDAO, responsableDAO);
    }

    @Test
    public void testAsignarEmpresa() {
        int responsableId = 1;
        Empresa empresa = new Empresa();

        ciudadanoService.asignarEmpresa(responsableId, empresa);

        Mockito.verify(responsableDAO).asignarEmpresa(responsableId, empresa);
    }

    @Test
    public void testEliminarEmpresa() {
        int responsableId = 1;
        Empresa empresa = new Empresa();

        ciudadanoService.eliminarEmpresa(responsableId, empresa);

        Mockito.verify(responsableDAO).eliminarEmpresa(responsableId, empresa);
    }

    @Test
    public void testAsignarEmpresaChofer() {
        int choferId = 1;
        Empresa empresa = new Empresa();

        ciudadanoService.asignarEmpresaChofer(choferId, empresa);


        Mockito.verify(choferDAO).asignarEmpresaChofer(choferId, empresa);
    }

    @Test
    public void testEliminarEmpresaChofer() {
        int choferId = 1;
        Empresa empresa = new Empresa();

        ciudadanoService.eliminarEmpresaChofer(choferId, empresa);

        Mockito.verify(choferDAO).eliminarEmpresaChofer(choferId, empresa);
    }

    @Test
    public void testAsignarViajeChofer() {
        int choferId = 1;
        Asignacion asignacion = new Asignacion();

        Chofer chofer = new Chofer();

        when(ciudadanoDAO.buscarCiudadanoPorId(choferId)).thenReturn(chofer);

        ciudadanoService.asingarViajeChofer(choferId, asignacion);

        Mockito.verify(choferDAO).modificarChofer(chofer);
    }

    @Test
    public void testContieneGuiaViajeChofer_WhenGuiaExists() {
        String cedulaChofer = "123456789";
        int numeroViaje = 1;
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        GuiaDeViajeDTO guiaDTO = new GuiaDeViajeDTO();
        guiaDTO.setNumero(numeroViaje);
        asignacionDTO.setGuia(guiaDTO);

        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.getAsignaciones().add(asignacionDTO);

        when(choferDAO.buscarChoferPorCedula(cedulaChofer)).thenReturn(choferDTO);

        boolean result = ciudadanoService.contieneGuiaViajeChofer(cedulaChofer, numeroViaje);

        assertTrue(result);
    }

    @Test
    public void testContieneGuiaViajeChofer_WhenGuiaDoesNotExist() {
        String cedulaChofer = "123456789";
        int numeroViaje = 1;
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        GuiaDeViajeDTO guiaDTO = new GuiaDeViajeDTO();
        guiaDTO.setNumero(numeroViaje + 1);
        asignacionDTO.setGuia(guiaDTO);

        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.getAsignaciones().add(asignacionDTO);

        when(choferDAO.buscarChoferPorCedula(cedulaChofer)).thenReturn(choferDTO);

        boolean result = ciudadanoService.contieneGuiaViajeChofer(cedulaChofer, numeroViaje);

        assertFalse(result);
    }

    @Test
    public void testObtenerEmpresaPorResponsable() {
        String cedulaResponsable = "123456789";
        EmpresaDTO empresaDTO = new EmpresaDTO();

        when(ciudadanoDAO.obtenerEmpresaPorResponsable(cedulaResponsable)).thenReturn(empresaDTO);

        EmpresaDTO result = ciudadanoService.obtenerEmpresaPorResponsable(cedulaResponsable);

        assertEquals(empresaDTO, result);
    }
}