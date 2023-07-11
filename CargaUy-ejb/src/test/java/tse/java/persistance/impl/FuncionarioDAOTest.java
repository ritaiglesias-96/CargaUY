package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.entity.Administrador;
import tse.java.entity.Ciudadano;
import tse.java.entity.Funcionario;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class FuncionarioDAOTest {

    @InjectMocks
    FuncionarioDAO funcionarioDAO;
    @Mock
    private EntityManager entityManager;
    @Test
    public void findAll() {
        List<Funcionario> funcionarios = new ArrayList<>();

        Funcionario funcionario = new Funcionario();
        Funcionario funcionario2 = new Funcionario();

        funcionario.setIdCiudadano(1);
        funcionario.setCedula("879861");
        funcionario.setEmail("emailaaa");

        funcionario2.setIdCiudadano(2);
        funcionario2.setCedula("23214");
        funcionario2.setEmail("mail.aaa");

        funcionarios.add(funcionario);
        funcionarios.add(funcionario2);

        TypedQuery<Funcionario> query = (TypedQuery<Funcionario>) Mockito.mock(TypedQuery.class);

        when(entityManager.createNativeQuery("select * from public.\"ciudadano\" where rol='Funcionario'", Ciudadano.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(funcionarios);

        List<Funcionario> funcionariosResult = funcionarioDAO.findAll();

        assertEquals(funcionariosResult.size(), funcionarios.size());
    }

    @Test
    public void agregarFuncionario() {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdCiudadano(1);
        funcionario.setCedula("879861");
        funcionario.setEmail("emailaaa");

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Funcionario funcionario = (Funcionario) invocation.getArguments()[0];
                funcionario.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).persist(any(Funcionario.class));

        funcionarioDAO.agregarFuncionario(funcionario);
        Mockito.verify(entityManager).persist(funcionario);

    }

    @Test
    public void modificarFuncionario() {

        Funcionario funcionario = new Funcionario();
        funcionario.setIdCiudadano(1);
        funcionario.setCedula("879861");
        funcionario.setEmail("emailaaa");
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Funcionario funcionario = (Funcionario) invocation.getArguments()[0];
                funcionario.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).merge(any(Funcionario.class));

        funcionarioDAO.modificarFuncionario(funcionario);
        Mockito.verify(entityManager).merge(funcionario);
    }

    @Test
    public void eliminiarFuncionario() {
        Funcionario funcionario = new Funcionario();
        int id = 1;
        funcionario.setIdCiudadano(id);
        funcionario.setCedula("879861");
        funcionario.setEmail("emailaaa");

        when(entityManager.find(Funcionario.class,id)).thenReturn(funcionario);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Funcionario funcionario = (Funcionario) invocation.getArguments()[0];
                funcionario.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).remove(any(Funcionario.class));

        funcionarioDAO.eliminiarFuncionario(funcionario);
        Mockito.verify(entityManager).remove(funcionario);
    }

}