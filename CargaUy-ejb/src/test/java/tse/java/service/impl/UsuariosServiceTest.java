package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.UsuarioDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;
import tse.java.enumerated.TipoUsuario;
import tse.java.exception.UsuarioExisteException;
import tse.java.persistance.IAdministradorDAO;
import tse.java.persistance.IAutoridadDAO;
import tse.java.persistance.IUsuarioDAO;

import javax.ejb.EJB;

import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UsuariosServiceTest {

    @Mock
    IUsuarioDAO usuarioDAO;
    @Mock
    IAdministradorDAO administradorDAO;
    @Mock
    IAutoridadDAO autoridadDAO;

    @InjectMocks
    UsuariosService usuariosService;

    @Test
    public void testListarUsuarios() {
        List<UsuarioDTO> expectedUsuarios = new ArrayList<>();

        when(usuarioDAO.listarUsuarios()).thenReturn(expectedUsuarios);

        List<UsuarioDTO> result = usuariosService.listarUsuarios();

        assertEquals(expectedUsuarios, result);
    }

    @Test
    public void testAutenticarUsuario_WhenUserExistsAndPasswordMatches() throws NoSuchAlgorithmException {
        String username = "john.doe";
        String password = "password";

        Usuario user = (Usuario) new Administrador();
        user.setUsername(username);
        user.setPassword(usuariosService.hashPassword(password));

        when(usuarioDAO.findByUsername(username)).thenReturn(user);

        boolean result = usuariosService.autenticarUsuario(username, password);

        assertTrue(result);
    }

    @Test
    public void testAutenticarUsuario_WhenUserDoesNotExist() {
        // Arrange
        String username = "john.doe";
        String password = "password";

        when(usuarioDAO.findByUsername(username)).thenReturn(null);

        // Act
        boolean result = usuariosService.autenticarUsuario(username, password);

        // Assert
        assertFalse(result);
    }

    @Test
    public void testAutenticarUsuario_WhenPasswordDoesNotMatch() throws NoSuchAlgorithmException {
        // Arrange
        String username = "john.doe";
        String password = "password";
        String wrongPassword = "wrongpassword";

        Usuario user = (Usuario) new Administrador();
        user.setUsername(username);
        user.setPassword(usuariosService.hashPassword(password));

        when(usuarioDAO.findByUsername(username)).thenReturn(user);

        boolean result = usuariosService.autenticarUsuario(username, wrongPassword);

        assertFalse(result);
    }



    @Test
    public void testGetUsuario() {
        String username = "john.doe";
        Usuario user = (Usuario) new Administrador();

        when(usuarioDAO.findByUsername(username)).thenReturn(user);

        Usuario result = usuariosService.getUsuario(username);

        assertEquals(user, result);
    }

    @Test
    public void testGetAdminByID() {
        int id = 1; // ID del administrador
        Administrador expectedAdmin = new Administrador();

        when(administradorDAO.findById(id)).thenReturn(expectedAdmin);

        Administrador result = usuariosService.getAdminByID(id);

        assertEquals(expectedAdmin, result);
    }

    @Test
    public void testGetAutoByID() {
        int id = 1; // ID de la autoridad
        Autoridad expectedAuto = new Autoridad();

        when(autoridadDAO.findById(id)).thenReturn(expectedAuto);

        Autoridad result = usuariosService.getAutoByID(id);

        assertEquals(expectedAuto, result);
    }

    @Test
    public void testRegistrarUsuario_WhenUserDoesNotExistAndTipoIsAutoridad() throws NoSuchAlgorithmException {
        UsuarioDTO user = new UsuarioDTO();
        user.setUsername("john.doe");
        user.setPassword("password");
        user.setTipo(TipoUsuario.AUTORIDAD);
        user.setFechaNacimiento(new Date(2000).toLocalDate());
        when(usuarioDAO.existUserByUsername(user.getUsername())).thenReturn(false);

        boolean result = usuariosService.registrarUsuario(user);

        assertTrue(result);
    }

    @Test
    public void testRegistrarUsuario_WhenUserDoesNotExistAndTipoIsAdmin() throws NoSuchAlgorithmException {
        UsuarioDTO user = new UsuarioDTO();
        user.setUsername("john.doe");
        user.setPassword("password");
        user.setTipo(TipoUsuario.ADMIN);
        user.setFechaNacimiento(new Date(2000).toLocalDate());
        when(usuarioDAO.existUserByUsername(user.getUsername())).thenReturn(false);


        boolean result = usuariosService.registrarUsuario(user);

        assertTrue(result);
    }

    @Test
    public void testRegistrarUsuario_WhenUserExists() {
        UsuarioDTO user = new UsuarioDTO();
        user.setUsername("john.doe");
        user.setFechaNacimiento(new Date(2000).toLocalDate());
        when(usuarioDAO.existUserByUsername(user.getUsername())).thenReturn(true);

        boolean result = usuariosService.registrarUsuario(user);

        assertFalse(result);
    }

    @Test
    public void testActualizarDatos_WhenUserIsAutoridad() {
        Autoridad user = new Autoridad();

        usuariosService.actualizarDatos(user);

        Mockito.verify(autoridadDAO).merge(user);
    }

    @Test
    public void testActualizarDatos_WhenUserIsAdmin() {
        Administrador user = new Administrador();

        usuariosService.actualizarDatos(user);

        Mockito.verify(administradorDAO).merge(user);
    }

    @Test
    public void testEliminarUsuario_WhenUserExistsAndTipoIsAdmin() throws UsuarioExisteException {
        // Arrange
        UsuarioDTO user = new UsuarioDTO();
        user.setUsername("john.doe");
        user.setTipo(TipoUsuario.ADMIN);
        user.setFechaNacimiento(new Date(2000).toLocalDate());

        when(usuarioDAO.existUserByUsername(user.getUsername())).thenReturn(true);

        usuariosService.eliminarUsuario(user);

        // Assert
        Mockito.verify(administradorDAO).delete(any(Administrador.class));
    }

    @Test
    public void testEliminarUsuario_WhenUserExistsAndTipoIsAutoridad() throws UsuarioExisteException {
        // Arrange
        UsuarioDTO user = new UsuarioDTO();
        user.setUsername("john.doe");
        user.setTipo(TipoUsuario.AUTORIDAD);
        user.setFechaNacimiento(new Date(2000).toLocalDate());

        when(usuarioDAO.existUserByUsername(user.getUsername())).thenReturn(true);

        // Act
        usuariosService.eliminarUsuario(user);

        // Assert
        Mockito.verify(autoridadDAO).delete(any(Autoridad.class));
    }

    @Test
    public void testEliminarUsuario_WhenUserDoesNotExist() {
        UsuarioDTO user = new UsuarioDTO();
        user.setUsername("john.doe");
        user.setFechaNacimiento(new Date(2000).toLocalDate());

        when(usuarioDAO.existUserByUsername(user.getUsername())).thenReturn(false);

        assertThrows(UsuarioExisteException.class, () -> usuariosService.eliminarUsuario(user));
    }

    @Test
    public void testHashPassword() throws NoSuchAlgorithmException {
        // Arrange
        String password = "password";
        String expectedHash = "5F4DCC3B5AA765D61D8327DEB882CF99"; // MD5 hash of "password"

        // Act
        String result = usuariosService.hashPassword(password);

        // Assert
        assertEquals(expectedHash, result.toUpperCase());
    }
}