package tse.java.dto;

import org.junit.Test;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PermisosVehiculoDTOTest {

    @Test
    public void testConstructorsAndGetters() {
        // Arrange
        PermisosVehiculoDTO permisosDTO1 = new PermisosVehiculoDTO();
        PermisosVehiculoDTO permisosDTO2 = new PermisosVehiculoDTO(1, 2, "ABC123", "Pais", new Date(2000).toLocalDate(), 3, new Date(2001).toLocalDate(), new Date(2002).toLocalDate());


        assertEquals(1, permisosDTO2.getIdVehiculo());
        assertEquals(2, permisosDTO2.getIdEmpresa());
        assertEquals("ABC123", permisosDTO2.getMatricula());
        assertEquals("Pais", permisosDTO2.getPais());
        assertNotNull(permisosDTO2.getFechaFinITV());
        assertEquals(3, permisosDTO2.getPnc());
        assertNotNull(permisosDTO2.getFechaInicioPNC());
        assertNotNull(permisosDTO2.getFechaFinPNC());
    }

    @Test
    public void testConstructorAndGetters() {

        LocalDate date = LocalDate.of(1999, 5, 12);
        // Arrange
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(1);
        vehiculoDTO.setMatricula("ABC123");
        vehiculoDTO.setPais("Pais");
        vehiculoDTO.setFechaFinITV(date);
        vehiculoDTO.setPnc(3);
        vehiculoDTO.setFechaInicioPNC(date);
        vehiculoDTO.setFechaFinPNC(date);

        // Act
        PermisosVehiculoDTO permisosDTO = new PermisosVehiculoDTO(vehiculoDTO);

        // Assert
        assertEquals(1L, permisosDTO.getIdVehiculo());
        assertEquals("ABC123", permisosDTO.getMatricula());
        assertEquals("Pais", permisosDTO.getPais());
        assertNotNull(permisosDTO.getFechaFinITV());
        assertEquals(3, permisosDTO.getPnc());
        assertNotNull(permisosDTO.getFechaInicioPNC());
        assertNotNull(permisosDTO.getFechaFinPNC());
    }
}