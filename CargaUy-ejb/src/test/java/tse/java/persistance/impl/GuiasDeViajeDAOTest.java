package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.*;
import tse.java.entity.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GuiasDeViajeDAOTest {
    @InjectMocks
    GuiasDeViajeDAO guiasDeViajeDAO;

    @Mock
    private EntityManager entityManager;

    @Test
    public void altaGuiaDeViaje() {
        int id = 1;
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        guiaDeViajeDTO.setId(1);
        guiaDeViajeDTO.setNumero(123);
        guiaDeViajeDTO.setRubroCliente("Transporte");
        guiaDeViajeDTO.setTipoCarga("Mercancías");
        guiaDeViajeDTO.setVolumenCarga(100.5f);
        guiaDeViajeDTO.setFecha(LocalDate.now());
        guiaDeViajeDTO.setOrigen("Origen");
        guiaDeViajeDTO.setInicio(LocalDate.now());
        guiaDeViajeDTO.setFin(LocalDate.now().plusDays(7));
        guiaDeViajeDTO.setDestino("Destino");

        // Crear una lista de pesajes
        List<PesajeDTO> pesajes = new ArrayList<>();
        PesajeDTO pesaje1 = new PesajeDTO();
        pesaje1.setId(1L);
        pesaje1.setCarga(50.5f);
        pesajes.add(pesaje1);

        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.setIdCiudadano(1);
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(1);
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(1);
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        Asignacion asignacion = new Asignacion();
        List<Asignacion> asignaciones = new ArrayList<>();
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(id);
        Empresa empresa = new Empresa();
        empresa.setId(id);
        Vehiculo vehiculo = new Vehiculo();
        asignaciones.add(asignacion);
        chofer.setAsignaciones(asignaciones);
        empresa.setAsignaciones(asignaciones);
        vehiculo.setAsignaciones(asignaciones);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                GuiaDeViaje guiaDeViaje = (GuiaDeViaje) invocation.getArguments()[0];
                guiaDeViaje.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(GuiaDeViaje.class));

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Asignacion asignacion = (Asignacion) invocation.getArguments()[0];
                asignacion.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Asignacion.class));


        when(entityManager.find(Chofer.class, choferDTO.getIdCiudadano())).thenReturn(chofer);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Chofer chofer = (Chofer) invocation.getArguments()[0];
                chofer.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).merge(any(Chofer.class));

        when(entityManager.find(Empresa.class, empresaDTO.getId())).thenReturn(empresa);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        when(entityManager.find(Vehiculo.class, vehiculoDTO.getId())).thenReturn(vehiculo);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Vehiculo vehiculo = (Vehiculo) invocation.getArguments()[0];
                vehiculo.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Vehiculo.class));

        guiasDeViajeDAO.altaGuiaDeViaje(guiaDeViajeDTO, choferDTO, empresaDTO, vehiculoDTO);

        Mockito.verify(entityManager).persist(any(GuiaDeViaje.class));
        Mockito.verify(entityManager).persist(any(Asignacion.class));
        Mockito.verify(entityManager).merge(chofer);
        Mockito.verify(entityManager).merge(empresa);
        Mockito.verify(entityManager).merge(vehiculo);
    }

    @Test
    public void buscarGuiaDeViaje() {
        int id = 3;
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setId(id);
        when(entityManager.find(GuiaDeViaje.class,id)).thenReturn(guiaDeViaje);
        GuiaDeViaje guiaRetorno = guiasDeViajeDAO.buscarGuiaDeViaje(id);
        assertEquals(guiaDeViaje.getId(), guiaRetorno.getId());
    }

    @Test
    public void listarGuiasDeViaje() {
        List<GuiaDeViajeDTO> guiaDeViajesDTO = new ArrayList<>();
        List<GuiaDeViaje> guiaDeViajes = new ArrayList<>();

        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        guiaDeViajeDTO.setId(1);
        guiaDeViajeDTO.setNumero(123);
        guiaDeViajeDTO.setRubroCliente("Transporte");
        guiaDeViajeDTO.setTipoCarga("Mercancías");
        guiaDeViajeDTO.setVolumenCarga(100.5f);
        guiaDeViajeDTO.setFecha(LocalDate.now());
        guiaDeViajeDTO.setOrigen("Origen");
        guiaDeViajeDTO.setInicio(LocalDate.now());
        guiaDeViajeDTO.setFin(LocalDate.now().plusDays(7));
        guiaDeViajeDTO.setDestino("Destino");
        guiaDeViajesDTO.add(guiaDeViajeDTO);
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje(guiaDeViajeDTO);
        guiaDeViajes.add(guiaDeViaje);

        TypedQuery<GuiaDeViaje> query = (TypedQuery<GuiaDeViaje>) Mockito.mock(TypedQuery.class);

        when(entityManager.createQuery("select g from GuiaDeViaje g")).thenReturn(query);
        when(query.getResultList()).thenReturn(guiaDeViajes);
        List<GuiaDeViajeDTO> guiaRetorno = guiasDeViajeDAO.listarGuiasDeViaje();
        assertEquals(guiaDeViajesDTO.size(), guiaRetorno.size());
    }

    @Test
    public void borrarGuiaDeViaje() {
        GuiaDeViaje gv = new GuiaDeViaje();
        Empresa e = new Empresa();
        List<Asignacion> asignaciones = new ArrayList<>();
        Asignacion a1 = new Asignacion();
        Asignacion a2 = new Asignacion();
        a1.setGuia(gv);
        a2.setGuia(gv);
        asignaciones.add(a1);
        asignaciones.add(a2);
        e.setAsignaciones(asignaciones);
        List<Vehiculo> vehiculos = new ArrayList<>();
        Vehiculo v1 = new Vehiculo();
        Vehiculo v2 = new Vehiculo();
        v1.setAsignaciones(asignaciones);
        v2.setAsignaciones(asignaciones);
        vehiculos.add(v1);
        vehiculos.add(v2);
        e.setVehiculos(vehiculos);
        List<Chofer> choferes = new ArrayList<>();
        Chofer c1 = new Chofer();
        Chofer c2 = new Chofer();
        c1.setAsignaciones(asignaciones);
        c2.setAsignaciones(asignaciones);
        choferes.add(c1);
        choferes.add(c2);
        e.setChoferes(choferes);
        gv.setPesajes(new ArrayList<Pesaje>());
        gv.setId(1);
        int idEmpresa = 1;

        // Stub the EntityManager find method to return the mock objects
        when(entityManager.find(GuiaDeViaje.class, gv.getId())).thenReturn(gv);
        when(entityManager.find(Empresa.class, idEmpresa)).thenReturn(e);
        // Call the method under test
        guiasDeViajeDAO.borrarGuiaDeViaje(gv.getId(), idEmpresa);

        // Verify that the necessary methods were called on the EntityManager
        Mockito.verify(entityManager).find(GuiaDeViaje.class, gv.getId());
        Mockito.verify(entityManager).find(Empresa.class, idEmpresa);
    }

    @Test
    public void modificarGuiaDeViaje() {
        List<Pesaje> pesajes = new ArrayList<>();
        List<Asignacion> asignaciones = new ArrayList<>();
        Asignacion asignacion = new Asignacion();
        asignaciones.add(asignacion);
        Pesaje pesaje = new Pesaje();
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setId(1);
        guiaDeViaje.setNumero(123);
        guiaDeViaje.setRubroCliente("Transporte");
        guiaDeViaje.setTipoCarga("Mercancías");
        guiaDeViaje.setVolumenCarga(100.5f);
        guiaDeViaje.setFecha(Date.valueOf(LocalDate.now()));
        guiaDeViaje.setOrigen("Origen");
        guiaDeViaje.setInicio(Date.valueOf(LocalDate.now()));
        guiaDeViaje.setFin(Date.valueOf(LocalDate.now().plusDays(7)));
        guiaDeViaje.setDestino("Destino");
        pesajes.add(pesaje);
        guiaDeViaje.setPesajes(pesajes);
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        chofer.setAsignaciones(asignaciones);
        Empresa empresa = new Empresa();
        empresa.setId(1);
        empresa.setAsignaciones(asignaciones);
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setAsignaciones(asignaciones);
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO(guiaDeViaje);
        ChoferDTO choferDTO = new ChoferDTO();
        choferDTO.setIdCiudadano(1);
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(1);
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(1);
        when(entityManager.find(GuiaDeViaje.class,1)).thenReturn(guiaDeViaje);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Pesaje pesaje = (Pesaje) invocation.getArguments()[0];
                pesaje.setId(1L);
                return null;
            }
        }).when(entityManager).merge(any(Pesaje.class));

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                GuiaDeViaje guiaDeViaje = (GuiaDeViaje) invocation.getArguments()[0];
                guiaDeViaje.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(GuiaDeViaje.class));

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Asignacion asignacion = (Asignacion) invocation.getArguments()[0];
                asignacion.setId(1);
                return null;
            }
        }).when(entityManager).persist(any(Asignacion.class));
        when(entityManager.find(Chofer.class,chofer.getIdCiudadano())).thenReturn(chofer);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Chofer chofer = (Chofer) invocation.getArguments()[0];
                chofer.setIdCiudadano(1);
                return null;
            }
        }).when(entityManager).merge(any(Chofer.class));
        when(entityManager.find(Empresa.class,empresaDTO.getId())).thenReturn(empresa);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Empresa empresa = (Empresa) invocation.getArguments()[0];
                empresa.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Empresa.class));

        when(entityManager.find(Vehiculo.class,1)).thenReturn(vehiculo);

        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Vehiculo vehiculo = (Vehiculo) invocation.getArguments()[0];
                vehiculo.setId(1);
                return null;
            }
        }).when(entityManager).merge(any(Vehiculo.class));

        guiasDeViajeDAO.modificarGuiaDeViaje(guiaDeViajeDTO, choferDTO, empresaDTO, vehiculoDTO );
        Mockito.verify(entityManager).merge(any(Pesaje.class));
        Mockito.verify(entityManager).merge(any(GuiaDeViaje.class));
        Mockito.verify(entityManager).merge(any(Chofer.class));
        Mockito.verify(entityManager).merge(any(Empresa.class));
        Mockito.verify(entityManager).merge(any(Vehiculo.class));
        Mockito.verify(entityManager).persist(any(Asignacion.class));



    }

    @Test
    public void modificarGuiaDeViajeSinAsignacion() {
        List<Pesaje> pesajes = new ArrayList<>();
        List<PesajeDTO> pesajesDTO = new ArrayList<>();
        Pesaje pesaje = new Pesaje();
        PesajeDTO pesajeDTO = new PesajeDTO();
        GuiaDeViajeDTO guiaDTO = new GuiaDeViajeDTO();
        guiaDTO.setId(1);
        guiaDTO.setOrigen("Sao Paulo");
        guiaDTO.setDestino("Rio de Janeiro");
        guiaDTO.setFecha(new Date(2000).toLocalDate());

        // Create a sample GuiaDeViaje object
        GuiaDeViaje guia = new GuiaDeViaje();
        guia.setId(1);
        guia.setOrigen("Sao Paulo");
        guia.setDestino("Rio de Janeiro");
        guia.setFecha(new Date(2000));

        pesajes.add(pesaje);
        pesajesDTO.add(pesajeDTO);
        guiaDTO.setPesajes(pesajesDTO);
        guia.setPesajes(pesajes);
        doAnswer(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                Pesaje pesaje = (Pesaje) invocation.getArguments()[0];
                pesaje.setId(1L);
                return null;
            }
        }).when(entityManager).merge(any(Pesaje.class));

        // Mock the EntityManager.find method to return the sample GuiaDeViaje object
        when(entityManager.find(GuiaDeViaje.class, guiaDTO.getId())).thenReturn(guia);

        // Call the modificarGuiaDeViajeSinAsignacion method
        guiasDeViajeDAO.modificarGuiaDeViajeSinAsignacion(guiaDTO);

        // Verify that the GuiaDeViaje object was updated correctly
        assertEquals(guiaDTO.getId(), guia.getId());
        assertEquals(guiaDTO.getOrigen(), guia.getOrigen());
        assertEquals(guiaDTO.getDestino(), guia.getDestino());
        Mockito.verify(entityManager).merge(any(Pesaje.class));
    }

    @Test
    public void getNextNumeroViaje() {
    }

    @Test
    public void buscarGuiaViajePorNumero() {
    }

    @Test
    public void cantidadViajesPorAnioRubro() {
    }

    @Test
    public void buscarGuiaViajePorId() {
    }
}