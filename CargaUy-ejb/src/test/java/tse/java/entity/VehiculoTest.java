package tse.java.entity;

import org.junit.Test;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.VehiculoDTO;

import javax.ejb.Local;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VehiculoTest {

    @Test
    public void testConstructor() {
        // Valores de ejemplo para el constructor
        int id = 1;
        String matricula = "ABC123";
        String pais = "URU";
        String marca = "Volskwagen";
        String modelo = "Virtus";
        Float peso = 1500.0f;
        Float capacidadCarga = 1000.0f;
        int pnc = 12345;
        Date date = new Date(1999, 5, 12);
        Empresa empresa = new Empresa();
        List<Asignacion> asignaciones = new ArrayList<>();

        // Crear una instancia de Vehiculo utilizando el constructor
        Vehiculo vehiculo = new Vehiculo(id, matricula, pais, marca, modelo, peso, capacidadCarga, date, pnc, date, date, empresa, asignaciones);

        // Verificar que los atributos se hayan establecido correctamente
        assertEquals(id, vehiculo.getId());
        assertEquals(matricula, vehiculo.getMatricula());
        assertEquals(pais, vehiculo.getPais());
        assertEquals(marca, vehiculo.getMarca());
        assertEquals(modelo, vehiculo.getModelo());
        assertEquals(peso, vehiculo.getPeso());
        assertEquals(capacidadCarga, vehiculo.getCapacidadCarga());
        assertEquals(date, vehiculo.getFechaFinITV());
        assertEquals(pnc, vehiculo.getPnc());
        assertEquals(date, vehiculo.getFechaInicioPNC());
        assertEquals(date, vehiculo.getFechaFinPNC());
        assertEquals(empresa, vehiculo.getEmpresa());
        assertEquals(asignaciones, vehiculo.getAsignaciones());
    }

    @Test
    public void testModificarVehiculo() {
        // Crear una instancia de Vehiculo
        Vehiculo vehiculo = new Vehiculo();

        // Valores de ejemplo para el VehiculoDTO
        String matricula = "ABC123";
        String pais = "USA";
        String marca = "Toyota";
        String modelo = "Corolla";
        Float peso = 1500.0f;
        Float capacidadCarga = 1000.0f;
        int pnc = 12345;
        LocalDate date = LocalDate.of(1999, 5, 12);
        List<AsignacionDTO> asignaciones = new ArrayList<>();

        // Crear una instancia de VehiculoDTO con los valores de ejemplo
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setMatricula(matricula);
        vehiculoDTO.setPais(pais);
        vehiculoDTO.setMarca(marca);
        vehiculoDTO.setModelo(modelo);
        vehiculoDTO.setPeso(peso);
        vehiculoDTO.setCapacidadCarga(capacidadCarga);
        vehiculoDTO.setPnc(pnc);
        vehiculoDTO.setFechaFinITV(date);
        vehiculoDTO.setFechaInicioPNC(date);
        vehiculoDTO.setFechaFinPNC(date);
        vehiculoDTO.setAsignaciones(asignaciones);

        // Llamar al método modificarVehiculo() utilizando el VehiculoDTO
        vehiculo.modificarVehiculo(vehiculoDTO);

        // Verificar que los atributos se hayan modificado correctamente
        assertEquals(matricula, vehiculo.getMatricula());
        assertEquals(pais, vehiculo.getPais());
        assertEquals(marca, vehiculo.getMarca());
        assertEquals(modelo, vehiculo.getModelo());
        assertEquals(peso, vehiculo.getPeso());
        assertEquals(capacidadCarga, vehiculo.getCapacidadCarga());
        assertEquals(pnc, vehiculo.getPnc());
        assertEquals(date, (vehiculoDTO.getFechaFinITV()));
        assertEquals(date, (vehiculoDTO.getFechaInicioPNC()));
        assertEquals(date, (vehiculoDTO.getFechaFinPNC()));
        assertEquals(asignaciones, vehiculo.getAsignaciones());
    }

    @Test
    public void testProcesarLista() {
        // Valores de ejemplo para las AsignacionDTO
        AsignacionDTO asignacionDTO1 = new AsignacionDTO();
        AsignacionDTO asignacionDTO2 = new AsignacionDTO();
        // ...

        // Crear una lista de AsignacionDTO con los valores de ejemplo
        List<AsignacionDTO> asignacionesDTO = new ArrayList<>();
        asignacionesDTO.add(asignacionDTO1);
        asignacionesDTO.add(asignacionDTO2);
        // ...

        // Crear una instancia de Vehiculo
        Vehiculo vehiculo = new Vehiculo();

        // Llamar al método procesarLista() con la lista de AsignacionDTO
        List<Asignacion> asignaciones = vehiculo.procesarLista(asignacionesDTO);

        // Verificar que se haya procesado correctamente la lista de AsignacionDTO
        assertEquals(asignacionesDTO.size(), asignaciones.size());
        // Verificar que cada elemento de la lista sea una instancia de Asignacion
        for (Asignacion asignacion : asignaciones) {
            assertTrue(asignacion instanceof Asignacion);
        }
    }
    @Test
    public void testSetters() {
        // Crear una instancia de Vehiculo
        Vehiculo vehiculo = new Vehiculo();

        // Valores de ejemplo para los setters
        int id = 1;
        String matricula = "ABC123";
        String pais = "Argentina";
        String marca = "Ford";
        String modelo = "Focus";
        Float peso = 1500f;
        Float capacidadCarga = 2000f;
        Date fechaFinITV = new Date(1999);
        int pnc = 12345;
        Date fechaInicioPNC = new Date(2000);
        Date fechaFinPNC = new Date(2001);
        List<Asignacion> asignaciones = new ArrayList<>();
        Empresa empresa = new Empresa();

        // Llamar a los setters
        vehiculo.setId(id);
        vehiculo.setMatricula(matricula);
        vehiculo.setPais(pais);
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setPeso(peso);
        vehiculo.setCapacidadCarga(capacidadCarga);
        vehiculo.setFechaFinITV(fechaFinITV);
        vehiculo.setPnc(pnc);
        vehiculo.setFechaInicioPNC(fechaInicioPNC);
        vehiculo.setFechaFinPNC(fechaFinPNC);
        vehiculo.setAsignaciones(asignaciones);
        vehiculo.setEmpresas(empresa);

        // Verificar que los atributos se hayan establecido correctamente a través de los correspondientes getters
        assertEquals(id, vehiculo.getId());
        assertEquals(matricula, vehiculo.getMatricula());
        assertEquals(pais, vehiculo.getPais());
        assertEquals(marca, vehiculo.getMarca());
        assertEquals(modelo, vehiculo.getModelo());
        assertEquals(peso, vehiculo.getPeso());
        assertEquals(capacidadCarga, vehiculo.getCapacidadCarga());
        assertEquals(fechaFinITV, vehiculo.getFechaFinITV());
        assertEquals(pnc, vehiculo.getPnc());
        assertEquals(fechaInicioPNC, vehiculo.getFechaInicioPNC());
        assertEquals(fechaFinPNC, vehiculo.getFechaFinPNC());
        assertEquals(asignaciones, vehiculo.getAsignaciones());
        assertEquals(empresa, vehiculo.getEmpresa());
    }


}