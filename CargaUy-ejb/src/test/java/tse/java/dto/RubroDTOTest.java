package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RubroDTOTest {

    @Test
    public void testConstructorVacio() {
        RubroDTO rubroDTO = new RubroDTO();

        assertNull(rubroDTO.getId());
        assertNull(rubroDTO.getNombre());
    }

    @Test
    public void testSetters() {
        RubroDTO rubroDTO = new RubroDTO();

        rubroDTO.setId(1L);
        rubroDTO.setNombre("Transporte");

        assertEquals(1L, rubroDTO.getId());
        assertEquals("Transporte", rubroDTO.getNombre());
    }

}