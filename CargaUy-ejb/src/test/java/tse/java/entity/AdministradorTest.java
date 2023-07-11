package tse.java.entity;

import org.junit.Test;
import tse.java.dto.UsuarioDTO;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class AdministradorTest {

    @Test
     public void testAdministrador() {
        Administrador administrador = new Administrador("Diego", "Bronc", new Date(99,8,15), "diegobronc@gmail.com", "diegob", "1234");
        Administrador esperado = new Administrador();
        esperado.setIdUsuario(administrador.getIdUsuario());
        esperado.setNombre("Diego"); esperado.setApellido("Bronc"); esperado.setFechaNacimiento(new Date(99,8,15)); esperado.setCorreo("diegobronc@gmail.com"); esperado.setUsername("diegob"); esperado.setPassword("1234");
        assertEquals(esperado.getClass(),administrador.getClass());
    }

    @Test
    public void testConstructorWithUsuarioDTO() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        LocalDate date = LocalDate.of(1999, 5, 12);
        usuarioDTO.setFechaNacimiento(date);
        Administrador administrador = new Administrador(usuarioDTO);

    }
}