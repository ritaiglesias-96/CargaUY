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
import tse.java.entity.Pesaje;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;

@RunWith(MockitoJUnitRunner.class)
public class PesajeDAOTest {

    @InjectMocks
    PesajeDAO pesajeDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testAltaPesaje() {
    /*    PesajeDTO pesajeDTO = new PesajeDTO();
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
        Mockito.verify(entityManager).persist(pesajeDTO);
*/
    }
}