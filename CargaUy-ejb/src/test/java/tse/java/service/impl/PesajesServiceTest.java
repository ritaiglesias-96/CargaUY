package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@RunWith(MockitoJUnitRunner.class)
public class PesajesServiceTest {

    @InjectMocks
    PesajesService pesajesService;
    @Test
    public void testListarPesajesDeGuia() {

        GuiaDeViajeDTO guia = new GuiaDeViajeDTO();
        guia.setPesajes(createPesajesList());
        LocalDate fecha = LocalDate.of(2023, 7, 11);

        List<PesajeDTO> result = pesajesService.listarPesajesDeGuia(guia, fecha);

        assertEquals(2, result.size());
        assertEquals(createPesaje1().getId(), result.get(0).getId());
        assertEquals(createPesaje2().getId(), result.get(1).getId());
    }


    private List<PesajeDTO> createPesajesList() {
        List<PesajeDTO> pesajes = new ArrayList<>();
        pesajes.add(createPesaje1());
        pesajes.add(createPesaje2());
        pesajes.add(createPesaje3());
        return pesajes;
    }

    private PesajeDTO createPesaje1() {
        PesajeDTO pesaje = new PesajeDTO();
        pesaje.setFecha(LocalDateTime.of(2023, 7, 11, 10, 0));
        pesaje.setCarga(500);
        return pesaje;
    }

    private PesajeDTO createPesaje2() {
        PesajeDTO pesaje = new PesajeDTO();
        pesaje.setFecha(LocalDateTime.of(2023, 7, 11, 15, 30));
        pesaje.setCarga(700);
        return pesaje;
    }

    private PesajeDTO createPesaje3() {
        PesajeDTO pesaje = new PesajeDTO();
        pesaje.setFecha(LocalDateTime.of(2023, 7, 12, 8, 45));
        pesaje.setCarga(600);
        return pesaje;
    }

}