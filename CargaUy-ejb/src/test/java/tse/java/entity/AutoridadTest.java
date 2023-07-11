package tse.java.entity;

import org.junit.Test;
import tse.java.dto.UsuarioDTO;
import tse.java.enumerated.TipoUsuario;

import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static tse.java.enumerated.TipoUsuario.AUTORIDAD;

public class AutoridadTest {

    @Test
    public void testConstructorWithValues() {
        // Valores de ejemplo para el constructor con valores
        String nombre = "John";
        String apellido = "Doe";
        Date fechaNacimiento = new Date(100L);
        String correo = "john.doe@example.com";
        String username = "johndoe";
        String password = "password123";

        // Crear una instancia de Autoridad utilizando el constructor con valores
        Autoridad autoridad = new Autoridad(nombre, apellido, fechaNacimiento, correo, username, password);

        // Verificar que los atributos se hayan establecido correctamente a través de los getters heredados
        assertEquals(nombre, autoridad.getNombre());
        assertEquals(apellido, autoridad.getApellido());
        assertEquals(fechaNacimiento, autoridad.getFechaNacimiento());
        assertEquals(correo, autoridad.getCorreo());
        assertEquals(username, autoridad.getUsername());
        assertEquals(password, autoridad.getPassword());
    }

    @Test
    public void testDefaultConstructor() {
        // Crear una instancia de Autoridad utilizando el constructor por defecto
        Autoridad autoridad = new Autoridad();

        // Verificar que no se haya establecido ningún atributo a través de los getters heredados
        assertNull(autoridad.getNombre());
        assertNull(autoridad.getApellido());
        assertNull(autoridad.getFechaNacimiento());
        assertNull(autoridad.getCorreo());
        assertNull(autoridad.getUsername());
        assertNull(autoridad.getPassword());
    }

    @Test
    public void testConstructorWithDTO() {
        // Valores de ejemplo para el DTO
        String nombre = "John";
        String apellido = "Doe";
        LocalDate fechaNacimiento = LocalDate.of(1999, 5, 12);
        String correo = "john.doe@example.com";
        String username = "johndoe";
        String password = "password123";
        TipoUsuario tipoUsuario = AUTORIDAD;

        // Crear una instancia de UsuarioDTO utilizando los valores de ejemplo
        UsuarioDTO usuarioDTO = new UsuarioDTO(nombre, apellido, fechaNacimiento, correo, username, password, tipoUsuario);

        // Crear una instancia de Autoridad utilizando el constructor con DTO
        Autoridad autoridad = new Autoridad(usuarioDTO);

        // Verificar que los atributos se hayan establecido correctamente a través de los getters heredados
        assertEquals(nombre, autoridad.getNombre());
        assertEquals(apellido, autoridad.getApellido());
        assertEquals(correo, autoridad.getCorreo());
        assertEquals(username, autoridad.getUsername());
        assertEquals(password, autoridad.getPassword());
    }

}