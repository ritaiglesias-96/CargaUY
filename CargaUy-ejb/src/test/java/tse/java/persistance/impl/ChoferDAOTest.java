package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.entity.Chofer;
import tse.java.entity.Empresa;

public class ChoferDAOTest {

    @Mock
    private ChoferDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAgregarChofer() {
        Chofer chofer = new Chofer();
        dao.agregarChofer(chofer);
        Mockito.verify(dao).agregarChofer(chofer);
    }

    @Test
    public void testModificarChofer() {
        Chofer chofer = new Chofer();
        dao.modificarChofer(chofer);
        Mockito.verify(dao).modificarChofer(chofer);
    }

    @Test
    public void testEliminarChofer() {
        Chofer chofer = new Chofer();
        dao.eliminiarChofer(chofer);
        Mockito.verify(dao).eliminiarChofer(chofer);
    }

    @Test
    public void testAsignarEmpresaChofer() {
        Chofer chofer = new Chofer();
        Empresa empresa = new Empresa();
        dao.asignarEmpresaChofer(chofer.getIdCiudadano(),empresa);
        Mockito.verify(dao).asignarEmpresaChofer(chofer.getIdCiudadano(),empresa);
    }

    @Test
    public void testEliminarEmpresaChofer() {
        Chofer chofer = new Chofer();
        Empresa empresa = new Empresa();
        dao.eliminarEmpresaChofer(chofer.getIdCiudadano(),empresa);
        Mockito.verify(dao).eliminarEmpresaChofer(chofer.getIdCiudadano(),empresa);
    }

    @Test
    public void testBuscarChoferPorCedula() {
        dao.buscarChoferPorCedula("55002931");
        Mockito.verify(dao).buscarChoferPorCedula("55002931");
    }

    @Test
    public void testFindAll() {
        dao.findAll();
        Mockito.verify(dao).findAll();
    }
}
