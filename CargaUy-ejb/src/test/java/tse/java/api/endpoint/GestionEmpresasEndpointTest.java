package tse.java.api.endpoint;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.EmpresaDTO;
import tse.java.entity.Empresa;
import tse.java.persistance.impl.EmpresasDAO;
import tse.java.service.IEmpresasService;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GestionEmpresasEndpointTest {
    @Mock
    private IEmpresasService empresasService;

    @Mock
    private EmpresasDAO empresasDAO;

    @InjectMocks
    private GestionEmpresasEndpoint gestionEmpresasEndpoint;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testGetEmpresaById() {
        // Configuración del mock
        int id = 1;
        Empresa empresa = new Empresa();
        empresa.setId(1);
        empresasDAO.guardarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
        EmpresaDTO empresaDTO = new EmpresaDTO();
        empresaDTO.setId(id);
      //  Mockito.when(empresasService.obtenerEmpresa(id)).thenReturn(empresaDTO);

        // Llamada al método del endpoint
    //    Response response = gestionEmpresasEndpoint.getEmpresaById(id);

        // Verificación de la respuesta
    //    assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
  //      assertEquals(empresaDTO, response.getEntity());
    }

}
