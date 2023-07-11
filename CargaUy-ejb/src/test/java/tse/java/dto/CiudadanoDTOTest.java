package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Ciudadano;
import tse.java.enumerated.RolCiudadano;

import static org.junit.jupiter.api.Assertions.*;

public class CiudadanoDTOTest {

    @Test
    public void testConstructor() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setIdCiudadano(1);
        ciudadano.setEmail("correo@example.com");
        ciudadano.setCedula("123456789");
        ciudadano.setRol(RolCiudadano.FUNCIONARIO);

        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO(ciudadano);

        assertEquals(1, ciudadanoDTO.getIdCiudadano());
        assertEquals("correo@example.com", ciudadanoDTO.getEmail());
        assertEquals("123456789", ciudadanoDTO.getCedula());
        assertEquals(RolCiudadano.FUNCIONARIO, ciudadanoDTO.getRol());
    }

    @Test
    public void testConstructorWithoutArguments() {
        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();

        assertNull(ciudadanoDTO.getEmail());
        assertNull(ciudadanoDTO.getCedula());
        assertNull(ciudadanoDTO.getRol());
    }

    @Test
    public void testSetters() {
        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();
        ciudadanoDTO.setIdCiudadano(1);
        ciudadanoDTO.setEmail("correo@example.com");
        ciudadanoDTO.setCedula("123456789");
        ciudadanoDTO.setRol(RolCiudadano.CHOFER);

        assertEquals(1L, ciudadanoDTO.getIdCiudadano());
        assertEquals("correo@example.com", ciudadanoDTO.getEmail());
        assertEquals("123456789", ciudadanoDTO.getCedula());
        assertEquals(RolCiudadano.CHOFER, ciudadanoDTO.getRol());

    }
}