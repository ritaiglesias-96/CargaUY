package tse.java.dto;

import org.junit.Test;
import tse.java.entity.GuiaDeViaje;

import java.sql.Date;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class GuiaDeViajeDTOTest {

    @Test
    public void testConstructorWithGuia() {
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setId(1);
        guiaDeViaje.setNumero(123);
        guiaDeViaje.setRubroCliente("Rubro Cliente");
        guiaDeViaje.setTipoCarga("Tipo de Carga");
        guiaDeViaje.setVolumenCarga(10.5f);
        guiaDeViaje.setFecha(Date.valueOf("2022-01-01"));
        guiaDeViaje.setOrigen("Origen");
        guiaDeViaje.setInicio(Date.valueOf("2022-01-02"));
        guiaDeViaje.setFin(Date.valueOf("2022-01-03"));
        guiaDeViaje.setDestino("Destino");
        guiaDeViaje.setPesajes(new ArrayList<>());

        // Act
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO(guiaDeViaje);

        // Assert
        assertEquals(guiaDeViaje.getId(), guiaDeViajeDTO.getId());
        assertEquals(guiaDeViaje.getNumero(), guiaDeViajeDTO.getNumero());
        assertEquals(guiaDeViaje.getRubroCliente(), guiaDeViajeDTO.getRubroCliente());
        assertEquals(guiaDeViaje.getTipoCarga(), guiaDeViajeDTO.getTipoCarga());
        assertEquals(guiaDeViaje.getVolumenCarga(), guiaDeViajeDTO.getVolumenCarga());
        assertEquals(guiaDeViaje.getFecha().toLocalDate(), guiaDeViajeDTO.getFecha());
        assertEquals(guiaDeViaje.getOrigen(), guiaDeViajeDTO.getOrigen());
        assertEquals(guiaDeViaje.getDestino(), guiaDeViajeDTO.getDestino());
        assertEquals(guiaDeViaje.getPesajes(), guiaDeViajeDTO.getPesajes());
        assertEquals(guiaDeViaje.getInicio().toLocalDate(), guiaDeViajeDTO.getInicio());
        assertEquals(guiaDeViaje.getFin().toLocalDate(), guiaDeViajeDTO.getFin());


    }

    @Test
    public void testConstructorWithDTO() {
        GuiaDeViajeAltaDTO guiaDeViajeAltaDTO = new GuiaDeViajeAltaDTO();
        guiaDeViajeAltaDTO.setRubroCliente("Rubro Cliente");
        guiaDeViajeAltaDTO.setTipoCarga("Tipo de Carga");
        guiaDeViajeAltaDTO.setVolumenCarga(10.5f);
        guiaDeViajeAltaDTO.setOrigen("Origen");
        guiaDeViajeAltaDTO.setDestino("Destino");

        // Act
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO(guiaDeViajeAltaDTO);

        // Assert
        assertEquals(guiaDeViajeAltaDTO.getRubroCliente(), guiaDeViajeDTO.getRubroCliente());
        assertEquals(guiaDeViajeAltaDTO.getTipoCarga(), guiaDeViajeDTO.getTipoCarga());
        assertEquals(guiaDeViajeAltaDTO.getVolumenCarga(), guiaDeViajeDTO.getVolumenCarga());
        assertEquals(guiaDeViajeAltaDTO.getOrigen(), guiaDeViajeDTO.getOrigen());
        assertEquals(guiaDeViajeAltaDTO.getDestino(), guiaDeViajeDTO.getDestino());

    }

}