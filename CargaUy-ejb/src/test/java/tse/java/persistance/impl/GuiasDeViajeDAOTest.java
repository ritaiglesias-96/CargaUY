package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.GuiaDeViaje;

public class GuiasDeViajeDAOTest {

    @Mock
    GuiasDeViajeDAO dao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAltaGuiaDeViaje() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        dao.altaGuiaDeViaje(guiaDeViajeDTO);
        Mockito.verify(dao).altaGuiaDeViaje(guiaDeViajeDTO);
    }

    @Test
    public void testModificarGuiaDeViaje() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        dao.modificarGuiaDeViaje(guiaDeViajeDTO);
        Mockito.verify(dao).modificarGuiaDeViaje(guiaDeViajeDTO);
    }

    @Test
    public void testBorrarGuiaDeViaje() {
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        dao.borrarGuiaDeViaje(guiaDeViajeDTO.getId());
        Mockito.verify(dao).borrarGuiaDeViaje(guiaDeViajeDTO.getId());
    }

    @Test
    public void testBuscarGuiaDeViaje() {
        dao.buscarGuiaDeViaje(100L);
        Mockito.verify(dao).buscarGuiaDeViaje(100L);
    }

    //TODO TERMINAR, VER SI ES ESTO LO Q HAY Q HACER


}
