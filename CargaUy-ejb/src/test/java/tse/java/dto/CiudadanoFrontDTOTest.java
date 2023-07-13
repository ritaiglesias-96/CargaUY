package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Ciudadano;
import tse.java.enumerated.RolCiudadano;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CiudadanoFrontDTOTest {

    @Test
    public void testConstructor() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setIdCiudadano(1);
        ciudadano.setNombre("batman");
        ciudadano.setApellido("batman2");
        ciudadano.setEmail("correo@example.com");
        ciudadano.setCedula("123456789");
        ciudadano.setRol(RolCiudadano.CIUDADANO);
        String jwt = "gdjdfghsdsfd";
        String idToken = "ddsyerdhtyfg";

        CiudadanoFrontDTO ciudadanoDTO = new CiudadanoFrontDTO(ciudadano, jwt, idToken);

        assertEquals("ddsyerdhtyfg", ciudadanoDTO.getIdToken());
        assertEquals("gdjdfghsdsfd", ciudadanoDTO.getJwt());
        assertEquals("correo@example.com", ciudadanoDTO.getEmail());
        assertEquals("batman", ciudadanoDTO.getNombre());
        assertEquals("batman2", ciudadanoDTO.getApellido());
        assertEquals("123456789", ciudadanoDTO.getCedula());
        assertEquals(RolCiudadano.CIUDADANO, ciudadanoDTO.getRol());
    }

    @Test
    public void testConstructorWithoutArguments() {
        CiudadanoDTO ciudadanoDTO = new CiudadanoDTO();

        assertNull(ciudadanoDTO.getEmail());
        assertNull(ciudadanoDTO.getNombre());
        assertNull(ciudadanoDTO.getApellido());
        assertNull(ciudadanoDTO.getCedula());
        assertNull(ciudadanoDTO.getRol());
    }

    @Test
    public void testSetters() {
        CiudadanoFrontDTO ciudadanoDTO = new CiudadanoFrontDTO();
        ciudadanoDTO.setIdToken("sdf");
        ciudadanoDTO.setJwt("sdfasd");
        ciudadanoDTO.setNombre("batman");
        ciudadanoDTO.setApellido("batman2");
        ciudadanoDTO.setEmail("correo@example.com");
        ciudadanoDTO.setCedula("123456789");
        ciudadanoDTO.setRol(RolCiudadano.CIUDADANO);

        assertEquals("sdf", ciudadanoDTO.getIdToken());
        assertEquals("sdfasd", ciudadanoDTO.getJwt());
        assertEquals("correo@example.com", ciudadanoDTO.getEmail());
        assertEquals("123456789", ciudadanoDTO.getCedula());
        assertEquals("batman", ciudadanoDTO.getNombre());
        assertEquals("batman2", ciudadanoDTO.getApellido());
        assertEquals(RolCiudadano.CIUDADANO, ciudadanoDTO.getRol());

    }
}