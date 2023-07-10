package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.ChoferDTO;
import tse.java.enumerated.RolCiudadano;

import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChoferTest {

    @Test
    public void testConstructorWithEmailAndCedula() {
        // Arrange
        String email = "chofer@example.com";
        String cedula = "123456789";
        RolCiudadano rol = RolCiudadano.CHOFER;

        // Act
        Chofer chofer = new Chofer(email, cedula);

        // Assert
        assertEquals(email, chofer.getEmail());
        assertEquals(cedula, chofer.getCedula());
        assertEquals(rol, chofer.getRol());
        assertNotNull(chofer.getAsignaciones());
        assertEquals(0, chofer.getAsignaciones().size());
    }

    @Test
    public void testConstructorWithEmailCedulaAndAsignaciones() {
        // Arrange
        String email = "chofer@example.com";
        String cedula = "123456789";
        List<Asignacion> asignaciones = new ArrayList<>();
        asignaciones.add(new Asignacion());
        asignaciones.add(new Asignacion());

        // Act
        Chofer chofer = new Chofer(email, cedula, asignaciones);

        // Assert
        assertEquals(email, chofer.getEmail());
        assertEquals(cedula, chofer.getCedula());
        assertEquals(RolCiudadano.CHOFER, chofer.getRol());
        assertEquals(asignaciones, chofer.getAsignaciones());
    }
// TODO:FIXME
//    @Test
//    public void testProcesarListaAsignaciones() {
//        // Arrange
//        Chofer chofer = new Chofer();
//        GuiaDeViaje guia = new GuiaDeViaje();
//        chofer.setRol(RolCiudadano.CHOFER);
//
//        // Valores de ejemplo para el constructor
//        int id = 1;
//        LocalDateTime fechaCambio = LocalDateTime.now();
//
//        // Crear una instancia de Asignacion utilizando el constructor
//        Asignacion asignacion = new Asignacion(id, guia, fechaCambio);
//        List<Asignacion> asignaciones = new ArrayList<>();
//        asignaciones.add(asignacion);
//
//        // Act
//        List<AsignacionDTO> result = chofer.procesarListaAsignaciones(asignaciones);
//
//        // Assert
//        assertEquals(asignaciones.size(), result.size());
//        for (int i = 0; i < asignaciones.size(); i++) {
//            Asignacion asignacion2 = asignaciones.get(i);
//            AsignacionDTO asignacionDTO = result.get(i);
//            assertEquals(asignacion2.getId(), asignacionDTO.getId());
//            assertEquals(RolCiudadano.CHOFER, chofer.getRol());
//
//        }
//    }
    @Test
    public void testSetAsignaciones() {
        // Arrange
        Chofer chofer = new Chofer();
        List<Asignacion> asignaciones = new ArrayList<>();
        asignaciones.add(new Asignacion());
        asignaciones.add(new Asignacion());

        // Act
        chofer.setAsignaciones(asignaciones);

        // Assert
        assertEquals(asignaciones, chofer.getAsignaciones());
    }

//    @Test
//    public void testGetSetEmpresas() {
//        // Arrange
//        Chofer chofer = new Chofer();
//        List<Empresa> empresas = new ArrayList<>();
//        empresas.add(new Empresa());
//        empresas.add(new Empresa());
//
//        // Act
//        chofer.setEmpresas(empresas);
//        List<Empresa> result = chofer.getEmpresas();
//
//        // Assert
//        assertEquals(empresas.size(), result.size());
//        assertSame(empresas.get(0), result.get(0));
//        assertSame(empresas.get(1), result.get(1));
//    }

    //TODO: FIXME
//    @Test
//    public void testDarDTO() {
//        // Arrange
//        Chofer chofer = new Chofer();
//        chofer.setIdCiudadano(1);
//        chofer.setEmail("chofer@example.com");
//        chofer.setCedula("1234567890");
//        GuiaDeViaje guia = new GuiaDeViaje();
//
//        // Valores de ejemplo para el constructor
//        int id = 1;
//        LocalDateTime fechaCambio = LocalDateTime.now();
//
//        // Crear una instancia de Asignacion utilizando el constructor
//        Asignacion asignacion = new Asignacion(id, guia, fechaCambio);
//        List<Asignacion> asignaciones = new ArrayList<>();
//        asignaciones.add(asignacion);
//
//        chofer.setAsignaciones(asignaciones);
//        // Act
//        ChoferDTO dto = chofer.darDTO();
//
//        // Assert
//        assertEquals("chofer@example.com", dto.getEmail());
//        assertEquals("1234567890", dto.getCedula());
//        assertEquals(asignaciones.size(), dto.getAsignaciones().size());
//    }
}