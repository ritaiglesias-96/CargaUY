package tse.java.persistance.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tse.java.dto.EmpresaDTO;
import tse.java.entity.Empresa;

public class EmpresaDAOTest {

    @Mock
    private EmpresasDAO dao;



    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGuardarEmpresa() {
        Empresa empresa = new Empresa();
        dao.guardarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
        Mockito.verify(dao).guardarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
    }

    @Test
    public void testModificarEmpresa() {
        EmpresaDTO empresaDTO = new EmpresaDTO();
        dao.modificarEmpresa(empresaDTO);
        Mockito.verify(dao).modificarEmpresa(empresaDTO);
    }

    @Test
    public void testEliminarEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setId(100); //TODO ARREGLAR ESTO
        dao.eliminarEmpresa(empresa.getId());
        Mockito.verify(dao).eliminarEmpresa(empresa.getId());
    }

    @Test
    public void testObtenerEmpresaPorId() {
        dao.obtenerEmpresaPorId(100);
        Mockito.verify(dao).obtenerEmpresaPorId(100);
    }

    @Test
    public void testObtenerEmpresaPorNumero() {
        dao.obtenerEmpresaPorNumero(100);
        Mockito.verify(dao).obtenerEmpresaPorNumero(100);
    }

    @Test
    public void testObtenerEmpresas() {
        dao.obtenerEmpresas();
        Mockito.verify(dao).obtenerEmpresas();
    }

}
