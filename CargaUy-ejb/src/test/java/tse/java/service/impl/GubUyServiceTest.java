package tse.java.service.impl;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.entity.Ciudadano;
import tse.java.service.ICiudadanosService;
import tse.java.soappdi.EmpresaServicePort;
import tse.java.soappdi.GetCiudadanoResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class GubUyServiceTest {

    @Mock
    ICiudadanosService ciudadanosService;
    @InjectMocks
    private GubUyService gubUyService;


  /*  @Test
    public void testGetAuthGubUy() {

        String randomState = "p9ClGpFdUquCzUX3LLeV";
        String expectedUrl = "https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?" +
                "response_type=code" +
                "&scope=openid%20personal_info%20document%20email" +
                "&client_id=890192" +
                "&state=" + randomState +
                "&redirect_uri=https%3A%2F%2Fcarga-uy-13.web.elasticloud.uy%2FCargaUy-web%2Fapi%2Fgubuy%2Ftokens";
        when(gubUyService.getAuthGubUy()).thenReturn(expectedUrl);

        String result = gubUyService.getAuthGubUy();
        System.out.println(result);
        assertEquals(expectedUrl, result);
    }
*/    @Test
    public void testLoginGubUy() {

    }

    @Test
    public void verificarJWT() {

    }

  /*  @Test
    public void testCrearCiudadanoPdi() throws Exception {
        String cedula = "1234567890"; // cédula de ejemplo

        // Se crea un mock del servicio de empresa para simular la respuesta
        EmpresaServicePort empresaPortMock = Mockito.mock(EmpresaServicePort.class);
        GetCiudadanoResponse ciudadanoResponseMock = new GetCiudadanoResponse();
        tse.java.soappdi.Ciudadano ciudadanoMock = new tse.java.soappdi.Ciudadano();
        ciudadanoMock.setCedula(cedula);
        ciudadanoMock.setNombre("John");
        ciudadanoMock.setApellido("Doe");
        ciudadanoMock.setEmail("jdoe@example.com");
        ciudadanoResponseMock.setCiudadano(ciudadanoMock);
        Mockito.when(empresaPortMock.getCiudadano(Mockito.any())).thenReturn(ciudadanoResponseMock);

        Ciudadano ciudadano = gubUyService.crearCiudadanoPdi(cedula);
        assertNotNull(ciudadano);
        assertEquals(cedula, ciudadano.getCedula());
        assertEquals("jdoe@example.com", ciudadano.getEmail());
    }
*/}


