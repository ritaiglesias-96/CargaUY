package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.CiudadanoDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Ciudadano;
import tse.java.entity.Funcionario;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CiudadanoDAOTest {

    @InjectMocks
    CiudadanoDAO ciudadanoDAO;

    @Mock
    EntityManager entityManager;

    @Test
    public void testListarCiudadanos() {
        List<Ciudadano> ciudadanos = new ArrayList<>();

        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setIdCiudadano(1);
        ciudadano.setCedula("12455");
        ciudadanos.add(ciudadano);


        TypedQuery<Ciudadano> query = (TypedQuery<Ciudadano>) Mockito.mock(TypedQuery.class);
        when(entityManager.createNativeQuery("select * from public.\"Ciudadano\"", Ciudadano.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(ciudadanos);

        ArrayList<CiudadanoDTO> result = ciudadanoDAO.listarCiudadanos();

        assertEquals(ciudadanos.size(), result.size());
    }

    @Test
    public void testAgregarCiudadano() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setIdCiudadano(1);
        ciudadano.setCedula("12455");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Ciudadano ciudadano = (Ciudadano) invocation.getArguments()[0];
                ciudadano.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).persist(any(Ciudadano.class));

        ciudadanoDAO.agregarCiudadano(ciudadano);
        Mockito.verify(entityManager).persist(ciudadano);
    }

    @Test
    public void testModificarCiudadano() {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setIdCiudadano(1);
        ciudadano.setCedula("12455");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Ciudadano ciudadano = (Ciudadano) invocation.getArguments()[0];
                ciudadano.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).merge(any(Ciudadano.class));

        ciudadanoDAO.modificarCiudadano(ciudadano);
        Mockito.verify(entityManager).merge(ciudadano);
    }

    @Test
    public void testEliminarCiudadano() {
        Ciudadano ciudadano = new Ciudadano();
        int id = 1 ;
        ciudadano.setIdCiudadano(id);
        ciudadano.setCedula("12455");

        when(entityManager.find(Ciudadano.class,id)).thenReturn(ciudadano);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Ciudadano ciudadano = (Ciudadano) invocation.getArguments()[0];
                ciudadano.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).remove(any(Ciudadano.class));

        ciudadanoDAO.eliminiarCiudadano(id);
        Mockito.verify(entityManager).remove(ciudadano);
    }


    @Test
    public void testBuscarCiudadanoPorId() {
        Ciudadano ciudadano = new Ciudadano();
        int id = 1 ;
        ciudadano.setIdCiudadano(id);
        ciudadano.setCedula("12455");
        when(entityManager.find(Ciudadano.class,id)).thenReturn(ciudadano);

        Ciudadano ciudadanoResult = ciudadanoDAO.buscarCiudadanoPorId(id);
        assertTrue(ciudadanoResult != null);
        assertEquals(ciudadano.getCedula(), ciudadanoResult.getCedula());
    }
    @Test
    public void testBuscarCiudadanoPorCedula() {
        Ciudadano ciudadano = new Ciudadano();
        int id = 1 ;
        String cedula = "12354";
        ciudadano.setIdCiudadano(id);
        ciudadano.setCedula(cedula);

        TypedQuery<Ciudadano> query = (TypedQuery<Ciudadano>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Ciudadano WHERE cedula = :cedula")).thenReturn(query);
        when(query.setParameter("cedula", cedula)).thenReturn(query);
        when(query.getSingleResult()).thenReturn(ciudadano);
        Ciudadano ciudadanoResult = ciudadanoDAO.buscarCiudadanoPorCedula(cedula);

        assertEquals(ciudadano.getCedula(), ciudadanoResult.getCedula());

    }
    @Test
    public void testBuscarCiudadanoPorCedula_NoExistente() {

        String cedula = "123456789";
        TypedQuery<Ciudadano> query = (TypedQuery<Ciudadano>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Ciudadano WHERE cedula = :cedula")).thenReturn(query);
        when(query.setParameter("cedula", cedula)).thenReturn(query);
        when(query.getSingleResult()).thenThrow(NoResultException.class);

        Ciudadano result = ciudadanoDAO.buscarCiudadanoPorCedula(cedula);

        assertEquals(null, result);


    }
}