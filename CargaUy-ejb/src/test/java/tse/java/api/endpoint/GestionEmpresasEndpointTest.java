package tse.java.api.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.EmpresaDTO;
import tse.java.service.IEmpresasService;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GestionEmpresasEndpointTest {
    @Mock
    private IEmpresasService empresasService;


    @InjectMocks
    private GestionEmpresasEndpoint gestionEmpresasEndpoint;

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

}
