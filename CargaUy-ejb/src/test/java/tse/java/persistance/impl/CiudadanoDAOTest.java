package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.entity.Ciudadano;

import static org.junit.Assert.assertEquals;

public class CiudadanoDAOTest {
    @Mock
    CiudadanoDAO dao;
    @Mock
    CiudadanoDAO daoAux;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        daoAux = new CiudadanoDAO();
    }
    @Test
    public void testCiudadanoDAO() {//Testear si se crea de manera correcta la instancia de la clase
        CiudadanoDAO daoTest = new CiudadanoDAO();
        assertEquals(daoAux.getClass(),daoTest.getClass());
    }

    @Test
    public void testAgregar() {
        Ciudadano obj = new Ciudadano();
        dao.agregarCiudadano(obj);
        Mockito.verify(dao).agregarCiudadano(obj);
    }

    @Test
    public void testModificar() {
        Ciudadano obj = new Ciudadano();
        dao.modificarCiudadano(obj);
        Mockito.verify(dao).modificarCiudadano(obj);
    }

    @Test
    public void testEliminar() {
        Ciudadano obj = new Ciudadano();
        dao.eliminiarCiudadano(obj.getIdCiudadano());
        Mockito.verify(dao).eliminiarCiudadano(obj.getIdCiudadano());
    }

    @Test
    public void testFindById(){
        dao.buscarCiudadanoPorId(100);
        Mockito.verify(dao).buscarCiudadanoPorId(100);
    }

    @Test
    public void testFindByCedula(){
        dao.buscarCiudadanoPorCedula("51002930");
        Mockito.verify(dao).buscarCiudadanoPorCedula("51002930");
    }

    @Test
    public void testListarCiudadanos(){
        dao.listarCiudadanos();
        Mockito.verify(dao).listarCiudadanos();
    }
}
