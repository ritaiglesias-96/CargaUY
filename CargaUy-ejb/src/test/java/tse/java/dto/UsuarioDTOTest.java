package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Administrador;
import tse.java.entity.Usuario;
import tse.java.enumerated.TipoUsuario;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioDTOTest {

    @Test
    public void testConstructorUsuarioDTO() {
        Date date = new Date(2000);

        Usuario usuario = (Usuario) new Administrador();
        usuario.setIdUsuario(1);
        usuario.setNombre("Diego");
        usuario.setApellido("Bronc");
        usuario.setFechaNacimiento(date);
        usuario.setCorreo("diegobronc@gmail");
        usuario.setUsername("diegob");
        usuario.setPassword("password");
        TipoUsuario tipo = TipoUsuario.ADMIN;

        // Act
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuario, tipo);

        // Assert
        assertNotNull(usuarioDTO);
        assertEquals(usuario.getIdUsuario(), usuarioDTO.getIdUsuario());
        assertEquals(usuario.getNombre(), usuarioDTO.getNombre());
        assertEquals(usuario.getApellido(), usuarioDTO.getApellido());
        assertEquals(usuario.getCorreo(), usuarioDTO.getCorreo());
        assertEquals(usuario.getUsername(), usuarioDTO.getUsername());
        assertEquals(usuario.getPassword(), usuarioDTO.getPassword());
        assertEquals(tipo, usuarioDTO.getTipo());
    }

    @Test
    public void testSetters() {
        // Crear una instancia de UsuarioDTO
        UsuarioDTO usuarioDTO = new UsuarioDTO();

        // Establecer valores utilizando los setters
        usuarioDTO.setIdUsuario(1);
        usuarioDTO.setNombre("Diego");
        usuarioDTO.setApellido("Bronc");
        usuarioDTO.setFechaNacimiento(LocalDate.of(1990, 5, 15));
        usuarioDTO.setCorreo("diegobronc@gmail");
        usuarioDTO.setUsername("diegob");
        usuarioDTO.setPassword("password123");
        usuarioDTO.setTipo(TipoUsuario.ADMIN);

        // Verificar los valores utilizando los getters
        assertEquals(1, usuarioDTO.getIdUsuario());
        assertEquals("Diego", usuarioDTO.getNombre());
        assertEquals("Bronc", usuarioDTO.getApellido());
        assertEquals(LocalDate.of(1990, 5, 15), usuarioDTO.getFechaNacimiento());
        assertEquals("diegobronc@gmail", usuarioDTO.getCorreo());
        assertEquals("diegob", usuarioDTO.getUsername());
        assertEquals("password123", usuarioDTO.getPassword());
        assertEquals(TipoUsuario.ADMIN, usuarioDTO.getTipo());
    }

}