package tse.java.api.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.RubroDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.persistance.IRubrosDAO;
import tse.java.persistance.ITipoCargaDAO;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class utilitiesEndpointTest {

    @Mock
    IRubrosDAO rubrosDAO;

    @Mock
    ITipoCargaDAO tipoCargaDAO;
    @InjectMocks
    private utilitiesEndpoint utilitiesEndpoint;

    @Test
    public void testListadoRubros() {
        List<RubroDTO> rubros = new ArrayList<>();
        rubros.add(new RubroDTO(10L, "Farmacia"));
        rubros.add(new RubroDTO(14L, "Cosmetica"));

        when(rubrosDAO.listarRubros()).thenReturn(rubros);
        Response response = utilitiesEndpoint.listadoRubros();
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        List<String> result = (List<String>) response.getEntity();
        assertEquals(result.get(0), rubros.get(0).getNombre());
    }

    @Test
    public void testListadoTiposCarga() {
        List<TipoCargaDTO> tipoCarga = new ArrayList<>();
        tipoCarga.add(new TipoCargaDTO(10L, "Farmacia"));
        tipoCarga.add(new TipoCargaDTO(14L, "Cosmetica"));

        when(tipoCargaDAO.listarTipoCarga()).thenReturn(tipoCarga);
        Response response = utilitiesEndpoint.listadoTiposCarga();
        assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());
        List<String> result = (List<String>) response.getEntity();
        assertEquals(result.get(0), tipoCarga.get(0).getNombre());
    }

    @Test
    public void testTest1() {
    }
}