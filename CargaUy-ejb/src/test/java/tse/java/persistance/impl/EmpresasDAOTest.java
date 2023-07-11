package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.TipoCarga;
import tse.java.entity.Vehiculo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EmpresasDAOTest {

    @InjectMocks
    EmpresasDAO empresasDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void testObtenerEmpresaPorId() {
        Empresa empresa = new Empresa();
        int id = 1 ;
        empresa.setId(id);
        when(entityManager.find(Empresa.class, empresa.getId())).thenReturn(empresa);
        EmpresaDTO empresaDTOResultado = empresasDAO.obtenerEmpresaPorId(id);

        assertNotNull(empresaDTOResultado);
        assertEquals(empresa.getId(), empresaDTOResultado.getId());
    }
    @Test
    public void testObtenerEmpresas() {
        List<Empresa> empresas = new ArrayList<>();
        Empresa empresa = new Empresa();
        empresa.setId(1);
        empresas.add(empresa);
        TypedQuery<Empresa> query = (TypedQuery<Empresa>) Mockito.mock(TypedQuery.class);

        when(entityManager.createNativeQuery("select * from public.\"Empresa\"", Empresa.class)).thenReturn(query);
        when(query.getResultList()).thenReturn(empresas);

        List<EmpresaDTO> empresasResultado = empresasDAO.obtenerEmpresas();

        assertEquals(empresas.size(), empresasResultado.size());
    }

    @Test
    public void testGuardarEmpresa() {
        String nombrePublico = "nombre", razonSocial = "razon", dirPrincipal = "direccion";
        int nroEmpresa = 3;
        Empresa empresa = new Empresa(nombrePublico, razonSocial,nroEmpresa, dirPrincipal);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Empresa.class));

        empresasDAO.guardarEmpresa(nombrePublico, razonSocial,nroEmpresa, dirPrincipal);
        Mockito.verify(entityManager).persist(any(Empresa.class));
    }

    @Test
    public void testModificarEmpresa() {
        int id = 3;
        String nombrePublico = "nombre", razonSocial = "razon", dirPrincipal = "direccion";
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(id);
        Empresa empresa = new Empresa();
        empresa.setId(id);
        empresa.setDirPrincipal(dirPrincipal);
        empresa.setRazonSocial(razonSocial);
        empresa.setNombrePublico(nombrePublico);
        when(entityManager.find(Empresa.class,id)).thenReturn(empresa);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        empresasDAO.modificarEmpresa(empresaDTO);
        Mockito.verify(entityManager).merge(any(Empresa.class));
    }

    @Test
    public void testEliminarEmpresa() {
        int id = 3;
        Empresa empresa = new Empresa();
        empresa.setId(id);
        when(entityManager.find(Empresa.class,id)).thenReturn(empresa);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).remove(any(Empresa.class));
        empresasDAO.eliminarEmpresa(id);
        Mockito.verify(entityManager).remove(any(Empresa.class));
    }

    @Test
    public void testObtenerEmpresaPorNumero() {
        List<Empresa> empresas = new ArrayList<>();
        Empresa empresa = new Empresa();
        String nombrePublico = "nombre", razonSocial = "razon", dirPrincipal = "direccion";
        int numeroEmpresa = 3;
        empresa.setId(1);
        empresa.setDirPrincipal(dirPrincipal);
        empresa.setRazonSocial(razonSocial);
        empresa.setNombrePublico(nombrePublico);
        empresa.setNroEmpresa(numeroEmpresa);
        empresas.add(empresa);
        TypedQuery<Empresa> query = (TypedQuery<Empresa>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select e from Empresa e where e.nroEmpresa=" + numeroEmpresa)).thenReturn(query);
        when(query.getResultList()).thenReturn(empresas);

        EmpresaDTO empresasRetorno = empresasDAO.obtenerEmpresaPorNumero(numeroEmpresa);
        assertEquals(empresa.getRazonSocial(), empresasRetorno.getRazonSocial());

    }
    @Test
    public void testObtenerEmpresaPorNumeroReturnNull() {
        List<Empresa> empresas = new ArrayList<>();
        Empresa empresa = new Empresa();
        String nombrePublico = "nombre", razonSocial = "razon", dirPrincipal = "direccion";
        int numeroEmpresa = 3;
        empresa.setId(1);
        empresa.setDirPrincipal(dirPrincipal);
        empresa.setRazonSocial(razonSocial);
        empresa.setNombrePublico(nombrePublico);
        empresa.setNroEmpresa(numeroEmpresa);
        empresas.add(empresa);
        TypedQuery<Empresa> query = (TypedQuery<Empresa>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select e from Empresa e where e.nroEmpresa=" + numeroEmpresa)).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        EmpresaDTO empresasRetorno = empresasDAO.obtenerEmpresaPorNumero(numeroEmpresa);
        assertNull(empresasRetorno);

    }

 /*   @Test
    public void testEliminarVehiculo() {
        int id = 3 , idEmpresa = 4;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(3);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);
        empresa.setVehiculos(List.of(vehiculo));
        when(entityManager.find(Empresa.class,idEmpresa)).thenReturn(empresa);
        when(entityManager.find(Vehiculo.class,id)).thenReturn(vehiculo);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        empresasDAO.eliminarVehiculo(idEmpresa,id);
        Mockito.verify(entityManager).merge(any(Empresa.class));

    }
*/

  /*    @Test
     public void testAgregarVehiculo() {
        int id = 3 , idEmpresa = 4;
        Vehiculo vehiculo = new Vehiculo();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);
        vehiculo.setId(3);
        Empresa empresa = new Empresa();
        empresa.setId(idEmpresa);
        empresa.setVehiculos(List.of(vehiculo));
        when(entityManager.find(Empresa.class,idEmpresa)).thenReturn(empresa);
        when(entityManager.find(Vehiculo.class,id)).thenReturn(vehiculo);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        empresasDAO.agregarVehiculo(idEmpresa,vehiculoDTO);
        Mockito.verify(entityManager).merge(any(Empresa.class));
    }*/
}