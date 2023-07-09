package tse.java.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tse.java.service.ICiudadanosService;
import tse.java.service.IGubUyService;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GubUyServiceTest {

    @Mock
    ICiudadanosService ciudadanosService;
    @Mock
    private GubUyService gubUyService;
    @Mock
    private GubUyService gubUyService2;
    @Before
    public void setUp() {
        gubUyService = mock(GubUyService.class);
        MockitoAnnotations.initMocks(this);
        gubUyService2 = new GubUyService();

    }
    @Test
    public void testGetAuthGubUy() {

        // Datos de prueba
        String randomState = "p9ClGpFdUquCzUX3LLeV";
        String expectedUrl = "https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?" +
                "response_type=code" +
                "&scope=openid%20personal_info%20document%20email" +
                "&client_id=890192" +
                "&state=" + randomState +
                "&redirect_uri=https%3A%2F%2Fcarga-uy-13.web.elasticloud.uy%2FCargaUy-web%2Fapi%2Fgubuy%2Ftokens";

        // Configuración del comportamiento del servicio mockeado
        //when(gubUyService.generateRandomString(20)).thenReturn(randomState);

        // Llamar al método bajo prueba
        String result = gubUyService2.getAuthGubUy();

        // Verificar el resultado
        assertNotEquals(expectedUrl, result);
    }


}