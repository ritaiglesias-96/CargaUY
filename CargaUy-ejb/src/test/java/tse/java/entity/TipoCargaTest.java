package tse.java.entity;

import org.junit.Test;
import tse.java.dto.TipoCargaDTO;

import static org.junit.jupiter.api.Assertions.*;

public class TipoCargaTest {

    @Test
    public void testConstructor() {
        // Arrange
        Long id = 1L;
        String nombre = "Carga General";

        // Act
        TipoCarga tipoCarga = new TipoCarga(id, nombre);

        // Assert
        assertEquals(id, tipoCarga.getId());
        assertEquals(nombre, tipoCarga.getNombre());
    }
    @Test
    public void testDarDTO() {
        // Arrange
        Long id = 1L;
        String nombre = "Carga General";
        TipoCarga tipoCarga = new TipoCarga();
        tipoCarga.setId(id);
        tipoCarga.setNombre(nombre);

        // Act
        TipoCargaDTO tipoCargaDTO = tipoCarga.darDTO();

        // Assert
        assertEquals(id, tipoCargaDTO.getId());
        assertEquals(nombre, tipoCargaDTO.getNombre());
    }


}