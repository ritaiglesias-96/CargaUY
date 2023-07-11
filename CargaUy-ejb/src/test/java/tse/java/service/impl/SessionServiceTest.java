package tse.java.service.impl;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.entity.Administrador;
import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.service.IUsuariosService;

import java.util.HashMap;
import java.util.Map;
@RunWith(MockitoJUnitRunner.class)
public class SessionServiceTest {
    @Mock
    private IUsuariosService usuariosService;

    @InjectMocks
    private SessionService sessionService;
    @Mock
    private final Map<String, Usuario> userConectados = new HashMap<String, Usuario>();

    @Test
    public void iniciarSesionTest_AutenticacionExitosa() {
        // Simular datos de prueba
        String username = "usuario1";
        String password = "password1";
        Usuario usuario = (Usuario) new Administrador();
        AuthResponse expectedResponse = AuthResponse.OK;

        // Configurar comportamiento del mock
        when(usuariosService.autenticarUsuario(username, password)).thenReturn(true);
        when(usuariosService.getUsuario(username)).thenReturn(usuario);

        // Ejecutar la función a probar
        AuthResponse response = sessionService.iniciarSesion(username, password);

        // Verificar que se llamó al método del servicio de usuarios
        verify(usuariosService, times(1)).autenticarUsuario(username, password);
        verify(usuariosService, times(1)).getUsuario(username);

        // Verificar el resultado
        assertEquals(expectedResponse, response);
        assertTrue(sessionService.getUsuarioLogueado(username) == usuario);
    }


    @Test
    public void iniciarSesionTest_AutenticacionFallida() {
        // Simular datos de prueba
        String username = "usuario1";
        String password = "password1";
        AuthResponse expectedResponse = AuthResponse.FAILED;

        // Configurar comportamiento del mock
        when(usuariosService.autenticarUsuario(username, password)).thenReturn(false);

        // Ejecutar la función a probar
        AuthResponse response = sessionService.iniciarSesion(username, password);

        // Verificar que se llamó al método del servicio de usuarios
        verify(usuariosService, times(1)).autenticarUsuario(username, password);

        // Verificar el resultado
        assertEquals(expectedResponse, response);
        assertNull(sessionService.getUsuarioLogueado(username));
    }


    /*@Test
    public void cerrarSesionTest() {
        // Simular datos de prueba
        String username = "usuario1";
        Usuario usuario = new Usuario();
        sessionService.getUsuarioConectados().put(username, usuario);

        // Ejecutar la función a probar
        sessionService.cerrarSesion(username);

        // Verificar que se removió el usuario de la lista de usuarios conectados
        assertNull(sessionService.getUsuarioConectados().get(username));
    }*/
}
