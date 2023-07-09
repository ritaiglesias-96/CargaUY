package tse.java.entity;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class PesajeTest {

    @Test
    public void testConstructorWithoutArguments() {
        // Crear una instancia de Pesaje utilizando el constructor sin argumentos
        Pesaje pesaje = new Pesaje();

        // Verificar que los atributos se hayan inicializado correctamente
        assertNull(pesaje.getId());
        assertEquals(0.0, pesaje.getLatitud());
        assertEquals(0.0, pesaje.getLonguitud());
        assertNull(pesaje.getFecha());
        assertEquals(0.0f, pesaje.getCarga());
    }

    @Test
    public void testSetters() {
        // Crear una instancia de Pesaje
        Pesaje pesaje = new Pesaje();

        // Valores de ejemplo para los setters
        Long id = 1L;
        double latitud = 42.12345;
        double longuitud = -71.98765;
        LocalDateTime fecha = LocalDateTime.now();
        float carga = 500.0f;

        // Llamar a los setters
        pesaje.setId(id);
        pesaje.setLatitud(latitud);
        pesaje.setLonguitud(longuitud);
        pesaje.setFecha(fecha);
        pesaje.setCarga(carga);

        // Verificar que los atributos se hayan establecido correctamente a través de los correspondientes getters
        assertEquals(id, pesaje.getId());
        assertEquals(latitud, pesaje.getLatitud());
        assertEquals(longuitud, pesaje.getLonguitud());
        assertEquals(fecha, pesaje.getFecha());
        assertEquals(carga, pesaje.getCarga());
    }

}