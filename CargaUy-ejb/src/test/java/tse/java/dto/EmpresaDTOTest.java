package tse.java.dto;

import org.junit.Test;
import tse.java.entity.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmpresaDTOTest {

    @Test
    public void testConstructor1() {
        // Arrange
        Empresa empresa = new Empresa();
        empresa.setId(1);
        empresa.setNombrePublico("Empresa A");
        empresa.setRazonSocial("Empresa A S.A.");
        empresa.setNroEmpresa(12345);
        empresa.setDirPrincipal("Calle Principal 123");
        GuiaDeViaje guiaDeViaje = new GuiaDeViaje();
        guiaDeViaje.setFecha(new Date(1200));
        Asignacion asignacion = new Asignacion();
        asignacion.setId(1);
        Chofer chofer = new Chofer();
        chofer.setIdCiudadano(1);
        Vehiculo vehiculo = new Vehiculo();
        Date fechaFinITV = new Date(1200);
        vehiculo.setId(1);
        vehiculo.setMatricula("ABC123");
        vehiculo.setPais("España");
        vehiculo.setMarca("Toyota");
        vehiculo.setModelo("Corolla");
        vehiculo.setPeso(1500.0f);
        vehiculo.setCapacidadCarga(1000.0f);
        vehiculo.setFechaFinITV(fechaFinITV);
        vehiculo.setPnc(123);
        vehiculo.setFechaInicioPNC(fechaFinITV);
        vehiculo.setFechaFinPNC(fechaFinITV);
        empresa.getAsignaciones().add(asignacion);
        empresa.getVehiculos().add(vehiculo);
        empresa.setChoferes(List.of(chofer));
        asignacion.setId(1);
        asignacion.setGuia(guiaDeViaje);

        // Act
        EmpresaDTO empresaDTO = new EmpresaDTO(empresa);

        ChoferDTO choferDTO = new ChoferDTO();
        empresaDTO.setChoferes(List.of(choferDTO));

        // Assert
        assertEquals(empresa.getId(), empresaDTO.getId());
        assertEquals(empresa.getNombrePublico(), empresaDTO.getNombrePublico());
        assertEquals(empresa.getRazonSocial(), empresaDTO.getRazonSocial());
        assertEquals(empresa.getNroEmpresa(), empresaDTO.getNroEmpresa());
        assertEquals(empresa.getDirPrincipal(), empresaDTO.getDirPrincipal());
    }

    @Test
    public void testConstructor2() {
        // Arrange
        Integer id = 1;
        String nombrePublico = "Empresa A";
        String razonSocial = "Empresa A S.A.";
        int nroEmpresa = 12345;
        String dirPrincipal = "Calle Principal 123";
        List<VehiculoDTO> vehiculos = new ArrayList<>();
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
        vehiculos.add(vehiculoDTO);

        // Act
        EmpresaDTO empresaDTO = new EmpresaDTO(id, nombrePublico, razonSocial, nroEmpresa, dirPrincipal, vehiculos);

        // Assert
        assertEquals(id, empresaDTO.getId());
        assertEquals(nombrePublico, empresaDTO.getNombrePublico());
        assertEquals(razonSocial, empresaDTO.getRazonSocial());
        assertEquals(nroEmpresa, empresaDTO.getNroEmpresa());
        assertEquals(dirPrincipal, empresaDTO.getDirPrincipal());
        assertEquals(vehiculos, empresaDTO.getVehiculos());
    }


    @Test
    public void testContieneVehiculo() {
        // Arrange
        VehiculoDTO vehiculo1 = new VehiculoDTO();
        vehiculo1.setId(1);

        VehiculoDTO vehiculo2 = new VehiculoDTO();
        vehiculo2.setId(2);
        VehiculoDTO vehiculo3 = new VehiculoDTO();
        vehiculo3.setId(3);

        List<VehiculoDTO> vehiculos = new ArrayList<>();
        vehiculos.add(vehiculo1);
        vehiculos.add(vehiculo2);

        List<AsignacionDTO> asignaciones = new ArrayList<>();
        asignaciones.add(new AsignacionDTO());
        asignaciones.add(new AsignacionDTO());


        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setVehiculos(vehiculos);
        empresaDTO.setAsignaciones(asignaciones);

        // Act
        boolean contieneVehiculo1 = empresaDTO.contieneVehiculo(vehiculo1);
        boolean contieneVehiculo2 = empresaDTO.contieneVehiculo(vehiculo2);
        boolean contieneVehiculo3 = empresaDTO.contieneVehiculo(vehiculo3);

        // Assert
        assertTrue(contieneVehiculo1);
        assertTrue(contieneVehiculo2);
        assertFalse(contieneVehiculo3);
        assertEquals(asignaciones, empresaDTO.getAsignaciones());
    }
}