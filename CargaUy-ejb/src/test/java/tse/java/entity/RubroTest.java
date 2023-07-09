package tse.java.entity;

import org.junit.Test;
import tse.java.dto.RubroDTO;

import static org.junit.jupiter.api.Assertions.*;

public class RubroTest {

    @Test
    public void testConstructor() {
        // Valores de ejemplo para el constructor
        Long id = 1L;
        String nombre = "Rubro1";

        // Crear una instancia de Rubro utilizando el constructor
        Rubro rubro = new Rubro(id, nombre);

        // Verificar que los atributos se hayan establecido correctamente
        assertEquals(id, rubro.getId());
        assertEquals(nombre, rubro.getNombre());
    }

    @Test
    public void testConstructorWithoutArguments() {
        Rubro rubro = new Rubro();

        assertNull(rubro.getId());
        assertNull(rubro.getNombre());

    }

    @Test
    public void testDarDTO() {
        // Valores de ejemplo para el id y el nombre
        Long id = 1L;
        String nombre = "Rubro1";

        // Crear una instancia de Rubro
        Rubro rubro = new Rubro();
        rubro.setId(id);
        rubro.setNombre(nombre);

        // Llamar al método darDTO()
        RubroDTO rubroDTO = rubro.darDTO();

        // Verificar que el DTO tenga los mismos valores que el Rubro
        assertEquals(id, rubroDTO.getId());
        assertEquals(nombre, rubroDTO.getNombre());
    }

}