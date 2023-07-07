package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.entity.Funcionario;

public class FuncionarioDAOTest {

    @Mock
    FuncionarioDAO dao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAgregarFuncionario() {
        Funcionario funcionario = new Funcionario();
        dao.agregarFuncionario(funcionario);
        Mockito.verify(dao).agregarFuncionario(funcionario);
    }

    @Test
    public void testModificarFuncionario() {
        Funcionario funcionario = new Funcionario();
        dao.modificarFuncionario(funcionario);
        Mockito.verify(dao).modificarFuncionario(funcionario);
    }

    @Test
    public void testEliminarFuncionario() {
        Funcionario funcionario = new Funcionario();
        dao.eliminiarFuncionario(funcionario);
        Mockito.verify(dao).eliminiarFuncionario(funcionario);
    }

    @Test
    public void testFindAll() {
        dao.findAll();
        Mockito.verify(dao).findAll();
    }
}
