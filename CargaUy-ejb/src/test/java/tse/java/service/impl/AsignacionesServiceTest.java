package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.persistance.IAsignacionDAO;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
@RunWith(MockitoJUnitRunner.class)
public class AsignacionesServiceTest {


    @InjectMocks
    AsignacionesService service;

    @Mock
    private IAsignacionDAO asignacionDAO;

    @Test
    public void testUltimaAsignacionViaje() {
        GuiaDeViajeDTO guiaDeViajeDTO1 = new GuiaDeViajeDTO();
        guiaDeViajeDTO1.setNumero(1);
        GuiaDeViajeDTO guiaDeViajeDTO2 = new GuiaDeViajeDTO();
        guiaDeViajeDTO2.setNumero(2);

        AsignacionDTO asignacion1 = new AsignacionDTO();
        asignacion1.setId(1);
        asignacion1.setGuia(guiaDeViajeDTO1);
        AsignacionDTO asignacion2 = new AsignacionDTO();
        asignacion2.setId(2);
        asignacion2.setGuia(guiaDeViajeDTO2);

        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(asignacion1);
        asignaciones.add(asignacion2);

        when(asignacionDAO.listarAsignaciones()).thenReturn(asignaciones);

        int result = service.ultimaAsignacionViaje(1);

        assertEquals(1, result);

    }

}