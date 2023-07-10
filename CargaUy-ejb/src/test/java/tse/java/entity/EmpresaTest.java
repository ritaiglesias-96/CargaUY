package tse.java.entity;

import org.junit.Test;
import org.mockito.Mockito;
import tse.java.dto.AsignacionDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmpresaTest {

    @Test
    public void testConstructorAndGetters() {
        Empresa empresa = new Empresa("Nombre", "Razón Social", 123, "Dirección");

        assertEquals("Nombre", empresa.getNombrePublico());
        assertEquals("Razón Social", empresa.getRazonSocial());
        assertEquals(123, empresa.getNroEmpresa());
        assertEquals("Dirección", empresa.getDirPrincipal());
    }

    @Test
    public void testSettersAndGetters() {
        Empresa empresa = new Empresa();

        empresa.setNombrePublico("Nombre");
        empresa.setRazonSocial("Razón Social");
        empresa.setNroEmpresa(123);
        empresa.setDirPrincipal("Dirección");

        assertEquals("Nombre", empresa.getNombrePublico());
        assertEquals("Razón Social", empresa.getRazonSocial());
        assertEquals(123, empresa.getNroEmpresa());
        assertEquals("Dirección", empresa.getDirPrincipal());
    }

    @Test
    public void testConstructorWithoutArguments() {
        Empresa empresa = new Empresa();

        assertNull(empresa.getId());
        assertNull(empresa.getNombrePublico());
        assertNull(empresa.getRazonSocial());
        assertEquals(0, empresa.getNroEmpresa());
        assertNull(empresa.getDirPrincipal());
        assertNull(empresa.getResponsable());
        assertTrue(empresa.getVehiculos().isEmpty());
        assertTrue(empresa.getAsignaciones().isEmpty());
    }

    @Test
    public void testConstructorWithEmpresaDTO() {
        // Crear un objeto EmpresaDTO de ejemplo
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(1);
        empresaDTO.setNombrePublico("Nombre");
        empresaDTO.setRazonSocial("Razón Social");
        empresaDTO.setNroEmpresa(123);
        empresaDTO.setDirPrincipal("Dirección");

        // Crear algunos objetos AsignacionDTO y VehiculoDTO de ejemplo
        AsignacionDTO asignacionDTO1 = new AsignacionDTO();
        AsignacionDTO asignacionDTO2 = new AsignacionDTO();
        VehiculoDTO vehiculoDTO1 = new VehiculoDTO();
        LocalDate fechaFinITV = LocalDate.of(1999, 5, 12);
        vehiculoDTO1.setId(1);
        vehiculoDTO1.setMatricula("ABC123");
        vehiculoDTO1.setPais("España");
        vehiculoDTO1.setMarca("Toyota");
        vehiculoDTO1.setModelo("Corolla");
        vehiculoDTO1.setPeso(1500.0f);
        vehiculoDTO1.setCapacidadCarga(1000.0f);
        vehiculoDTO1.setFechaFinITV(fechaFinITV);
        vehiculoDTO1.setPnc(123);
        vehiculoDTO1.setFechaInicioPNC(fechaFinITV);
        vehiculoDTO1.setFechaFinPNC(fechaFinITV);
        VehiculoDTO vehiculoDTO2 = new VehiculoDTO();
        vehiculoDTO2.setId(2);
        vehiculoDTO2.setMatricula("ABC23");
        vehiculoDTO2.setPais("Espña");
        vehiculoDTO2.setMarca("Toota");
        vehiculoDTO2.setModelo("Coolla");
        vehiculoDTO2.setPeso(1500.0f);
        vehiculoDTO2.setCapacidadCarga(1000.0f);
        vehiculoDTO2.setFechaFinITV(fechaFinITV);
        vehiculoDTO2.setPnc(1213);
        vehiculoDTO2.setFechaInicioPNC(fechaFinITV);
        vehiculoDTO2.setFechaFinPNC(fechaFinITV);

        // Agregar asignaciones y vehículos al objeto EmpresaDTO
        empresaDTO.getAsignaciones().add(asignacionDTO1);
        empresaDTO.getAsignaciones().add(asignacionDTO2);
        empresaDTO.getVehiculos().add(vehiculoDTO1);
        empresaDTO.getVehiculos().add(vehiculoDTO2);

        // Crear mocks de Asignacion y Vehiculo
        Asignacion asignacionMock1 = Mockito.mock(Asignacion.class);
        Asignacion asignacionMock2 = Mockito.mock(Asignacion.class);
        Vehiculo vehiculoMock1 = Mockito.mock(Vehiculo.class);
        Vehiculo vehiculoMock2 = Mockito.mock(Vehiculo.class);

        // Crear la instancia de Empresa utilizando el constructor con un objeto EmpresaDTO
        Empresa empresa = new Empresa(empresaDTO);



        // Verificar que los atributos restantes sean los esperados
        assertEquals(1, empresa.getId());
        assertEquals("Nombre", empresa.getNombrePublico());
        assertEquals("Razón Social", empresa.getRazonSocial());
        assertEquals(123, empresa.getNroEmpresa());
        assertEquals("Dirección", empresa.getDirPrincipal());
    }

    @Test
    public void testConstructorWithParameters() {
        Integer id = 1;
        String nombrePublico = "Nombre";
        String razonSocial = "Razón Social";
        int nroEmpresa = 123;
        String dirPrincipal = "Dirección";

        Empresa empresa = new Empresa(id, nombrePublico, razonSocial, nroEmpresa, dirPrincipal);

        assertEquals(id, empresa.getId());
        assertEquals(nombrePublico, empresa.getNombrePublico());
        assertEquals(razonSocial, empresa.getRazonSocial());
        assertEquals(nroEmpresa, empresa.getNroEmpresa());
        assertEquals(dirPrincipal, empresa.getDirPrincipal());
        assertNull(empresa.getResponsable());
        assertTrue(empresa.getVehiculos().isEmpty());
        assertTrue(empresa.getAsignaciones().isEmpty());
    }

    @Test
    public void testListaDeAsignaciones() {
        // Crear una instancia de Empresa
        Empresa empresa = new Empresa();

        // Crear algunas asignaciones de ejemplo
        Asignacion asignacion1 = new Asignacion();
        Asignacion asignacion2 = new Asignacion();
        Asignacion asignacion3 = new Asignacion();

        // Agregar las asignaciones a la lista de asignaciones de la empresa
        empresa.getAsignaciones().add(asignacion1);
        empresa.getAsignaciones().add(asignacion2);

        // Verificar que la lista de asignaciones contenga las asignaciones agregadas
        assertTrue(empresa.getAsignaciones().contains(asignacion1));
        assertTrue(empresa.getAsignaciones().contains(asignacion2));

        // Verificar que la lista de asignaciones no contenga una asignación no agregada
        assertFalse(empresa.getAsignaciones().contains(asignacion3));
    }

    @Test
    public void testSetVehiculos() {
        Empresa empresa = new Empresa();

        // Crear algunos objetos de vehículo de ejemplo
        Vehiculo vehiculo1 = new Vehiculo();
        Vehiculo vehiculo2 = new Vehiculo();

        // Crear una lista de vehículos y establecerla en la empresa
        empresa.setVehiculos(List.of(vehiculo1, vehiculo2));

        // Verificar que la lista de vehículos de la empresa sea igual a la lista establecida
        assertEquals(List.of(vehiculo1, vehiculo2), empresa.getVehiculos());
    }

    @Test
    public void testSetResponsable() {
        Empresa empresa = new Empresa();

        // Crear un objeto de responsable de ejemplo
        Responsable responsable = new Responsable();

        // Establecer el responsable en la empresa
        empresa.setResponsable(responsable);

        // Verificar que el responsable de la empresa sea igual al responsable establecido
        assertEquals(responsable, empresa.getResponsable());
    }
    @Test
    public void testSetAsignaciones() {
        Empresa empresa = new Empresa();

        // Crear algunas asignaciones de ejemplo
        Asignacion asignacion1 = new Asignacion();
        Asignacion asignacion2 = new Asignacion();

        // Crear una lista de asignaciones y establecerla en la empresa
        empresa.setAsignaciones(List.of(asignacion1, asignacion2));

        // Verificar que la lista de asignaciones de la empresa sea igual a la lista establecida
        assertEquals(List.of(asignacion1, asignacion2), empresa.getAsignaciones());
    }
    @Test
    public void testProcesarListaAsignaciones() {
        Empresa empresa = new Empresa();

        // Crear una lista de asignaciones DTO de ejemplo
        List<AsignacionDTO> asignacionesDTO = new ArrayList<>();
        asignacionesDTO.add(new AsignacionDTO());
        asignacionesDTO.add(new AsignacionDTO());

        // Procesar la lista de asignaciones DTO y obtener la lista de asignaciones resultante
        List<Asignacion> asignaciones = empresa.procesarListaAsignaciones(asignacionesDTO);

        // Verificar que la lista de asignaciones procesadas tenga la misma cantidad de elementos
        assertEquals(asignacionesDTO.size(), asignaciones.size());

        // Verificar que los elementos en la lista de asignaciones sean instancias de la clase Asignacion
        for (Asignacion asignacion : asignaciones) {
            assertTrue(asignacion instanceof Asignacion);
        }
    }
}