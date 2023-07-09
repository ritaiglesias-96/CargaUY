package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Asignacion;
import tse.java.entity.Empresa;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Vehiculo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculoDTOTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        LocalDate fechaFinITV = LocalDate.of(2023, 1, 1);
        LocalDate fechaInicioPNC = LocalDate.of(2023, 1, 2);
        LocalDate fechaFinPNC = LocalDate.of(2023, 1, 3);
        List<AsignacionDTO> asignaciones = new ArrayList<>();

        // Act
        VehiculoDTO vehiculoDTO = new VehiculoDTO(1L, "ABC123", "Pais", "Marca", "Modelo", 1000.0f, 2000.0f,
                fechaFinITV, 3, fechaInicioPNC, fechaFinPNC, 2, asignaciones);

        // Assert
        assertEquals(1L, vehiculoDTO.getId());
        assertEquals("ABC123", vehiculoDTO.getMatricula());
        assertEquals("Pais", vehiculoDTO.getPais());
        assertEquals("Marca", vehiculoDTO.getMarca());
        assertEquals("Modelo", vehiculoDTO.getModelo());
        assertEquals(1000.0f, vehiculoDTO.getPeso());
        assertEquals(2000.0f, vehiculoDTO.getCapacidadCarga());
        assertEquals(fechaFinITV, vehiculoDTO.getFechaFinITV());
        assertEquals(3, vehiculoDTO.getPnc());
        assertEquals(fechaInicioPNC, vehiculoDTO.getFechaInicioPNC());
        assertEquals(fechaFinPNC, vehiculoDTO.getFechaFinPNC());
        assertEquals(2, vehiculoDTO.getEmpresaId());
        assertEquals(asignaciones, vehiculoDTO.getAsignaciones());
    }

    @Test
    public void testProcesarLista() {
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        // Arrange
        Asignacion asignacion1 = new Asignacion(1L, new GuiaDeViaje(), LocalDateTime.now());
        Asignacion asignacion2 = new Asignacion(2L, new GuiaDeViaje(), LocalDateTime.now());
        List<Asignacion> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion1);
        asignaciones.add(asignacion2);

        // Act
        List<AsignacionDTO> asignaciones2 =vehiculoDTO.procesarLista(asignaciones);

        // Assert
        assertNotNull(asignaciones2);
        assertEquals(2, asignaciones2.size());
        assertEquals(asignacion1.darDTO().getClass(), asignaciones2.get(0).getClass());
        assertEquals(asignacion2.darDTO().getClass(), asignaciones2.get(1).getClass());
    }

    @Test
    public void testConstructorVehiculoDTO() {
        Date date = new Date(200);
        Asignacion asignacion1 = new Asignacion(1L, new GuiaDeViaje(), LocalDateTime.now());
        Asignacion asignacion2 = new Asignacion(2L, new GuiaDeViaje(), LocalDateTime.now());
        List<Asignacion> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion1);
        asignaciones.add(asignacion2);
        // Arrange
        Empresa empresa = new Empresa(1, "Empresa A", "Razón Social A", 12345, "Dirección A");
        Vehiculo vehiculo = new Vehiculo(1L, "ABC123", "Argentina", "Marca A", "Modelo A",
                1000f, 2000f, date, 1, date, date, empresa,asignaciones);

        // Act
        VehiculoDTO vehiculoDTO = new VehiculoDTO(vehiculo);

        // Assert
        assertNotNull(vehiculoDTO);
        assertEquals(vehiculo.getId(), vehiculoDTO.getId());
        assertEquals(vehiculo.getMatricula(), vehiculoDTO.getMatricula());
        assertEquals(vehiculo.getPais(), vehiculoDTO.getPais());
        assertEquals(vehiculo.getMarca(), vehiculoDTO.getMarca());
        assertEquals(vehiculo.getModelo(), vehiculoDTO.getModelo());
        assertEquals(vehiculo.getPeso(), vehiculoDTO.getPeso());
        assertEquals(vehiculo.getCapacidadCarga(), vehiculoDTO.getCapacidadCarga());
        assertEquals(vehiculo.getFechaFinITV().toLocalDate(), vehiculoDTO.getFechaFinITV());
        assertEquals(vehiculo.getPnc(), vehiculoDTO.getPnc());
        assertEquals(vehiculo.getFechaInicioPNC().toLocalDate(), vehiculoDTO.getFechaInicioPNC());
        assertEquals(vehiculo.getFechaFinPNC().toLocalDate(), vehiculoDTO.getFechaFinPNC());
        assertEquals(asignaciones.size(), vehiculoDTO.getAsignaciones().size());
        assertEquals(empresa.getId(), vehiculoDTO.getEmpresaId());
    }

}