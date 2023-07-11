package tse.java.entity;

import org.junit.Test;
import tse.java.dto.TrackingDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

public class TrackingTest {

    @Test
    public void testConstructorWithValues() {
        // Valores de ejemplo para el constructor con valores
        String matricula = "ABC123";
        String pais = "Argentina";
        String longitude = "40.7128° N";
        String latitude = "74.0060° W";
        LocalDateTime timeStamp = LocalDateTime.of(2023, 7, 10, 12, 30, 0);

        // Crear una instancia de Tracking utilizando el constructor con valores
        Tracking tracking = new Tracking(matricula, pais, longitude, latitude, timeStamp);

        // Verificar que los atributos se hayan establecido correctamente
        assertEquals(matricula, tracking.getMatricula());
        assertEquals(pais, tracking.getPais());
        assertEquals(longitude, tracking.getLongitude());
        assertEquals(latitude, tracking.getLatitude());
        assertEquals(timeStamp, tracking.getTimeStamp());
    }

    @Test
    public void testConstructorWithDTO() {
        // Valores de ejemplo para el DTO
        String matricula = "ABC123";
        String pais = "Argentina";
        String longitude = "40.7128° N";
        String latitude = "74.0060° W";
        String timestamp = "2023-07-10 12:30";

        // Crear una instancia de TrackingDTO utilizando los valores de ejemplo
        TrackingDTO dto = new TrackingDTO(matricula, pais, longitude, latitude, timestamp);

        // Crear una instancia de Tracking utilizando el constructor con DTO
        Tracking tracking = new Tracking(dto);

        // Verificar que los atributos se hayan establecido correctamente
        assertEquals(matricula, tracking.getMatricula());
        assertEquals(pais, tracking.getPais());
        assertEquals(longitude, tracking.getLongitude());
        assertEquals(latitude, tracking.getLatitude());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime expectedTimeStamp = LocalDateTime.parse(timestamp, formatter);
        assertEquals(expectedTimeStamp, tracking.getTimeStamp());
    }
    @Test
    public void testGetDto() {
        // Valores de ejemplo para el Tracking
        Long id = 1L;
        String matricula = "Ax13";
        String pais = "Uruguay";
        String longitude = "40.7128° N";
        String latitude = "74.0060° W";
        LocalDateTime timeStamp = LocalDateTime.of(2023, 7, 10, 12, 30, 0);

        // Crear una instancia de Tracking utilizando los valores de ejemplo
        Tracking tracking = new Tracking();
        tracking.setId(id);
        tracking.setMatricula(matricula);
        tracking.setPais(pais);
        tracking.setLongitude(longitude);
        tracking.setLatitude(latitude);
        tracking.setTimeStamp(timeStamp);

        // Llamar al método getDto()
        TrackingDTO dto = tracking.getDto();

        // Verificar que el DTO tenga los mismos valores que el Tracking
        assertEquals(id, dto.getId());
        assertEquals(matricula, dto.getMatricula());
        assertEquals(pais, dto.getPais());
        assertEquals(longitude, dto.getLongitude());
        assertEquals(latitude, dto.getLatitude());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String expectedTimeStamp = timeStamp.format(formatter);
        assertEquals(expectedTimeStamp, dto.getTimestamp());
    }
}