package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.RubroDTO;
import tse.java.entity.Ciudadano;
import tse.java.entity.Rubro;
import tse.java.exception.RubroExisteException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RubrosDAOTest {

    @InjectMocks
    RubrosDAO rubrosDAO;

    @Mock
    EntityManager entityManager;

    @Test
    public void testAltaRubro() throws RubroExisteException {
        RubroDTO rubroDTO = new RubroDTO();
        List<Rubro> rubros = new ArrayList<>();
        Rubro rubro = new Rubro();
        rubros.add(rubro);
        TypedQuery<Rubro> query = (TypedQuery<Rubro>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select r from Rubro r where r.nombre='" + rubro.getNombre() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Rubro rubro = (Rubro) invocation.getArguments()[0];
                rubro.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(Rubro.class));

        rubrosDAO.altaRubro(rubroDTO);
        Mockito.verify(entityManager).persist(any(Rubro.class));
    }
  /*  @Test
    public void testAltaRubroRubroExistenteConId() throws RubroExisteException {
        RubroDTO rubroDTO = new RubroDTO();
        rubroDTO.setId(1L);
        List<Rubro> rubros = new ArrayList<>();
        Rubro rubro = new Rubro();
        rubro.setId(1L);
        rubros.add(rubro);
        when(entityManager.find(Rubro.class,1L)).thenReturn(rubro);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Rubro rubro = (Rubro) invocation.getArguments()[0];
                rubro.setId(1L);
                return null;
            }
        }).when(entityManager).persist(any(Rubro.class));

        rubrosDAO.altaRubro(rubroDTO);

    }*/
 /*   @Test
    public void testAltaRubroRubroExistenteConNombre() throws RubroExisteException {
        RubroDTO rubroDTO = new RubroDTO();
        List<Rubro> rubros = new ArrayList<>();
        Rubro rubro = new Rubro();
        rubros.add(rubro);
        TypedQuery<Rubro> query = (TypedQuery<Rubro>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select r from Rubro r where r.nombre='" + rubro.getNombre() + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(rubros);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws RubroExisteException {
                throw new RubroExisteException("Existe");
            }
        }).when(entityManager).persist(any(Rubro.class));

        rubrosDAO.altaRubro(rubroDTO);

    }

*/

    @Test
    public void testListarRubros() {
        List<RubroDTO> rubrosDTO = new ArrayList<>();
        Rubro rubro = new Rubro();
        rubro.setId(1L);
        rubro.setNombre("hola");
        RubroDTO rubroDTO = new RubroDTO();
        rubroDTO.setId(1L);
        rubroDTO.setNombre("hola");
        rubrosDTO.add(rubroDTO);

        TypedQuery<Rubro> query = (TypedQuery<Rubro>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select r from Rubro r")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(rubro));

        List<RubroDTO> rubrosRetorno = rubrosDAO.listarRubros();

        assertEquals(rubro.getNombre(),rubrosRetorno.get(0).getNombre());
    }

    @Test
    public void testBorrarRubro() {
        Rubro rubro = new Rubro();
        rubro.setNombre("hola");
        rubro.setId(1L);
        when(entityManager.find(Rubro.class,rubro.getId())).thenReturn(rubro);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws RubroExisteException {
                Rubro rubro = (Rubro) invocation.getArguments()[0];
                rubro.setId(1L);
                return null;
            }
        }).when(entityManager).remove(any(Rubro.class));

        rubrosDAO.borrarRubro(rubro.getId());
        Mockito.verify(entityManager).remove(any(Rubro.class));

    }

 /*   @Test
    public void testModificarRubro() throws RubroExisteException {
        RubroDTO rubroDTO = new RubroDTO();
        List<Rubro> rubros = new ArrayList<>();
        Rubro rubro = new Rubro();
        rubroDTO.setNombre("Hola");
        rubros.add(rubro);
        TypedQuery<Rubro> query = (TypedQuery<Rubro>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select r from Rubro r where r.nombre='" + null + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        when(entityManager.find(Rubro.class,1)).thenReturn(rubro);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Rubro rubro = (Rubro) invocation.getArguments()[0];
                rubro.setNombre("Hola");
                return null;
            }
        }).when(entityManager).merge(any(Rubro.class));

        rubrosDAO.modificarRubro(rubroDTO);
        Mockito.verify(entityManager).merge(any(Rubro.class));
    }*/
}