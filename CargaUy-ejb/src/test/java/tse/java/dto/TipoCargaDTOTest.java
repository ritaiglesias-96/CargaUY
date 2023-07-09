package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TipoCargaDTOTest {


    @Test
    public void testConstructorVacio() {
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();

        assertNull(tipoCargaDTO.getId());
        assertNull(tipoCargaDTO.getNombre());
    }
    @Test
    public void testSetters() {
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();

        tipoCargaDTO.setId(1L);
        tipoCargaDTO.setNombre("Carga Pesada");

        assertEquals(1L, tipoCargaDTO.getId());
        assertEquals("Carga Pesada", tipoCargaDTO.getNombre());
    }

}