package tse.java.api.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.service.IEmpresasService;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class estadoVehiculosEmpresaEndpointTest {


    @InjectMocks
    estadoVehiculosEmpresaEndpoint estadoVehiculosEmpresaEndpoint;

    @Mock
    private IEmpresasService empresasService;

    @Test
    public void testGetVehiculos(){
        int id = 1;
        List<VehiculoDTO>  vehiculos = new ArrayList<>();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(1);
        vehiculoDTO.setFechaFinITV(LocalDate.of(2000,1,1));
        vehiculoDTO.setFechaInicioPNC(LocalDate.of(2000,1,1));
        vehiculoDTO.setFechaFinPNC(LocalDate.of(2000,1,1));
        vehiculos.add(vehiculoDTO);
        when(empresasService.listarVehiculos(id)).thenReturn(vehiculos);
        Response response = estadoVehiculosEmpresaEndpoint.getVehiculos(id);
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }

    @Test
    public void testGetVehiculos_NotFound(){
        int id = 1;
        when(empresasService.listarVehiculos(id)).thenThrow(NoResultException.class);
        Response response = estadoVehiculosEmpresaEndpoint.getVehiculos(id);
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    public void testGetTodosVehiculos(){
        ArrayList<EmpresaDTO>  empresas = new ArrayList<>();
        List<VehiculoDTO>  vehiculos = new ArrayList<>();
        VehiculoDTO vehiculoDTO = new VehiculoDTO();
        vehiculoDTO.setId(1);
        vehiculoDTO.setFechaFinITV(LocalDate.of(2000,1,1));
        vehiculoDTO.setFechaInicioPNC(LocalDate.of(2000,1,1));
        vehiculoDTO.setFechaFinPNC(LocalDate.of(2000,1,1));
        vehiculos.add(vehiculoDTO);
        EmpresaDTO empresa = new EmpresaDTO();
        empresa.setVehiculos(vehiculos);
        empresas.add(empresa);
        when(empresasService.obtenerEmpresas()).thenReturn(empresas);
        Response response = estadoVehiculosEmpresaEndpoint.getTodosVehiculos();
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
    }
    @Test
    public void testGetTodosVehiculos_NotFound(){
        when(empresasService.obtenerEmpresas()).thenThrow(NoResultException.class);
        Response response = estadoVehiculosEmpresaEndpoint.getTodosVehiculos();
        assertEquals(response.getStatus(), Response.Status.NOT_FOUND.getStatusCode());
    }

}