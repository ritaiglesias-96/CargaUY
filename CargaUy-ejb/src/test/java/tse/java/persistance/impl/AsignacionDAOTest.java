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
import javax.persistence.TypedQuery;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @Test
    public void testListarAsignaciones() {
        List<Asignacion> asignaciones = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.of(2023, 7, 9, 10, 30, 0);
        Asignacion asignacion = new Asignacion();
        Asignacion asignacion2 = new Asignacion();

        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();

        guiaDeViaje.setFecha(new Date(2000));


        asignacion.setId(1);
        asignacion2.setId(2);

        asignacion.setGuia(guiaDeViaje);
        asignacion2.setGuia(guiaDeViaje);

        asignacion.setFechaCambio(localDateTime);
        asignacion2.setFechaCambio(localDateTime);

        asignaciones.add(asignacion);
        asignaciones.add(asignacion2);

        TypedQuery<Asignacion> query = (TypedQuery<Asignacion>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select a from Asignacion a")).thenReturn(query);

        when(query.getResultList()).thenReturn(asignaciones);

        List<AsignacionDTO> asignacionesResult = asignacionDAO.listarAsignaciones();

        assertEquals(asignaciones.size(), asignacionesResult.size());

    }

    @Test
    public void testBorrarAsignacion() {
        Asignacion asignacion = new Asignacion();
        asignacion.setId(1);

        when(entityManager.find(Asignacion.class, asignacion.getId())).thenReturn(asignacion);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Asignacion asignacion = (Asignacion) invocation.getArguments()[0];
                asignacion.setId(1);
                return null;
            }
        }).when(entityManager).remove(any(Asignacion.class));

        asignacionDAO.borrarAsignacion(asignacion.getId());
        Mockito.verify(entityManager).remove(asignacion);
    }

    @Test
    public void testUltimaIngresada() {
        Asignacion asignacion = new Asignacion();
        LocalDateTime localDateTime = LocalDateTime.of(2023, 7, 9, 10, 30, 0);
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setFecha(new Date(2000));
        asignacion.setId(1);
        asignacion.setGuia(guiaDeViaje);
        asignacion.setFechaCambio(localDateTime);

        TypedQuery<Asignacion> query = (TypedQuery<Asignacion>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select a from Asignacion a WHERE id=(SELECT max(id) FROM Asignacion )")).thenReturn(query);

        when(query.getResultList()).thenReturn(Collections.singletonList(asignacion));
        AsignacionDTO asignacionResult = asignacionDAO.ultimaIngresada();

        assertEquals(asignacion.getId(), asignacionResult.getId());
    }

}