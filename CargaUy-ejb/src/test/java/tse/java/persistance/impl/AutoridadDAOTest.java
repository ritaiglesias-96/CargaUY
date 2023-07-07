package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;

public class AutoridadDAOTest {

    @Mock
    private AutoridadDAO dao;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPersist() {
        Autoridad autoridad = new Autoridad();
        dao.persist(autoridad);
        Mockito.verify(dao).persist(autoridad);
    }

    @Test
    public void testMerge() {
        Autoridad autoridad = new Autoridad();
        dao.merge(autoridad);
        Mockito.verify(dao).merge(autoridad);
    }

    @Test
    public void testDelete(){
        Autoridad autoridad = new Autoridad();
        dao.delete(autoridad);
        Mockito.verify(dao).delete(autoridad);
    }

    @Test
    public void testFindById(){
        dao.findById(100);
        Mockito.verify(dao).findById(100);
    }

    @Test
    public void testFindAll(){
        dao.findAll();
        Mockito.verify(dao).findAll();
    }



}
