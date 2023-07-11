package tse.java.entity;

import org.junit.Test;
import tse.java.dto.FuncionarioDTO;
import tse.java.enumerated.RolCiudadano;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioTest {

    @Test
    public void testConstructor() {
        // Arrange
        String email = "funcionario@example.com";
        String cedula = "1234567890";
        String nombre = "Pablo";
        String apellido = "Gomex";


        // Act
        Funcionario funcionario = new Funcionario(email, cedula, nombre, apellido);

        // Assert
        assertEquals(email, funcionario.getEmail());
        assertEquals(cedula, funcionario.getCedula());
        assertEquals(RolCiudadano.FUNCIONARIO, funcionario.getRol());
    }

    @Test
    public void testDarDTO() {
        // Arrange
        Integer idCiudadano = 23;
        String email = "funcionario@example.com";
        String cedula = "1234567890";

        Funcionario funcionario = new Funcionario();
        funcionario.setIdCiudadano(idCiudadano);
        funcionario.setEmail(email);
        funcionario.setCedula(cedula);

        // Act
        FuncionarioDTO funcionarioDTO = funcionario.darDTO();

        // Assert
        assertEquals(idCiudadano, funcionarioDTO.getIdCiudadano());
        assertEquals(email, funcionarioDTO.getEmail());
        assertEquals(cedula, funcionarioDTO.getCedula());
        assertEquals(RolCiudadano.FUNCIONARIO, funcionarioDTO.getRol());
    }

}