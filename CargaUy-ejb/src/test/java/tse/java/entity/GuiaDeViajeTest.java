package tse.java.entity;

import org.junit.Test;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GuiaDeViajeTest {

    @Test
    public void testConstructorWithoutArguments() {
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();

        assertNull(guiaDeViaje.getId());
        assertEquals(0, guiaDeViaje.getNumero());
        assertNull(guiaDeViaje.getRubroCliente());
        assertNull(guiaDeViaje.getTipoCarga());
        assertEquals(0.0f, guiaDeViaje.getVolumenCarga());
        assertNull(guiaDeViaje.getFecha());
        assertNull(guiaDeViaje.getOrigen());
        assertNull(guiaDeViaje.getInicio());
        assertNull(guiaDeViaje.getFin());
        assertNull(guiaDeViaje.getDestino());
        assertTrue(guiaDeViaje.getPesajes().isEmpty());
    }

    @Test
    public void testConstructorWithGuiaDeViajeDTO() {
        // Crear una lista de pesajes de ejemplo
        List<PesajeDTO> pesajes = new ArrayList<>();
        PesajeDTO pesaje1 = new PesajeDTO();
        PesajeDTO pesaje2 = new PesajeDTO();
        pesajes.add(pesaje1);
        pesajes.add(pesaje2);

        // Crear un objeto GuiaDeViajeDTO de ejemplo
        GuiaDeViajeDTO guiaDeViajeDTO = new GuiaDeViajeDTO();
        Date date = new Date(1999, 5, 12);
        guiaDeViajeDTO.setId(1L);
        guiaDeViajeDTO.setNumero(123);
        guiaDeViajeDTO.setRubroCliente("Rubro");
        guiaDeViajeDTO.setTipoCarga("Tipo");
        guiaDeViajeDTO.setVolumenCarga(10.5f);
        guiaDeViajeDTO.setFecha(date);
        guiaDeViajeDTO.setOrigen("Origen");
        guiaDeViajeDTO.setInicio(date);
        guiaDeViajeDTO.setFin(date);
        guiaDeViajeDTO.setDestino("Destino");
        guiaDeViajeDTO.setPesajes(pesajes);

        // Crear una instancia de GuiaDeViaje utilizando el constructor con un objeto GuiaDeViajeDTO
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje(guiaDeViajeDTO);

        // Verificar que los atributos sean los esperados según los valores del objeto GuiaDeViajeDTO
        assertEquals(guiaDeViajeDTO.getId(), guiaDeViaje.getId());
        assertEquals(guiaDeViajeDTO.getNumero(), guiaDeViaje.getNumero());
        assertEquals(guiaDeViajeDTO.getRubroCliente(), guiaDeViaje.getRubroCliente());
        assertEquals(guiaDeViajeDTO.getTipoCarga(), guiaDeViaje.getTipoCarga());
        assertEquals(guiaDeViajeDTO.getVolumenCarga(), guiaDeViaje.getVolumenCarga());
        assertEquals(guiaDeViajeDTO.getFecha(), guiaDeViaje.getFecha());
        assertEquals(guiaDeViajeDTO.getOrigen(), guiaDeViaje.getOrigen());
        assertEquals(guiaDeViajeDTO.getInicio(), guiaDeViaje.getInicio());
        assertEquals(guiaDeViajeDTO.getFin(), guiaDeViaje.getFin());
        assertEquals(guiaDeViajeDTO.getDestino(), guiaDeViaje.getDestino());
        assertEquals(pesajes.getClass(), guiaDeViaje.getPesajes().getClass());
    }

    @Test
    public void testConstructorWithParameters() {
        // Crear objetos de ejemplo
        Long id = 1L;
        int numero = 123;
        String rubroCliente = "Rubro";
        String tipoCarga = "Tipo";
        float volumenCarga = 10.5f;
        Date fecha = new Date();
        String origen = "Origen";
        Date inicio = new Date();
        Date fin = new Date();
        String destino = "Destino";
        List<Pesaje> pesajes = new ArrayList<>();

        // Crear una instancia de GuiaDeViaje utilizando el constructor con parámetros
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje(id, numero, rubroCliente, tipoCarga, volumenCarga, fecha, origen, inicio, fin, destino, pesajes);

        // Verificar que los atributos sean los esperados
        assertEquals(id, guiaDeViaje.getId());
        assertEquals(numero, guiaDeViaje.getNumero());
        assertEquals(rubroCliente, guiaDeViaje.getRubroCliente());
        assertEquals(tipoCarga, guiaDeViaje.getTipoCarga());
        assertEquals(volumenCarga, guiaDeViaje.getVolumenCarga());
        assertEquals(fecha, guiaDeViaje.getFecha());
        assertEquals(origen, guiaDeViaje.getOrigen());
        assertEquals(inicio, guiaDeViaje.getInicio());
        assertEquals(fin, guiaDeViaje.getFin());
        assertEquals(destino, guiaDeViaje.getDestino());
        assertEquals(pesajes, guiaDeViaje.getPesajes());
    }

    @Test
    public void testProcesarLista() {
        // Crear una lista de pesajes de ejemplo
        List<Pesaje> pesajes = new ArrayList<>();
        Pesaje pesaje1 = new Pesaje(1L, 42.123456, -71.987654, LocalDateTime.now(), 10.5f);
        Pesaje pesaje2 = new Pesaje(2L, 41.123456, -70.987654, LocalDateTime.now(), 15.3f);
        pesajes.add(pesaje1);
        pesajes.add(pesaje2);

        // Crear una instancia de GuiaDeViaje
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setPesajes(pesajes);

        // Llamar al método procesarLista()
        List<PesajeDTO> result = guiaDeViaje.procesarLista();

        // Verificar que la lista resultante tenga el tamaño esperado
        assertEquals(pesajes.size(), result.size());

        // Verificar que los atributos de cada PesajeDTO sean los esperados
        for (int i = 0; i < pesajes.size(); i++) {
            Pesaje pesaje = pesajes.get(i);
            PesajeDTO pesajeDTO = result.get(i);
            assertEquals(pesaje.getId(), pesajeDTO.getId());
            assertEquals(pesaje.getLatitud(), pesajeDTO.getLatitud());
            assertEquals(pesaje.getLonguitud(), pesajeDTO.getLonguitud());
            assertEquals(pesaje.getFecha(), pesajeDTO.getFecha());
            assertEquals(pesaje.getCarga(), pesajeDTO.getCarga());
        }
    }

    @Test
    public void testDarDto() {
        // Crear objetos de ejemplo
        Long id = 1L;
        int numero = 123;
        String rubroCliente = "Rubro";
        String tipoCarga = "Tipo";
        float volumenCarga = 10.5f;
        Date fecha = new Date();
        String origen = "Origen";
        Date inicio = new Date();
        Date fin = new Date();
        String destino = "Destino";

        // Crear una lista de pesajes de ejemplo
        List<Pesaje> pesajes = new ArrayList<>();
        Pesaje pesaje1 = new Pesaje(1L, 42.123456, -71.987654, LocalDateTime.now(), 10.5f);
        Pesaje pesaje2 = new Pesaje(2L, 41.123456, -70.987654, LocalDateTime.now(), 15.3f);
        pesajes.add(pesaje1);
        pesajes.add(pesaje2);

        // Crear una instancia de GuiaDeViaje
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje(id, numero, rubroCliente, tipoCarga, volumenCarga, fecha, origen, inicio, fin, destino, pesajes);

        // Llamar al método darDto()
        GuiaDeViajeDTO result = guiaDeViaje.darDto();

        // Verificar que los valores del objeto GuiaDeViajeDTO sean los esperados
        assertEquals(id, result.getId());
        assertEquals(numero, result.getNumero());
        assertEquals(rubroCliente, result.getRubroCliente());
        assertEquals(tipoCarga, result.getTipoCarga());
        assertEquals(volumenCarga, result.getVolumenCarga());
        assertEquals(fecha, result.getFecha());
        assertEquals(origen, result.getOrigen());
        assertEquals(inicio, result.getInicio());
        assertEquals(fin, result.getFin());
        assertEquals(destino, result.getDestino());

        // Verificar que la lista de pesajes en el objeto GuiaDeViajeDTO tenga el tamaño esperado
        assertEquals(pesajes.size(), result.getPesajes().size());

        // Verificar que los atributos de cada PesajeDTO en la lista sean los esperados
        List<PesajeDTO> pesajesDTO = result.getPesajes();
        for (int i = 0; i < pesajes.size(); i++) {
            Pesaje pesaje = pesajes.get(i);
            PesajeDTO pesajeDTO = pesajesDTO.get(i);
            assertEquals(pesaje.getId(), pesajeDTO.getId());
            assertEquals(pesaje.getLatitud(), pesajeDTO.getLatitud());
            assertEquals(pesaje.getLonguitud(), pesajeDTO.getLonguitud());
            assertEquals(pesaje.getFecha(), pesajeDTO.getFecha());
            assertEquals(pesaje.getCarga(), pesajeDTO.getCarga());
        }
    }

    @Test
    public void testSetters() {
        // Crear una instancia de GuiaDeViaje
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();

        // Valores de ejemplo para los setters
        Long id = 1L;
        int numero = 123;
        String rubroCliente = "Rubro";
        String tipoCarga = "Tipo";
        float volumenCarga = 10.5f;
        Date fecha = new Date();
        String origen = "Origen";
        Date inicio = new Date();
        Date fin = new Date();
        String destino = "Destino";

        // Establecer los valores utilizando los setters correspondientes
        guiaDeViaje.setId(id);
        guiaDeViaje.setNumero(numero);
        guiaDeViaje.setRubroCliente(rubroCliente);
        guiaDeViaje.setTipoCarga(tipoCarga);
        guiaDeViaje.setVolumenCarga(volumenCarga);
        guiaDeViaje.setFecha(fecha);
        guiaDeViaje.setOrigen(origen);
        guiaDeViaje.setInicio(inicio);
        guiaDeViaje.setFin(fin);
        guiaDeViaje.setDestino(destino);

        // Verificar que los valores se hayan establecido correctamente
        assertEquals(id, guiaDeViaje.getId());
        assertEquals(numero, guiaDeViaje.getNumero());
        assertEquals(rubroCliente, guiaDeViaje.getRubroCliente());
        assertEquals(tipoCarga, guiaDeViaje.getTipoCarga());
        assertEquals(volumenCarga, guiaDeViaje.getVolumenCarga());
        assertEquals(fecha, guiaDeViaje.getFecha());
        assertEquals(origen, guiaDeViaje.getOrigen());
        assertEquals(inicio, guiaDeViaje.getInicio());
        assertEquals(fin, guiaDeViaje.getFin());
        assertEquals(destino, guiaDeViaje.getDestino());
    }




}