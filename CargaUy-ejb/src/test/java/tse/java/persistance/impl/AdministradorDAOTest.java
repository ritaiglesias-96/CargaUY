package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.entity.Administrador;

public class AdministradorDAOTest {
    @Mock
    private AdministradorDAO dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPersist() {
        Administrador administrador = new Administrador();
        dao.persist(administrador);
        Mockito.verify(dao).persist(administrador);
    }

    @Test
    public void testMerge() {
        Administrador administrador = new Administrador();
        dao.merge(administrador);
        Mockito.verify(dao).merge(administrador);
    }

    @Test
    public void testDelete(){
        Administrador administrador = new Administrador();
        dao.delete(administrador);
        Mockito.verify(dao).delete(administrador);
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
