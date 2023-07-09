package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Pesaje;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PesajeDTOTest {
    @Test
    public void testSetters() {
        PesajeDTO pesajeDTO = new PesajeDTO();

        Long id = 1L;
        double latitud = 10.0;
        double longitud = 20.0;
        LocalDateTime fecha = LocalDateTime.now();
        float carga = 100.0f;

        pesajeDTO.setId(id);
        pesajeDTO.setLatitud(latitud);
        pesajeDTO.setLonguitud(longitud);
        pesajeDTO.setFecha(fecha);
        pesajeDTO.setCarga(carga);

        // Verificar que los valores se asignaron correctamente
        assertEquals(id, pesajeDTO.getId());
        assertEquals(latitud, pesajeDTO.getLatitud());
        assertEquals(longitud, pesajeDTO.getLonguitud());
        assertEquals(fecha, pesajeDTO.getFecha());
        assertEquals(carga, pesajeDTO.getCarga());
    }
}