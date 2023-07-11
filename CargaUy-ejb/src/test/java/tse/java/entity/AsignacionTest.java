package tse.java.entity;

import org.junit.Test;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AsignacionTest {


    @Test
    public void testConstructor() {
        // Crear una instancia de GuiaDeViaje para utilizarla en el constructor de Asignacion
        GuiaDeViaje guia = new GuiaDeViaje();

        // Valores de ejemplo para el constructor
        int id = 1;
        LocalDateTime fechaCambio = LocalDateTime.now();

        // Crear una instancia de Asignacion utilizando el constructor
        Asignacion asignacion = new Asignacion(id, guia, fechaCambio);

        // Verificar que los atributos se hayan inicializado correctamente a través de los correspondientes getters
        assertEquals(id, asignacion.getId());
        assertEquals(guia, asignacion.getGuia());
        assertEquals(fechaCambio, asignacion.getFechaCambio());
    }
    @Test
    public void testGettersAndSetters() {
        // Valores de ejemplo
        int id = 1;
        GuiaDeViaje guia = new GuiaDeViaje();
        LocalDateTime fechaCambio = LocalDateTime.now();

        // Crear una instancia de Asignacion
        Asignacion asignacion = new Asignacion();

        // Probar los setters
        asignacion.setId(id);
        asignacion.setGuia(guia);
        asignacion.setFechaCambio(fechaCambio);

        // Probar los getters y verificar que los valores sean correctos
        assertEquals(id, asignacion.getId());
        assertEquals(guia, asignacion.getGuia());
        assertEquals(fechaCambio, asignacion.getFechaCambio());
    }
//TODO: FIXME
//    @Test
//    public void testDarDTO() {
//        // Crear una instancia de GuiaDeViaje para utilizarla en la Asignacion
//        GuiaDeViaje guia = new GuiaDeViaje();
//
//        // Valores de ejemplo para los atributos de Asignacion
//        int id = 1;
//        LocalDateTime fechaCambio = LocalDateTime.now();
//
//        // Crear una instancia de Asignacion
//        Asignacion asignacion = new Asignacion();
//        asignacion.setId(id);
//        asignacion.setGuia(guia);
//        asignacion.setFechaCambio(fechaCambio);
//
//        // Obtener el DTO utilizando el método darDTO
//        AsignacionDTO asignacionDTO = asignacion.darDTO();
//
//        // Verificar que los valores del DTO coincidan con los valores de la Asignacion
//        assertEquals(id, asignacionDTO.getId());
//        assertEquals(asignacionDTO.getClass(), asignacion.darDTO().getClass());
//        assertEquals(fechaCambio, asignacionDTO.getFechaCambio());
//    }
//TODO: FIXME
//    @Test
//    public void testConvertirGuia() {
//        // Crear una instancia de GuiaDeViajeDTO
//        GuiaDeViajeDTO guiaDTO = new GuiaDeViajeDTO();
//        guiaDTO.setId(1);
//        guiaDTO.setNumero(123);
//        // ... Establecer otros valores del DTO según tus necesidades ...
//
//        // Crear una instancia de GuiaDeViaje utilizando el método convertirGuia
//        GuiaDeViaje guia = new Asignacion().convertirGuia(guiaDTO);
//
//        // Verificar que la guía convertida no sea nula
//        assertNotNull(guia);
//
//        // Verificar que los atributos de la guía convertida sean iguales a los del DTO
//        assertEquals(guiaDTO.getId(), guia.getId());
//        assertEquals(guiaDTO.getNumero(), guia.getNumero());
//        assertEquals(guiaDTO.getClass(), guia.darDto().getClass());
//    }

    @Test
    public void testConvertirGuia2() {
        LocalDate date = LocalDate.of(1999, 5, 12);
        List<PesajeDTO> pesajesDTO = new ArrayList<>();
        pesajesDTO.add(new PesajeDTO(1L, 1.0, 2.0, LocalDateTime.now(), 100.0f));
        pesajesDTO.add(new PesajeDTO(2L, 3.0, 4.0, LocalDateTime.now(), 200.0f));
        // Crear un objeto GuiaDeViajeDTO de prueba
        GuiaDeViajeDTO guiaDTO = new GuiaDeViajeDTO(1, 123, "Rubro", "Tipo Carga",
                10.5f, date, "Origen", date,
                date, "Destino",pesajesDTO );

        // Crear una instancia de Asignacion
        Asignacion asignacion = new Asignacion();

        // Invocar al método convertirGuia
        GuiaDeViaje guia = asignacion.convertirGuia(guiaDTO);

        // Verificar que el objeto GuiaDeViaje se haya creado correctamente
        assertNotNull(guia);
        assertEquals(guiaDTO.getId(), guia.getId());
        assertEquals(guiaDTO.getNumero(), guia.getNumero());
        assertEquals(guiaDTO.getRubroCliente(), guia.getRubroCliente());
        assertEquals(guiaDTO.getTipoCarga(), guia.getTipoCarga());
        assertEquals(guiaDTO.getVolumenCarga(), guia.getVolumenCarga());
        //TODO: FIXME
//        assertEquals(guiaDTO.getFecha(), guia.getFecha());
        assertEquals(guiaDTO.getOrigen(), guia.getOrigen());
        assertEquals(guiaDTO.getInicio(), guia.getInicio().toLocalDate());
        assertEquals(guiaDTO.getFin(), guia.getFin().toLocalDate());
        assertEquals(guiaDTO.getDestino(), guia.getDestino());
    }
}