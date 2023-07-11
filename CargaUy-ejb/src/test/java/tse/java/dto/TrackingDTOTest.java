package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrackingDTOTest {

    @Test
    public void testDefaultConstructor() {
        TrackingDTO trackingDTO = new TrackingDTO();

        // Verificar que los valores por defecto se asignaron correctamente
        assertNull(trackingDTO.getId());
        assertNull(trackingDTO.getMatricula());
        assertNull(trackingDTO.getPais());
        assertNull(trackingDTO.getLongitude());
        assertNull(trackingDTO.getLatitude());
        assertNull(trackingDTO.getTimestamp());
    }
    @Test
    public void testConstructorWithId() {
        Long id = 1L;
        TrackingDTO trackingDTO = new TrackingDTO(id);

        assertEquals(id, trackingDTO.getId());
    }
    @Test
    public void testSetters() {
        TrackingDTO trackingDTO = new TrackingDTO();

        // Setear valores usando los setters
        trackingDTO.setId(1L);
        trackingDTO.setMatricula("ABC123");
        trackingDTO.setLongitude("10.12345");
        trackingDTO.setLatitude("20.54321");
        trackingDTO.setPais("URU");
        trackingDTO.setTimeStamp("2023-07-07 12:34");

        // Verificar que los valores se asignaron correctamente
        assertEquals(1L, trackingDTO.getId());
        assertEquals("ABC123", trackingDTO.getMatricula());
        assertEquals("10.12345", trackingDTO.getLongitude());
        assertEquals("20.54321", trackingDTO.getLatitude());
        assertEquals("URU", trackingDTO.getPais());
        assertEquals("2023-07-07 12:34", trackingDTO.getTimestamp());
    }

}