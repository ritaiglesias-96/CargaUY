package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.TipoCargaDTO;
import tse.java.entity.*;
import tse.java.exception.TipoCargaExisteException;

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
public class TipoCargaDAOTest {

    @InjectMocks
    TipoCargaDAO tipoCargaDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testAltaTipoCarga() throws TipoCargaExisteException {
        TipoCarga tipoCarga = new TipoCarga();
        String nombre = "asd";
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();


        TypedQuery<TipoCarga> query = (TypedQuery<TipoCarga>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select t from TipoCarga t where t.nombre='" + tipoCargaDTO.getNombre() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        tipoCargaDAO.altaTipoCarga(tipoCargaDTO);
        Mockito.verify(entityManager).persist(any(TipoCarga.class));

    }
    /*@Test
    public void testAltaTipoCargaThrowExistente() throws TipoCargaExisteException {
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();
        TipoCarga tipocarga = new TipoCarga();
        Long id = 1L ;
        tipocarga.setNombre("hola");
        tipocarga.setId(id);
        tipoCargaDTO.setId(id);
        when(entityManager.find(TipoCarga.class, tipocarga.getId())).thenReturn(tipocarga);


        tipoCargaDAO.altaTipoCarga(tipoCargaDTO);

    }
*/

    @Test
    public void testBuscarTipoCargaPorId() {
        TipoCarga tipocarga = new TipoCarga();
        Long id = 1L ;
        tipocarga.setNombre("hola");
        tipocarga.setId(id);
        when(entityManager.find(TipoCarga.class, tipocarga.getId())).thenReturn(tipocarga);
        TipoCargaDTO tipoCargaResult = tipoCargaDAO.buscarTipoCargaPorId(id);

        assertTrue(tipoCargaResult != null);
        assertEquals(tipocarga.getNombre(), tipoCargaResult.getNombre());
    }

    @Test
    public void testBuscarTipoCargaPorIdNull() {
        TipoCarga tipocarga = new TipoCarga();
        Long id = 1L ;
        tipocarga.setNombre("hola");
        TipoCargaDTO tipoCargaResult = tipoCargaDAO.buscarTipoCargaPorId(null);

        assertFalse(tipoCargaResult != null);
    }

    @Test
    public void testBuscarTipoCargaPorNombre() {
        TipoCarga tipoCarga = new TipoCarga();
        String nombre = "asd";
        tipoCarga.setNombre(nombre);
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();

        TypedQuery<TipoCarga> query = (TypedQuery<TipoCarga>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select t from TipoCarga t where t.nombre='" + nombre + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(tipoCarga));

        TipoCargaDTO tipoCargaDTOResult = tipoCargaDAO.buscarTipoCargaPorNombre(nombre);

        assertEquals(tipoCarga.getNombre(), tipoCargaDTOResult.getNombre());

    }

    @Test
    public void testBuscarTipoCargaPorNombreEmpty() {
        List<TipoCarga> tipoCargas = new ArrayList<>();
        TipoCarga tipoCarga = new TipoCarga();
        String nombre = "asd";
        tipoCarga.setNombre(nombre);
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();

        TypedQuery<TipoCarga> query = (TypedQuery<TipoCarga>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select t from TipoCarga t where t.nombre='" + nombre + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(tipoCargas);

        TipoCargaDTO tipoCargaDTOResult = tipoCargaDAO.buscarTipoCargaPorNombre(nombre);
        assertNull(tipoCargaDTOResult);
    }

    @Test
    public void testListarTipoCarga() {
        List<TipoCarga> tipoCargas = new ArrayList<>();
        TipoCarga tipoCarga = new TipoCarga();
        String nombre = "asd";
        tipoCarga.setNombre(nombre);
        tipoCarga.setId(1L);
        tipoCargas.add(tipoCarga);

        TypedQuery<TipoCarga> query = (TypedQuery<TipoCarga>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select t from TipoCarga t")).thenReturn(query);
        when(query.getResultList()).thenReturn(tipoCargas);

        List<TipoCargaDTO> tipoCargaDTOResult = tipoCargaDAO.listarTipoCarga();

        assertEquals(tipoCargas.size(), tipoCargaDTOResult.size());

    }

    @Test
    public void testBorrarTipoCarga() {
        TipoCarga tipoCarga = new TipoCarga();
        Long id = 1L ;
        tipoCarga.setNombre("hola");
        tipoCarga.setId(id);
        when(entityManager.find(TipoCarga.class, tipoCarga.getId())).thenReturn(tipoCarga);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                TipoCarga tipoCarga = (TipoCarga) invocation.getArguments()[0];
                tipoCarga.setId(1L);
                tipoCarga.setNombre("hola");
                return null;
            }
        }).when(entityManager).remove(any(TipoCarga.class));

        tipoCargaDAO.borrarTipoCarga(id);
        Mockito.verify(entityManager).remove(tipoCarga);
    }

    @Test
    public void testModificarTipoCarga() throws TipoCargaExisteException {
        TipoCarga tipoCarga = new TipoCarga();
        String nombre = "asd";
        TipoCargaDTO tipoCargaDTO = new TipoCargaDTO();


        TypedQuery<TipoCarga> query = (TypedQuery<TipoCarga>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select t from TipoCarga t where t.nombre='" + tipoCargaDTO.getNombre() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());
        when(entityManager.find(TipoCarga.class, tipoCarga.getId())).thenReturn(tipoCarga);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                TipoCarga tipoCarga = (TipoCarga) invocation.getArguments()[0];
                tipoCarga.setId(1L);
                tipoCarga.setNombre("hola");
                return null;
            }
        }).when(entityManager).persist(any(TipoCarga.class));

        tipoCargaDAO.modificarTipoCarga(tipoCargaDTO);
        Mockito.verify(entityManager).persist(tipoCarga);

    }

}