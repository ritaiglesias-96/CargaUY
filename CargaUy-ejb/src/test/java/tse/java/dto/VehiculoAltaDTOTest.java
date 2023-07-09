package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Asignacion;
import tse.java.entity.GuiaDeViaje;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculoAltaDTOTest {
    @Test
    public void testConstructorVehiculoAltaDTO() {
        // Arrange
        int idEmpresa = 1;
        String matricula = "ABC123";
        String pais = "Uruguay";
        String marca = "Marca A";
        String modelo = "Modelo A";
        Float peso = 1000f;
        Float capacidadCarga = 2000f;
        int pnc = 1;
        Date fechaFinITV = new Date(2000);
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2000);

        // Act
        VehiculoAltaDTO vehiculoAltaDTO = new VehiculoAltaDTO(idEmpresa, matricula, pais, marca, modelo,
                peso, capacidadCarga, pnc, fechaFinITV, fechaInicioPNC, fechaFinPNC);

        // Assert
        assertNotNull(vehiculoAltaDTO);
        assertEquals(idEmpresa, vehiculoAltaDTO.getIdEmpresa());
        assertEquals(matricula, vehiculoAltaDTO.getMatricula());
        assertEquals(pais, vehiculoAltaDTO.getPais());
        assertEquals(marca, vehiculoAltaDTO.getMarca());
        assertEquals(modelo, vehiculoAltaDTO.getModelo());
        assertEquals(peso, vehiculoAltaDTO.getPeso());
        assertEquals(capacidadCarga, vehiculoAltaDTO.getCapacidadCarga());
        assertEquals(pnc, vehiculoAltaDTO.getPnc());
    }

    @Test
    public void testConstructorVehiculoAltaDTO_Default() {
        // Arrange
        String matricula = "ABC123";
        String pais = "Uruguay";
        // Act
        VehiculoAltaDTO vehiculoAltaDTO = new VehiculoAltaDTO();

        // Assert
        vehiculoAltaDTO.setPais(pais);
        vehiculoAltaDTO.setMatricula(matricula);
        assertNotNull(vehiculoAltaDTO);
        assertEquals(matricula,vehiculoAltaDTO.getMatricula());
        assertEquals(pais,vehiculoAltaDTO.getPais());
        assertNull(vehiculoAltaDTO.getMarca());
        assertNull(vehiculoAltaDTO.getModelo());
        assertNull(vehiculoAltaDTO.getPeso());
        assertNull(vehiculoAltaDTO.getCapacidadCarga());
        assertEquals(0, vehiculoAltaDTO.getPnc());
        assertNull(vehiculoAltaDTO.getFechaFinITV());
        assertNull(vehiculoAltaDTO.getFechaInicioPNC());
        assertNull(vehiculoAltaDTO.getFechaFinPNC());
    }

}