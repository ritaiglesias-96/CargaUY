package tse.java.api.endpoint;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoAltaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Vehiculo;
import tse.java.exception.VehiuloException;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;

import javax.ejb.Local;
import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GestionVehiculosEndpointTest {

    @Mock
    IVehiculosService vehiculosService;

    @Mock
    IEmpresasService empresasService;

    @Mock
    IVehiculosDAO vehiculosDAO;
    @InjectMocks
    private GestionVehiculosEndpoint gestionVehiculosEndpoint;

    @Test
    public void testGetEmpresaById() throws VehiuloException {
        int id = 1;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);

        when(vehiculosService.obtenerVehiculoPorId(id)).thenReturn(vehiculoDTO);

        // Llamada al método del endpoint
        Response response = gestionVehiculosEndpoint.getVehiculoById(id);

        // Verificación de la respuesta
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(vehiculoDTO, response.getEntity());
    }

    @Test
    public void testGetEmpresaByIdThrowError() throws VehiuloException {
        // Configuración del mock
        int id = 1;
        when(vehiculosService.obtenerVehiculoPorId(id)).thenThrow(new VehiuloException("Not found"));
        // Llamada al método del endpoint
        Response response = gestionVehiculosEndpoint.getVehiculoById(id);
        // Verificación de la respuesta
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetVehiculos() {
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        VehiculoDTO vehiculoDTO1 = new VehiculoDTO();
        VehiculoDTO vehiculoDTO2 = new VehiculoDTO();
        vehiculos.add(vehiculoDTO1);
        vehiculos.add(vehiculoDTO2);

        when(vehiculosService.obtenerVehiculos()).thenReturn(vehiculos);

        Response response = gestionVehiculosEndpoint.getVehiculos();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(vehiculos, response.getEntity());
    }

    @Test
    public void testGetVehiculosThrowError() {
        // Configuración del mock
        int id = 1;
        when(vehiculosService.obtenerVehiculos()).thenThrow(new NoResultException("Not found"));
        // Llamada al método del endpoint
        Response response = gestionVehiculosEndpoint.getVehiculos();
        // Verificación de la respuesta
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAgregarVehiculo() {
        VehiculoAltaDTO vehiculo = new VehiculoAltaDTO();
        LocalDate date = LocalDate.of(2000, 1, 1);
        vehiculo.setFechaFinITV(date);
        vehiculo.setFechaFinPNC(date);
        vehiculo.setFechaInicioPNC(date);
        vehiculo.setPeso(200);
        vehiculo.setCapacidadCarga(5000);
        EmpresaDTO empresaExistente = new EmpresaDTO();
        empresaExistente.setId(1);
        vehiculo.setIdEmpresa(empresaExistente.getId());
        when(empresasService.obtenerEmpresa(vehiculo.getIdEmpresa())).thenReturn(empresaExistente);
        doAnswer((Answer<Object>) invocation -> {
            VehiculoDTO vehiculo2 = (VehiculoDTO) invocation.getArguments()[0];
            vehiculo2.setId(1);
            return null;
        }).when(vehiculosService).agregarVehiculo(any(VehiculoDTO.class));

        Response response = gestionVehiculosEndpoint.agregarVehiculo(vehiculo);


        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    public void testAgregarVehiculoThrowError() {
        VehiculoAltaDTO vehiculo = new VehiculoAltaDTO();
        LocalDate date = LocalDate.of(2000, 1, 1);
        vehiculo.setFechaFinITV(date);
        vehiculo.setFechaFinPNC(date);
        vehiculo.setFechaInicioPNC(date);

        EmpresaDTO empresaExistente = null;

        Response response = gestionVehiculosEndpoint.agregarVehiculo(vehiculo);


        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }


    @Test
    public void testModificarVehiculo() {
        VehiculoDTO vehiculo = new VehiculoDTO();
        int id = 2;
        doAnswer((Answer<Object>) invocation -> {
            VehiculoDTO vehiculo2 = (VehiculoDTO) invocation.getArguments()[0];
            vehiculo2.setId(1);
            return null;
        }).when(vehiculosService).modificarVehiculo(any(VehiculoDTO.class));

        Response response = gestionVehiculosEndpoint.modificarVehiculo(id, vehiculo);


        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testModificarVehiculoThrowsError() {
        VehiculoDTO vehiculo = new VehiculoDTO();
        int id = 2;
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("No hay resultado");
        }).when(vehiculosService).modificarVehiculo(any(VehiculoDTO.class));

        Response response = gestionVehiculosEndpoint.modificarVehiculo(id, vehiculo);


        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testEliminarVehiculo() throws VehiuloException {
        int id = 1;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);

        when(vehiculosService.obtenerVehiculoPorId(id)).thenReturn(vehiculoDTO);

        doAnswer((Answer<Object>) invocation -> {

            return null;
        }).when(vehiculosService).eliminarVehiculo(anyInt());
        Response response = gestionVehiculosEndpoint.eliminarVehiculo(id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
    @Test
    public void testEliminarVehiculoThrowNoResult() throws VehiuloException {
        int id = 1;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);

        when(vehiculosService.obtenerVehiculoPorId(id)).thenReturn(vehiculoDTO);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("No hay resultado");
            //return null;
        }).when(vehiculosService).eliminarVehiculo(anyInt());
        Response response = gestionVehiculosEndpoint.eliminarVehiculo(id);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testEliminarVehiculoThrowException() throws VehiuloException {
        int id = 1;
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(id);

        when(vehiculosService.obtenerVehiculoPorId(id)).thenThrow(new NoResultException("Not found"));;

        Response response = gestionVehiculosEndpoint.eliminarVehiculo(id);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

}