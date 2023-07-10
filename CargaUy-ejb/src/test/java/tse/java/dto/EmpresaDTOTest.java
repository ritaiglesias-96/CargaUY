package tse.java.dto;

import org.junit.Test;
import tse.java.entity.Asignacion;
import tse.java.entity.Empresa;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Vehiculo;

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


        // Act
        EmpresaDTO empresaDTO = new EmpresaDTO(empresa);

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

    //TODO: FIXME
//    @Test
//    public void testProcesarListaAsignaciones() {
//
//        GuiaDeViaje guia1 = new GuiaDeViaje();
//        guia1.setId(1);
//
//        GuiaDeViaje guia2 = new GuiaDeViaje();
//        guia2.setId(2);
//
//        Asignacion asignacion1 = new Asignacion();
//        asignacion1.setId(1);
//        asignacion1.setFechaCambio(LocalDateTime.now());
//        asignacion1.setGuia(guia1);
//
//
//        Asignacion asignacion2 = new Asignacion();
//        asignacion2.setId(2);
//        asignacion2.setFechaCambio(LocalDateTime.now().plusDays(1));
//        asignacion2.setGuia(guia2);
//
//        List<Asignacion> asignaciones = new ArrayList<>();
//        asignaciones.add(asignacion1);
//        asignaciones.add(asignacion2);
//
//        EmpresaDTO empresaDTO = new EmpresaDTO();
//
//        // Act
//        List<AsignacionDTO> asignacionesDTO = empresaDTO.procesarListaAsignaciones(asignaciones);
//
//        // Assert
//        assertEquals(asignaciones.size(), asignacionesDTO.size());
//
//        for (int i = 0; i < asignaciones.size(); i++) {
//            Asignacion asignacion = asignaciones.get(i);
//            AsignacionDTO asignacionDTO = asignacionesDTO.get(i);
//
//            assertEquals(asignacion.getId(), asignacionDTO.getId());
//            assertEquals(asignacion.getFechaCambio(), asignacionDTO.getFechaCambio());
//        }
//    }

    @Test
    public void testContieneVehiculo() {
        // Arrange
        VehiculoDTO vehiculo1 = new VehiculoDTO();
        vehiculo1.setId(1);

        VehiculoDTO vehiculo2 = new VehiculoDTO();
        vehiculo2.setId(2);

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
//        boolean contieneVehiculo3 = empresaDTO.contieneVehiculo(new VehiculoDTO());

        // Assert
        assertTrue(contieneVehiculo1);
        assertTrue(contieneVehiculo2);
//        assertFalse(contieneVehiculo3);
        assertEquals(asignaciones, empresaDTO.getAsignaciones());
    }
}