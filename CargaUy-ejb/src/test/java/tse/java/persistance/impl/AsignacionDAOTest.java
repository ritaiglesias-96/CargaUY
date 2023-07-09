package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.dto.AsignacionDTO;
import tse.java.entity.Asignacion;

public class AsignacionDAOTest {

    @Mock
    private AsignacionDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAltaAsignacion() {
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        dao.altaAsignacion(asignacionDTO);
        Mockito.verify(dao).altaAsignacion(asignacionDTO);
    }

    @Test
    public void testModificarAsignacion() {
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        dao.modificarAsignacion(asignacionDTO);
        Mockito.verify(dao).modificarAsignacion(asignacionDTO);
    }

    @Test
    public void testBorrarAsignacion() {
        AsignacionDTO asignacionDTO = new AsignacionDTO();
        dao.borrarAsignacion(asignacionDTO.getId());
        Mockito.verify(dao).borrarAsignacion(asignacionDTO.getId());
    }

    @Test
    public void testBuscarAsignacion() {
        dao.buscarAsignacion(100L);
        Mockito.verify(dao).buscarAsignacion(100L);
    }

    @Test
    public void testListarAsignaciones() {
        dao.listarAsignaciones();
        Mockito.verify(dao).listarAsignaciones();
    }
}
