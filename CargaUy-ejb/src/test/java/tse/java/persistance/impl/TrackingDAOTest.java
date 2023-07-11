package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.TrackingDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Tracking;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrackingDAOTest {

    @InjectMocks
    TrackingDAO trackingDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void listAll() {
        List<Tracking> trackings = new ArrayList<>();
        Tracking tracking = new Tracking();
        String matricula = "AXCAER", pais = "URU";
        tracking.setId(1L);
        tracking.setPais(pais);
        tracking.setMatricula(matricula);
        trackings.add(tracking);
        Integer a = 0, b= 1;

        TypedQuery<Tracking> query = (TypedQuery<Tracking>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("FROM Tracking", Tracking.class)).thenReturn(query);
        System.out.println(query);
        when(query.getResultList()).thenReturn(trackings);

        List<Tracking> trackingsResults = trackingDAO.listAll(a, b);

        assertEquals(trackingsResults.size(),trackings.size());

    }

    @Test
    public void find() {
        List<Tracking> trackings = new ArrayList<>();
        Tracking tracking = new Tracking();
        LocalDateTime localDateTime = LocalDateTime.of(2023, 7, 9, 10, 30, 0);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        tracking.setTimeStamp(localDateTime);
        String matricula = "AXCAER", pais = "URU";
        tracking.setId(1L);
        tracking.setPais(pais);
        tracking.setMatricula(matricula);
        trackings.add(tracking);

        TypedQuery<Tracking> query = (TypedQuery<Tracking>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select t from Tracking t where t.matricula='" + matricula + "' and t.pais='" + pais + "'")).thenReturn(query);
        System.out.println(query);
        when(query.getResultList()).thenReturn(trackings);

        TrackingDTO trackingResult = trackingDAO.find(matricula,pais);

        assertEquals(tracking.getPais(),trackingResult.getPais());
    }

    @Test
    public void findThrowIsEmpty() {
        List<Tracking> trackings = new ArrayList<>();
        List<Tracking> listaVacia = new ArrayList<>();
        Tracking tracking = new Tracking();
        LocalDateTime localDateTime = LocalDateTime.of(2023, 7, 9, 10, 30, 0);
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        tracking.setTimeStamp(localDateTime);
        String matricula = "AXCAER", pais = "URU";
        tracking.setId(1L);
        tracking.setPais(pais);
        tracking.setMatricula(matricula);
        trackings.add(tracking);

        TypedQuery<Tracking> query = (TypedQuery<Tracking>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select t from Tracking t where t.matricula='" + matricula + "' and t.pais='" + pais + "'")).thenReturn(query);
        System.out.println(query);
        when(query.getResultList()).thenReturn(listaVacia);

        TrackingDTO trackingResult = trackingDAO.find(matricula,pais);

        assertNull(trackingResult);
    }

    @Test
    public void create() {
        Tracking tracking = new Tracking();
        tracking.setId(1L);
        tracking.setPais("URU");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Tracking tracking = (Tracking) invocation.getArguments()[0];
                tracking.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(Tracking.class));
        trackingDAO.create(tracking);
        Mockito.verify(entityManager).persist(tracking);
    }

    @Test
    public void update() {
        Tracking tracking = new Tracking();
        tracking.setId(1L);
        tracking.setPais("URU");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Tracking tracking = (Tracking) invocation.getArguments()[0];
                tracking.setId(1L);
                return null;
            }
        }).when(entityManager).merge(any(Tracking.class));
        trackingDAO.update(tracking);
        Mockito.verify(entityManager).merge(tracking);
    }
}