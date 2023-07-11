package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CiudadanoJwtDTOTest {

    @Test
    public void testEmptyConstructor() {
        CiudadanoJwtDTO ciudadanoJwtDTO = new CiudadanoJwtDTO();

        // Verificar que los valores por defecto sean null
        assertNull(ciudadanoJwtDTO.getJwt());
        assertNull(ciudadanoJwtDTO.getCedula());
        assertNull(ciudadanoJwtDTO.getIdToken());
    }

    @Test
    public void testParameterizedConstructor() {
        String jwt = "abc123";
        String cedula = "123456789";
        String idToken = "xyz987";

        CiudadanoJwtDTO ciudadanoJwtDTO = new CiudadanoJwtDTO(jwt, cedula, idToken);

        // Verificar que los valores se asignaron correctamente
        assertEquals(jwt, ciudadanoJwtDTO.getJwt());
        assertEquals(cedula, ciudadanoJwtDTO.getCedula());
        assertEquals(idToken, ciudadanoJwtDTO.getIdToken());
    }



    @Test
    public void testSetters() {
        CiudadanoJwtDTO ciudadanoJwtDTO = new CiudadanoJwtDTO();

        // Setear valores usando los setters
        ciudadanoJwtDTO.setJwt("abc123");
        ciudadanoJwtDTO.setCedula("123456789");
        ciudadanoJwtDTO.setIdToken("xyz987");

        // Verificar que los valores se asignaron correctamente
        assertEquals("abc123", ciudadanoJwtDTO.getJwt());
        assertEquals("123456789", ciudadanoJwtDTO.getCedula());
        assertEquals("xyz987", ciudadanoJwtDTO.getIdToken());
    }

}