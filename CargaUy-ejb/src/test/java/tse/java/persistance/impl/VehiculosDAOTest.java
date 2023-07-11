package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Asignacion;
import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;

import javax.persistence.EntityManager;
import javax.persistence.ManyToMany;
import javax.persistence.TypedQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class VehiculosDAOTest {

    @InjectMocks
    VehiculosDAO vehiculosDAO;

    @Mock
    EntityManager entityManager;


    @Test
    public void testObtenerVehiculo() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        Date fechaFinITV = new Date(2000);
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2000);
        vehiculo.setFechaFinITV(fechaFinITV);
        vehiculo.setFechaInicioPNC(fechaInicioPNC);
        vehiculo.setFechaFinPNC(fechaFinPNC);


        vehiculos.add(vehiculo);
        TypedQuery<Vehiculo> query = (TypedQuery<Vehiculo>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select v from Vehiculo v")).thenReturn(query);
        when(query.getResultList()).thenReturn(vehiculos);

        ArrayList<VehiculoDTO> vehiculosResult = vehiculosDAO.obtenerVehiculos();
        assertEquals(vehiculos.size(), vehiculosResult.size());
    }

    @Test
    public void testObtenerVehiculoId() {
        int id = 1 ;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(2);
        when(entityManager.find(Vehiculo.class,id)).thenReturn(vehiculo);
        Vehiculo vehiculoResultado = vehiculosDAO.obtenerVehiculoId(id);
        assertTrue(vehiculoResultado != null);
        assertEquals(vehiculo.getId(), vehiculoResultado.getId());
    }

    @Test
    public void testModificarVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);

        Date fechaFinITV = new Date(2000);
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2000);

        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(1);
        vehiculoDTO.setPeso(200F);
        vehiculoDTO.setCapacidadCarga(2000);

        vehiculoDTO.setFechaFinITV(fechaFinITV.toLocalDate());
        vehiculoDTO.setFechaInicioPNC(fechaInicioPNC.toLocalDate());
        vehiculoDTO.setFechaFinPNC(fechaFinPNC.toLocalDate());

        when(entityManager.find(Vehiculo.class, vehiculo.getId())).thenReturn(vehiculo);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Vehiculo vehiculo = (Vehiculo) invocation.getArguments()[0];
                vehiculo.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Vehiculo.class));

        vehiculosDAO.modificarVehiculo(vehiculoDTO);
        Mockito.verify(entityManager).merge(any(Vehiculo.class));
    }

  /*  @Test
    public void testEliminarVehiculo() {
        int id = 1;
        Vehiculo vehiculo = new Vehiculo();
        Empresa empresa = new Empresa();
        empresa.setId(id);
        vehiculo.setId(id);
        empresa.setVehiculos(List.of(vehiculo));
        when(entityManager.find(Vehiculo.class, id )).thenReturn(vehiculo);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Vehiculo vehiculo = (Vehiculo) invocation.getArguments()[0];
                vehiculo.setId(1);
                vehiculo.setEmpresas(empresa);
                return null;
            }
        }).when(entityManager).merge(any(Vehiculo.class));

        when(entityManager.find(Empresa.class, id)).thenReturn(empresa);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));


        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Vehiculo vehiculo = (Vehiculo) invocation.getArguments()[0];
                vehiculo.setId(1);
                return null;
            }
        }).when(entityManager).remove(any(Vehiculo.class));

        vehiculosDAO.eliminarVehiculo(id);
        Mockito.verify(entityManager).merge(any(Vehiculo.class));
        Mockito.verify(entityManager).merge(any(Empresa.class));
        Mockito.verify(entityManager).remove(any(Vehiculo.class));

    }
*/
    @Test
    public void testAgregarVehiculo() {
        int id = 1;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        Date fechaFinITV = new Date(2000);
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2000);

        vehiculoDTO.setId(1);
        vehiculoDTO.setPeso(200F);
        vehiculoDTO.setCapacidadCarga(2000);
        vehiculoDTO.setIdEmpresa(1);
        vehiculoDTO.setFechaFinITV(fechaFinITV.toLocalDate());
        vehiculoDTO.setFechaInicioPNC(fechaInicioPNC.toLocalDate());
        vehiculoDTO.setFechaFinPNC(fechaFinPNC.toLocalDate());

        Empresa empresa = new Empresa();
        empresa.setId(1);
        when(entityManager.find(Empresa.class,id)).thenReturn(empresa);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Vehiculo vehiculo = (Vehiculo) invocation.getArguments()[0];
                vehiculo.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Vehiculo.class));

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        vehiculosDAO.agregarVehiculo(vehiculoDTO);
        Mockito.verify(entityManager).persist(any(Vehiculo.class));
        Mockito.verify(entityManager).merge(any(Empresa.class));
    }

    @Test
    public void testObtenerVehiculoMatriculaPais() {
        String matricula = "ASDAS", pais = "URU";
        List<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        Date fechaFinITV = new Date(2000);
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2000);
        vehiculo.setFechaFinITV(fechaFinITV);
        vehiculo.setFechaInicioPNC(fechaInicioPNC);
        vehiculo.setFechaFinPNC(fechaFinPNC);


        vehiculos.add(vehiculo);
        TypedQuery<Vehiculo> query = (TypedQuery<Vehiculo>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select v from Vehiculo v where v.matricula='" + matricula + "' and v.pais='" + pais + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.singletonList(vehiculo));

        VehiculoDTO vehiculosResult = vehiculosDAO.obtenerVehiculoMatriculaPais(matricula, pais);
        assertEquals(vehiculo.getId(), vehiculosResult.getId());
    }
    @Test
    public void testObtenerVehiculoMatriculaPaisThrowError() {
        String matricula = "ASDAS", pais = "URU";
        List<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        Date fechaFinITV = new Date(2000);
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2000);
        vehiculo.setFechaFinITV(fechaFinITV);
        vehiculo.setFechaInicioPNC(fechaInicioPNC);
        vehiculo.setFechaFinPNC(fechaFinPNC);


        vehiculos.add(vehiculo);
        TypedQuery<Vehiculo> query = (TypedQuery<Vehiculo>) Mockito.mock(TypedQuery.class);
        when(entityManager.createQuery("select v from Vehiculo v where v.matricula='" + matricula + "' and v.pais='" + pais + "'")).thenReturn(query);
        when(query.getResultList()).thenReturn(Collections.emptyList());

        VehiculoDTO vehiculosResult = vehiculosDAO.obtenerVehiculoMatriculaPais(matricula, pais);
        assertNull(vehiculosResult);
    }

}