package tse.java.persistance.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.mockito.stubbing.OngoingStubbing;
import tse.java.entity.Administrador;
import tse.java.util.EntityManagerProducer;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class AdministradorDAOTest {

    @InjectMocks
    AdministradorDAO administradorDAO;


    @Mock
    private EntityManager entityManager;

    Administrador administrador = new Administrador();

    private void setAdministrador(){
        administrador.setId(1);
        administrador.setNombre("test");
        administrador.setApellido("apellido");
    }

    @Test
    public void persist() {
        Administrador administrador = new Administrador();
        administrador.setId(1);
        administrador.setNombre("test");
        administrador.setApellido("apellido");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Administrador administrador = (Administrador) invocation.getArguments()[0];
                administrador.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Administrador.class));

        administradorDAO.persist(administrador);
        Mockito.verify(entityManager).persist(administrador);

    }

    @Test
    public void merge() {
        Administrador administrador = new Administrador();
        administrador.setId(1);
        administrador.setNombre("test");
        administrador.setApellido("apellido");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Administrador administrador = (Administrador) invocation.getArguments()[0];
                administrador.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Administrador.class));

        administradorDAO.merge(administrador);
        Mockito.verify(entityManager).merge(administrador);

    }

    @Test
    public void testDeleteExistingAdmin(){
        this.setAdministrador();
        when(entityManager.contains(this.administrador)).thenReturn(true);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Administrador administrador = (Administrador) invocation.getArguments()[0];
                administrador.setId(1);
                return null;
            }
        }).when(entityManager).remove(any(Administrador.class));

        administradorDAO.delete(this.administrador);
        Mockito.verify(entityManager).remove(this.administrador);

    }


    @Test
    public void testDeleteNotExistingAdmin(){
        this.setAdministrador();
        when(entityManager.contains(this.administrador)).thenReturn(false);
        when(entityManager.merge(this.administrador)).thenReturn(this.administrador);

        doAnswer((Answer<Object>) invocation -> {
            Administrador administrador = (Administrador) invocation.getArguments()[0];
            administrador.setId(1);
            return null;
        }).when(entityManager).remove(any(Administrador.class));

        administradorDAO.delete(this.administrador);
        Mockito.verify(entityManager).remove(this.administrador);

    }

    @Test
    public void testFindAll() {
        List<Administrador> administradores = new ArrayList<>();

        Administrador administrador = new Administrador();
        administrador.setId(1);
        administrador.setNombre("test");
        administrador.setApellido("apellido");
        administradores.add(administrador);

        Administrador administrador2 = new Administrador();
        administrador2.setId(2);
        administrador2.setNombre("test 2");
        administrador2.setApellido("apellido 2");
        administradores.add(administrador2);
        TypedQuery<Administrador> query = (TypedQuery<Administrador>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT r FROM Administrador r", Administrador.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(administradores);

        List<Administrador> administradoresResult = administradorDAO.findAll();

        assertEquals(administradoresResult.size(), administradores.size());


    }

    @Test
    public void testFindById() {
        Integer id = 1;
        when(entityManager.find(Administrador.class, id)).thenReturn(administrador);
        Administrador administradorResult = administradorDAO.findById(id);
        assertNotNull(administradorResult);
        assertEquals(administrador.getNombre(), administradorResult.getNombre());

    }

}