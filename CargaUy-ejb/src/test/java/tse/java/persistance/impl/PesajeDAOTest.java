package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.PesajeDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Ciudadano;
import tse.java.entity.Pesaje;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PesajeDAOTest {

    @InjectMocks
    PesajeDAO pesajeDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testAltaPesaje() {
        PesajeDTO pesajeDTO = new PesajeDTO();
        pesajeDTO.setId(1L);
        pesajeDTO.setCarga(23F);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Pesaje pesaje = (Pesaje) invocation.getArguments()[0];
                pesaje.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(Pesaje.class));

        pesajeDAO.altaPesaje(pesajeDTO);
        Mockito.verify(entityManager).persist(any(Pesaje.class));
    }

    @Test
    public void testBuscarPesaje() {
        Long id = 1L;
        Pesaje pesaje = new Pesaje();
        pesaje.setId(id);
        when(entityManager.find(Pesaje.class, id)).thenReturn(pesaje);

        PesajeDTO pesajeRetorno = pesajeDAO.buscarPesaje(id);

        assertEquals(pesaje.getId(),pesajeRetorno.getId());
    }

    @Test
    public void testListarPesajes() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 11, 15, 30, 0);
        List<Pesaje> pesajes = new ArrayList<>();
        Pesaje pesaje = new Pesaje();
        pesaje.setCarga(200);
        pesaje.setId(1L);
        pesaje.setFecha(dateTime);
        pesajes.add(pesaje);

        TypedQuery<Pesaje> query = (TypedQuery<Pesaje>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select p from Pesaje p")).thenReturn(query);
        when(query.getResultList()).thenReturn(pesajes);

        List<PesajeDTO> pesajesRetorno = pesajeDAO.listarPesajes();

        assertEquals(pesajes.size(),pesajesRetorno.size());
    }

    @Test
    public void testBorrarPesaje() {
        Long id = 1L;
        Pesaje pesaje = new Pesaje();
        pesaje.setId(id);
        when(entityManager.find(Pesaje.class, id)).thenReturn(pesaje);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Pesaje pesaje = (Pesaje) invocation.getArguments()[0];
                pesaje.setId(1L);
                return null;
            }
        }).when(entityManager).remove(any(Pesaje.class));

        pesajeDAO.borrarPesaje(id);
        Mockito.verify(entityManager).remove(pesaje);
    }

  /*  @Test
    public void testGetLastId() {
        LocalDateTime dateTime = LocalDateTime.of(2023, 7, 11, 15, 30, 0);
        List<Pesaje> pesajes = new ArrayList<>();
        Pesaje pesaje = new Pesaje();
        pesaje.setId(2000L);
        pesaje.setCarga(200);
        pesaje.setId(1L);
        pesaje.setFecha(dateTime);
        pesajes.add(pesaje);


        TypedQuery<Pesaje> query = (TypedQuery<Pesaje>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select max(p.id) from Pesaje p where p.id<1000")).thenReturn(query);
        when(query.getResultList()).thenReturn(pesajes);

        Long idRetorno  = pesajeDAO.getLastId();

        assertEquals(pesaje.getId(),idRetorno);
    }
*/
}