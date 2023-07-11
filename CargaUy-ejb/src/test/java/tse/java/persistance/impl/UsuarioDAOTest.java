package tse.java.persistance.impl;

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

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UsuarioDAOTest {

    @InjectMocks
    UsuarioDAO usuarioDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testFindByUsername() {
        Usuario usuario = (Usuario) new Administrador();
        String username = "usertest";
        usuario.setNombre("Test");
        usuario.setUsername(username);
        TypedQuery<Usuario> query = (TypedQuery<Usuario>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("FROM Usuario WHERE username = :username")).thenReturn(query);
        when(query.setParameter("username", username)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(usuario);

        Usuario usuarioRetorno = usuarioDAO.findByUsername(username);

        assertEquals(usuario.getUsername(),usuarioRetorno.getUsername());
    }

    @Test
    public void testExistUserByUsername() {

        String username = "usertest";

        TypedQuery<Usuario> query = (TypedQuery<Usuario>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Usuario WHERE username = :username")).thenReturn(query);
        when(query.setParameter("username", username)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(null);

        boolean resultado = usuarioDAO.existUserByUsername(username);

        assertEquals(true, resultado);
    }

  /*  @Test
    public void testListarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        Autoridad usuario =  new Autoridad();
        usuario.setNombre("Test");
        usuario.setUsername("usertest");

        Administrador usuario2 =  new Administrador();
        usuario2.setNombre("Test2");
        usuario2.setUsername("usertest2");

        usuarios.add(usuario);
        usuarios.add(usuario2);
        TypedQuery<Usuario> query = (TypedQuery<Usuario>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select u from Usuario u", Usuario.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(usuarios);

        List<UsuarioDTO> usuariosResult = usuarioDAO.listarUsuarios();

        assertEquals(usuarios.size(), usuariosResult.size() );

    }
*/
}
