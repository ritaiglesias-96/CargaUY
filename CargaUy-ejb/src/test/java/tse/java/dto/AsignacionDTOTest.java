package tse.java.dto;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AsignacionDTOTest {

    @Test
    public void testSetters() {
        AsignacionDTO asignacionDTO = new AsignacionDTO();

        Long id = 1L;
        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        LocalDateTime fechaCambio = LocalDateTime.now();

        asignacionDTO.setId(id);
        asignacionDTO.setGuia(guia);
        asignacionDTO.setFechaCambio(fechaCambio);

        assertEquals(id, asignacionDTO.getId());
        assertEquals(guia, asignacionDTO.getGuia());
        assertEquals(fechaCambio, asignacionDTO.getFechaCambio());
    }

}