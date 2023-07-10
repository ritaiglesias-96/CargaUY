package tse.java.api.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Empresa;
import tse.java.service.IEmpresasService;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GestionEmpresasEndpointTest {
    @Mock
    private IEmpresasService empresasService;


    @InjectMocks
    private GestionEmpresasEndpoint gestionEmpresasEndpoint;

    @Test
    public void testGetEmpresas(){
        ArrayList<EmpresaDTO> empresas = new ArrayList<>();
        EmpresaDTO e1 = new EmpresaDTO();
        e1.setId(1);
        EmpresaDTO e2 = new EmpresaDTO();
        e1.setId(2);

        empresas.add(e1);
        empresas.add(e1);
        when(empresasService.obtenerEmpresas()).thenReturn(empresas);
        Response response = gestionEmpresasEndpoint.getEmpresas();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(empresas, response.getEntity());

    }

    @Test
    public void testGetEmpresaById() {
        // Configuración del mock
        int id = 1;
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(id);
        when(empresasService.obtenerEmpresa(id)).thenReturn(empresaDTO);
        // Llamada al método del endpoint
        Response response = gestionEmpresasEndpoint.getEmpresaById(id);
        // Verificación de la respuesta
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(empresaDTO, response.getEntity());
    }
    @Test
    public void testGetEmpresaByIdThrowError() {
        // Configuración del mock
        int id = 1;
        when(empresasService.obtenerEmpresa(id)).thenThrow(new NoResultException("Not found"));
        // Llamada al método del endpoint
        Response response = gestionEmpresasEndpoint.getEmpresaById(id);
        // Verificación de la respuesta
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    private void mockAgregarEmpresa(String rut, int resultado){
        when(empresasService.agregarEmpresa(rut)).thenReturn(resultado);
    }

    @Test
    public void testAgregarEmpresa_Exitosamente(){
        String rut = "testRut";
        mockAgregarEmpresa(rut, 1);
        Response response = gestionEmpresasEndpoint.agregarEmpresa(rut);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Empresa " + rut + " creada exitosamente.", response.getEntity());
    }

    @Test
    public void testAgregarEmpresa_NoExiste(){
        String rut = "testRut";
        mockAgregarEmpresa(rut, 0);
        Response response = gestionEmpresasEndpoint.agregarEmpresa(rut);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("NO existe la empresa " + rut, response.getEntity());
    }
    @Test
    public void testAgregarEmpresa_YaExiste(){
        String rut = "testRut";
        mockAgregarEmpresa(rut, 3);
        Response response = gestionEmpresasEndpoint.agregarEmpresa(rut);
        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
        assertEquals("Ya existe la empresa con rut " + rut, response.getEntity());
    }
    @Test
    public void testAgregarEmpresa_Timeout(){
        String rut = "testRut";
        mockAgregarEmpresa(rut, 2);
        Response response = gestionEmpresasEndpoint.agregarEmpresa(rut);
        assertEquals(Response.Status.REQUEST_TIMEOUT.getStatusCode(), response.getStatus());
        assertEquals("Hubo un error al comunicarse con la plataforma", response.getEntity());
    }

    @Test
    public void testModificarEmpresa(){
        int id = 1;
        Empresa empresa = new Empresa();
        empresa.setNroEmpresa(1);

        doAnswer((Answer<Object>) invocation -> {
            EmpresaDTO empresaDTO = (EmpresaDTO) invocation.getArguments()[0];
            empresaDTO.setId(1);
            return null;
        }).when(empresasService).modificarEmpresa(any(EmpresaDTO.class));
        Response response = gestionEmpresasEndpoint.modificarEmpresa(empresa, id);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(empresa, response.getEntity());
    }

    @Test
    public void testModificarEmpresa_NotFound(){
        int id = 1;
        Empresa empresa = new Empresa();
        empresa.setNroEmpresa(1);
        doThrow(NoResultException.class).when(empresasService).modificarEmpresa(any(EmpresaDTO.class));
        Response response = gestionEmpresasEndpoint.modificarEmpresa(empresa, id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testEliminarEmpresa(){
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(empresasService).eliminarEmpresa(id);
        Response response = gestionEmpresasEndpoint.eliminarEmpresa(id);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(id, response.getEntity());
    }
    @Test
    public void testEliminarEmpresa_NotFound(){
        int id = 1;
        doThrow(NoResultException.class).when(empresasService).eliminarEmpresa(id);
        Response response = gestionEmpresasEndpoint.eliminarEmpresa(id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testListarVehiculos(){
        int id = 1;
        List<VehiculoDTO> vehiculos = new ArrayList<>();
        when(empresasService.listarVehiculos(id)).thenReturn(vehiculos);
        Response response = gestionEmpresasEndpoint.listarVehiculos(id);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(vehiculos, response.getEntity());
    }
    @Test
    public void testListarVehiculos_NotFound(){
        int id = 1;
        when(empresasService.listarVehiculos(id)).thenThrow(NoResultException.class);
        Response response = gestionEmpresasEndpoint.listarVehiculos(id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }
    @Test
    public void testGetViajesFinalizados() {
        List<EmpresaDTO> viajes = new ArrayList<>();
        when(empresasService.listarViajesFinalizados()).thenReturn(viajes);
        Response response = gestionEmpresasEndpoint.getViajesFinalizados();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(viajes, response.getEntity());
    }

}
