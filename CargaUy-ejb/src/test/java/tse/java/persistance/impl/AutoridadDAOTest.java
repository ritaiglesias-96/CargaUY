package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class AutoridadDAOTest {
    @InjectMocks
    AutoridadDAO autoridadDAO;
    @Mock
    private EntityManager entityManager;

    Autoridad autoridad = new Autoridad();
    private void setAutoridad(){
        autoridad.setId(1);
        autoridad.setNombre("test");
        autoridad.setApellido("apellido");
    }
    @Test
    public void testPersist() {
        Autoridad autoridad = new Autoridad();
        autoridad.setId(1);
        autoridad.setNombre("test");
        autoridad.setApellido("apellido");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Autoridad autoridad = (Autoridad) invocation.getArguments()[0];
                autoridad.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Autoridad.class));

        autoridadDAO.persist(autoridad);
        Mockito.verify(entityManager).persist(autoridad);
    }

    @Test
    public void testMerge() {
        Autoridad autoridad = new Autoridad();
        autoridad.setId(1);
        autoridad.setNombre("test");
        autoridad.setApellido("apellido");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Autoridad autoridad = (Autoridad) invocation.getArguments()[0];
                autoridad.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Autoridad.class));

        autoridadDAO.merge(autoridad);
        Mockito.verify(entityManager).merge(autoridad);
    }


    @Test
    public void testDeleteExistingAutoridad() {
        this.setAutoridad();
        when(entityManager.contains(this.autoridad)).thenReturn(true);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Autoridad autoridad = (Autoridad) invocation.getArguments()[0];
                autoridad.setId(1);
                return null;
            }
        }).when(entityManager).remove(any(Autoridad.class));

        autoridadDAO.delete(autoridad);
        Mockito.verify(entityManager).remove(autoridad);
    }
    @Test
    public void testDeleteNotExistingAutoridad() {
        this.setAutoridad();
        when(entityManager.contains(this.autoridad)).thenReturn(false);
        when(entityManager.merge(this.autoridad)).thenReturn(this.autoridad);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Autoridad autoridad = (Autoridad) invocation.getArguments()[0];
                autoridad.setId(1);
                return null;
            }
        }).when(entityManager).remove(any(Autoridad.class));

        autoridadDAO.delete(autoridad);
        Mockito.verify(entityManager).remove(autoridad);
    }

    @Test
    public void testFindAll() {
        List<Autoridad> autoridades = new ArrayList<>();

        Autoridad autoridad = new Autoridad();
        autoridad.setId(1);
        autoridad.setNombre("test");
        autoridad.setApellido("apellido");
        autoridades.add(autoridad);

        Autoridad autoridad2 = new Autoridad();
        autoridad2.setId(2);
        autoridad2.setNombre("test 2");
        autoridad2.setApellido("apellido 2");
        autoridades.add(autoridad2);
        TypedQuery<Autoridad> query = (TypedQuery<Autoridad>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("SELECT f FROM Autoridad f", Autoridad.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(autoridades);

        List<Autoridad> autoridadesResult = autoridadDAO.findAll();

        assertEquals(autoridadesResult.size(), autoridades.size());
    }


    @Test
    public void testFindById() {
        Integer id = 1;
        when(entityManager.find(Autoridad.class, id)).thenReturn(autoridad);
        Autoridad autoridadResult = autoridadDAO.findById(id);
        assertTrue(autoridadResult != null);
        assertEquals(autoridad.getNombre(), autoridadResult.getNombre());

    }
}