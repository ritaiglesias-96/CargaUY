package tse.java.service.impl;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.service.IUsuariosService;

import java.util.HashMap;
import java.util.Map;

public class SessionServiceTest {
/*
    private SessionService sessionService;
    private IUsuariosService usuariosServiceMock;


    private static final Map<String, Usuario> userConectados = new HashMap<String, Usuario>();

    @Before
    public void setUp() {
        usuariosServiceMock =new UsuariosService();
        sessionService = new SessionService();
    }

    @Test
    public void testIniciarSesionAutenticacionExitosa() {
        // Configuración del caso de prueba
        String username = "user123";
        String password = "password123";
        Usuario usuarioMock = mock(Usuario.class);

        // Simular el comportamiento del método autenticarUsuario
        when(usuariosServiceMock.autenticarUsuario(username, password)).thenReturn(true);
        when(usuariosServiceMock.getUsuario(username)).thenReturn(usuarioMock);

        // Ejecutar el método bajo prueba
        AuthResponse response = sessionService.iniciarSesion(username, password);
        userConectados.put(username, usuariosServiceMock.getUsuario(username));

        // Verificar los resultados
        assertEquals(AuthResponse.OK, response);
        assertTrue(userConectados.containsKey(username));
    }

    @Test
    public void testIniciarSesionAutenticacionFallida() {
        // Configuración del caso de prueba
        String username = "user123";
        String password = "password123";

        // Simular el comportamiento del método autenticarUsuario
        when(usuariosServiceMock.autenticarUsuario(username, password)).thenReturn(false);

        // Ejecutar el método bajo prueba
        AuthResponse response = sessionService.iniciarSesion(username, password);

        // Verificar los resultados
        assertEquals(AuthResponse.FAILED, response);
    }

    @Test
    public void testCerrarSesion() {
        // Configuración del caso de prueba
        String username = "user123";


        // Ejecutar el método bajo prueba
        sessionService.cerrarSesion(username);

        // Verificar los resultados
        assertFalse(userConectados.containsKey(username));
    }*/
}
