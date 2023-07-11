package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.ChoferDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.enumerated.RolCiudadano;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;
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

    @Test
    public void testConstructorWithDTO() {
        int id = 1;
        ChoferDTO  choferDTO  = new ChoferDTO();
        choferDTO.setIdCiudadano(1);
        choferDTO.setEmail("chofer@example.com");
        choferDTO.setCedula("1234567890");
        LocalDateTime fechaCambio = LocalDateTime.now();
        LocalDate localDate = new Date(2000).toLocalDate();
        Date date = new Date(200);
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        guia.setFecha(localDate);
        AsignacionDTO asignacion = new AsignacionDTO(id, guia, fechaCambio);
        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion);
        choferDTO.setAsignaciones(asignaciones);

        Chofer resultado = new Chofer(choferDTO);
        assertEquals(choferDTO.getEmail(), resultado.getEmail());
        assertEquals(choferDTO.getCedula(), resultado.getCedula());
        assertEquals(asignaciones.size(), resultado.getAsignaciones().size());
    }
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

    @Test
    public void testProcesarListaAsignaciones() {
        int id = 1;
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setEmail("chofer@example.com");
        chofer.setCedula("1234567890");
        LocalDateTime fechaCambio = LocalDateTime.now();
        Date date = new Date(200);
        GuiaDeViaje guia = new GuiaDeViaje();
        guia.setFecha(date);
        Asignacion asignacion = new Asignacion(id, guia, fechaCambio);
        List<Asignacion> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion);

        List<AsignacionDTO> result = chofer.procesarListaAsignaciones(asignaciones);

        // Assert
        assertEquals(asignaciones.size(), result.size());

    }

    @Test
    public void testDarDTO() {
        int id = 1;
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setEmail("chofer@example.com");
        chofer.setCedula("1234567890");
        LocalDateTime fechaCambio = LocalDateTime.now();
        Date date = new Date(200);
        GuiaDeViaje guia = new GuiaDeViaje();
        guia.setFecha(date);
        Asignacion asignacion = new Asignacion(id, guia, fechaCambio);
        List<Asignacion> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion);

        ChoferDTO resultado  = chofer.darDTO();
        assertEquals(chofer.getIdCiudadano(), resultado.getIdCiudadano());
        assertEquals(chofer.getEmail(), resultado.getEmail());
        assertEquals(chofer.getCedula(), resultado.getCedula());
    }

}