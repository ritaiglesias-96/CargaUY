package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.ChoferDTO;
import tse.java.dto.ResponsableDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.entity.Responsable;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ResponsableDAOTest {

    @InjectMocks
    ResponsableDAO responsableDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testFindAll() {
        List<Responsable> responsables = new ArrayList<>();
        Responsable responsable = new Responsable();
        responsable.setCedula("46634");
        responsables.add(responsable);

        TypedQuery<Responsable> query = (TypedQuery<Responsable>) Mockito.mock(TypedQuery.class);

        when(entityManager.createNativeQuery("select * from public.\"ciudadano\" where rol='Responsable'", Ciudadano.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(responsables);

        List<Responsable> responsablesResult = responsableDAO.findAll();

        assertEquals(responsables.size(), responsablesResult.size());
    }

    @Test
    public void testAgregarResponsable() {
        Responsable responsable = new Responsable();
        responsable.setCedula("46634");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Responsable responsable = (Responsable) invocation.getArguments()[0];
                responsable.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).persist(any(Responsable.class));

        responsableDAO.agregarResponsable(responsable);
        Mockito.verify(entityManager).persist(responsable);
    }

    @Test
    public void testModificarResponsable() {
        Responsable responsable = new Responsable();
        responsable.setCedula("46634");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Responsable responsable = (Responsable) invocation.getArguments()[0];
                responsable.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).merge(any(Responsable.class));

        responsableDAO.modificarResponsable(responsable);
        Mockito.verify(entityManager).merge(responsable);
    }

    @Test
    public void testEliminarResponsable() {
        Responsable responsable = new Responsable();
        responsable.setIdCiudadano(1);
        responsable.setCedula("46634");

        when(entityManager.find(Responsable.class, responsable.getIdCiudadano())).thenReturn(responsable);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Responsable responsable = (Responsable) invocation.getArguments()[0];
                responsable.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).remove(any(Responsable.class));

        responsableDAO.eliminiarResponsable(responsable);
        Mockito.verify(entityManager).remove(responsable);
    }

    @Test
    public void testAsignarEmpresa() {
        Responsable responsable = new Responsable();
        Empresa empresa = new Empresa();
        responsable.setIdCiudadano(1);
        responsable.setCedula("1241");

        when(entityManager.find(Responsable.class, responsable.getIdCiudadano())).thenReturn(responsable);
        when(entityManager.find(Empresa.class,empresa.getId())).thenReturn(empresa);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        responsableDAO.asignarEmpresa(responsable.getIdCiudadano(), empresa);
        Mockito.verify(entityManager).merge(empresa);
    }

    @Test
    public void testEliminarEmpresa() {
        Responsable responsable = new Responsable();
        Empresa empresa = new Empresa();
        responsable.setIdCiudadano(1);
        responsable.setCedula("1241");

        when(entityManager.find(Responsable.class, responsable.getIdCiudadano())).thenReturn(responsable);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        responsableDAO.eliminarEmpresa(responsable.getIdCiudadano(), empresa);
        Mockito.verify(entityManager).merge(empresa);
    }

    @Test
    public void testBuscarResponsablePorCedula() {
        Responsable responsable = new Responsable();
        responsable.setIdCiudadano(1);
        String cedula = "1241";
        responsable.setCedula(cedula);

        TypedQuery<Responsable> query = (TypedQuery<Responsable>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(responsable));

        ResponsableDTO responsableDTO = responsableDAO.buscarResponsablePorCedula(cedula);
        assertEquals(responsable.getCedula(),responsableDTO.getCedula());
    }
    @Test
    public void testBuscarResponsablePorCedulaEmpty() {
        List<Responsable> responsables = new ArrayList<>();
        Responsable responsable = new Responsable();
        responsable.setIdCiudadano(1);
        String cedula = "1241";
        responsable.setCedula(cedula);

        TypedQuery<Responsable> query = (TypedQuery<Responsable>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(responsables);

        ResponsableDTO responsableDTO = responsableDAO.buscarResponsablePorCedula(cedula);
        assertNull(responsableDTO);

    }
}