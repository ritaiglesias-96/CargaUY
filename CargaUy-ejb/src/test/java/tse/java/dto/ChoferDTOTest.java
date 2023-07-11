package tse.java.dto;

import org.junit.Test;
import tse.java.enumerated.RolCiudadano;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChoferDTOTest {

    @Test
    public void testConstructor() {
        int idCiudadano = 1;
        String email = "chofer@example.com";
        String cedula = "123456789";
        String nombre = "Pablo";
        String apellido = "Gomex";
        RolCiudadano rol = RolCiudadano.CHOFER;
        List<AsignacionDTO> asignacionesDTO = new ArrayList<>();
        asignacionesDTO.add(new AsignacionDTO());
        asignacionesDTO.add(new AsignacionDTO());

        ChoferDTO choferDTO = new ChoferDTO(idCiudadano, email, cedula, rol, nombre, apellido);

        choferDTO.setAsignaciones(asignacionesDTO);

        assertEquals(idCiudadano, choferDTO.getIdCiudadano());
        assertEquals(email, choferDTO.getEmail());
        assertEquals(cedula, choferDTO.getCedula());
        assertEquals(rol, choferDTO.getRol());
        assertEquals(asignacionesDTO,choferDTO.getAsignaciones());
    }

}