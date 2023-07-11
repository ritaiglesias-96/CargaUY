package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;
import tse.java.persistance.IRubrosDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RubrosServiceTest {
    @Mock
    private IRubrosDAO rubrosDAO;

    @InjectMocks
    private RubrosService rubrosService;
    @Test
    public void listarRubrosTest() {
        List<RubroDTO> rubros = new ArrayList<>();
        rubros.add(new RubroDTO());
        rubros.add(new RubroDTO());

        when(rubrosDAO.listarRubros()).thenReturn(rubros);

        List<RubroDTO> resultado = rubrosService.listarRubros();

        Mockito.verify(rubrosDAO, times(1)).listarRubros();

        assertEquals(rubros, resultado);
    }

    @Test
    public void altaRubroTest() throws RubroExisteException {

        RubroDTO rubro = new RubroDTO();

        rubrosService.altaRubro(rubro);

        Mockito.verify(rubrosDAO, times(1)).altaRubro(rubro);
    }

    @Test
    public void modificarRubroTest() throws RubroExisteException {
        RubroDTO rubro = new RubroDTO();

        rubrosService.modificarRubro(rubro);

        Mockito.verify(rubrosDAO, times(1)).modificarRubro(rubro);
    }

    @Test
    public void eliminarRubroTest() {
        Long id = 1L;

        rubrosService.eliminarRubro(id);

        Mockito.verify(rubrosDAO, times(1)).borrarRubro(id);
    }

    @Test
    public void buscarRubroTest() {
        Long id = 1L;
        RubroDTO rubro = new RubroDTO();

        when(rubrosDAO.buscarRubroPorId(id)).thenReturn(rubro);

        RubroDTO resultado = rubrosService.buscarRubro(id);

        Mockito.verify(rubrosDAO, times(1)).buscarRubroPorId(id);

        assertEquals(rubro, resultado);
    }
}
