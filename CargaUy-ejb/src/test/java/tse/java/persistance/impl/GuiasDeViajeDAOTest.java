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

import java.time.LocalDate;
import java.util.ArrayList;
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
    }

    @Test
    public void listarGuiasDeViaje() {
    }

    @Test
    public void borrarGuiaDeViaje() {
    }

    @Test
    public void modificarGuiaDeViaje() {
    }

    @Test
    public void modificarGuiaDeViajeSinAsignacion() {
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