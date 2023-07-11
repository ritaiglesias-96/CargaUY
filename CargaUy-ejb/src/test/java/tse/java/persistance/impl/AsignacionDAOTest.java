package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Asignacion;
import tse.java.entity.GuiaDeViaje;

import javax.persistence.EntityManager;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AsignacionDAOTest {
    @InjectMocks
    AsignacionDAO asignacionDAO;


    @Mock
    private EntityManager entityManager;

    @Test
    public void testAltaAsignacion() {
        Asignacion asignacion = new Asignacion();
        asignacion.setId(1);
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        asignacionDTO.setId(1);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Asignacion asignacion = (Asignacion) invocation.getArguments()[0];
                asignacion.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Asignacion.class));

        asignacionDAO.altaAsignacion(asignacionDTO);
        Mockito.verify(entityManager).persist(any(Asignacion.class));
    }

    @Test
    public void testModificarAsignacion() {
        Asignacion asignacion = new Asignacion();
        asignacion.setId(1);
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        asignacionDTO.setId(1);

        when(entityManager.find(Asignacion.class, asignacion.getId())).thenReturn(asignacion);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Asignacion asignacion = (Asignacion) invocation.getArguments()[0];
                asignacion.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Asignacion.class));

        asignacionDAO.modificarAsignacion(asignacionDTO);
        Mockito.verify(entityManager).persist(any(Asignacion.class));
    }

    @Test
    public void testBuscarAsignacion() {
        Integer id = 1;
        Asignacion asignacion = new Asignacion();
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setFecha(new Date(2000));
        asignacion.setId(id);
        asignacion.setGuia(guiaDeViaje);


        when(entityManager.find(Asignacion.class, id)).thenReturn(asignacion);
        AsignacionDTO asignacionResult = asignacionDAO.buscarAsignacion(id);
        assertTrue(asignacionResult != null);
        assertEquals(asignacion.getId(), asignacionResult.getId());
    }
}