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
import tse.java.entity.*;

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
public class ChoferDAOTest {

    @InjectMocks
    ChoferDAO choferDAO;

    @Mock
    private EntityManager entityManager;


    @Test
    public void testFindAll() {
        List<Chofer> choferes = new ArrayList<>();
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setCedula("1241");
        choferes.add(chofer);

        TypedQuery<Chofer> query = (TypedQuery<Chofer>) Mockito.mock(TypedQuery.class);

        when(entityManager.createNativeQuery("select * from public.\"ciudadano\" where rol='Chofer'", Ciudadano.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(choferes);

        List<Chofer> choferesResult = choferDAO.findAll();

        assertEquals(choferes.size(), choferesResult.size());
    }

    @Test
    public void testAgregarChofer() {
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setCedula("1241");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Chofer chofer = (Chofer) invocation.getArguments()[0];
                chofer.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).persist(any(Chofer.class));

        choferDAO.agregarChofer(chofer);
        Mockito.verify(entityManager).persist(chofer);
    }

    @Test
    public void testModificarChofer() {
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setCedula("1241");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Chofer chofer = (Chofer) invocation.getArguments()[0];
                chofer.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).merge(any(Chofer.class));

        choferDAO.modificarChofer(chofer);
        Mockito.verify(entityManager).merge(chofer);
    }

    @Test
    public void testEliminarChofer() {
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setCedula("1241");

        when(entityManager.find(Chofer.class, chofer.getIdCiudadano())).thenReturn(chofer);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Chofer chofer = (Chofer) invocation.getArguments()[0];
                chofer.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).remove(any(Chofer.class));

        choferDAO.eliminiarChofer(chofer);
        Mockito.verify(entityManager).remove(chofer);
    }

    @Test
    public void testAsignarEmpresaChofer() {
        Chofer chofer = new Chofer();
        Empresa empresa = new Empresa();
        chofer.setIdCiudadano(1);
        chofer.setCedula("1241");

        when(entityManager.find(Chofer.class, chofer.getIdCiudadano())).thenReturn(chofer);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        choferDAO.asignarEmpresaChofer(chofer.getIdCiudadano(), empresa);
        Mockito.verify(entityManager).merge(empresa);
    }

    @Test
    public void testEliminarEmpresaChofer() {
        Chofer chofer = new Chofer();
        Empresa empresa = new Empresa();
        chofer.setIdCiudadano(1);
        chofer.setCedula("1241");

        when(entityManager.find(Chofer.class, chofer.getIdCiudadano())).thenReturn(chofer);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        choferDAO.eliminarEmpresaChofer(chofer.getIdCiudadano(), empresa);
        Mockito.verify(entityManager).merge(empresa);
    }

    @Test
    public void testBuscarChoferPorCedula() {
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        String cedula = "1241";
        chofer.setCedula(cedula);

        TypedQuery<Chofer> query = (TypedQuery<Chofer>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(chofer));

        ChoferDTO choferDTO = choferDAO.buscarChoferPorCedula(cedula);
        assertEquals(chofer.getCedula(),choferDTO.getCedula());
    }
    @Test
    public void testBuscarChoferPorCedulaEmpty() {
        List<Chofer> choferes = new ArrayList<>();
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        String cedula = "1241";
        chofer.setCedula(cedula);

        TypedQuery<Chofer> query = (TypedQuery<Chofer>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(choferes);

        ChoferDTO choferDTO = choferDAO.buscarChoferPorCedula(cedula);
        assertNull(choferDTO);
    }
   /* @Test
    public void testBuscarChoferPorCedulaNotChofer() {
        Chofer chofer = new Chofer();
        Ciudadano ciudadano = new Ciudadano();
        chofer.setIdCiudadano(1);
        String cedula = "1241";
        chofer.setCedula(cedula);

        TypedQuery<Chofer> query = (TypedQuery<Chofer>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(chofer));
        when(query.getResultList().get(0)).thenReturn(funcionario);

        ChoferDTO choferDTO = choferDAO.buscarChoferPorCedula(cedula);
        assertNull(choferDTO);
    }*/

}