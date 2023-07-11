package tse.java.api.endpoint;

import com.google.firebase.messaging.FirebaseMessagingException;
import org.jetbrains.annotations.TestOnly;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.CiudadanoJwtDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.service.IGubUyService;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;

import java.io.IOException;
import java.net.URISyntaxException;

import static com.google.cloud.storage.Cors.Origin.any;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginGubUyEndpointTest {

    @Mock
    private IGubUyService gubUyService;

    @InjectMocks
    LoginGubUyEndpoint loginGubUyEndpoint;

    @Test
    public void testGubUyAuth() throws URISyntaxException, IOException {
        String string = "url";

        when(gubUyService.getAuthGubUy()).thenReturn(string);

        Response response =  loginGubUyEndpoint.gubUyAuth(string);

        assertEquals(Response.Status.TEMPORARY_REDIRECT.getStatusCode(), response.getStatus());
    }
    @Test
    public void testGubUyAuthFalseMobile() throws URISyntaxException, IOException {
        String string = "url";

        when(gubUyService.getAuthGubUy()).thenReturn(string);

        Response response =  loginGubUyEndpoint.gubUyAuth(null);

        assertEquals(Response.Status.TEMPORARY_REDIRECT.getStatusCode(), response.getStatus());
    }
    @Test
    public void testGetToken() throws URISyntaxException, IOException, FirebaseMessagingException {
        CiudadanoJwtDTO ciudadanoJwtDTO = new CiudadanoJwtDTO();
        String code =" hola",state = "chau";
        Response response = loginGubUyEndpoint.getToken(code, state);
        assertEquals(Response.Status.SEE_OTHER.getStatusCode(), response.getStatus());
    }
    @Test
    public void testGetTokenMobile() throws URISyntaxException, IOException, FirebaseMessagingException {
        CiudadanoJwtDTO ciudadanoJwtDTO = new CiudadanoJwtDTO();
        ciudadanoJwtDTO.setJwt("CVSWETG");
        ciudadanoJwtDTO.setCedula("5184968");
        String code =" hola",state = "chau";
        loginGubUyEndpoint.setIsMobile(true);

        when(gubUyService.loginGubUy(code, state)).thenReturn(ciudadanoJwtDTO);

        Response response = loginGubUyEndpoint.getToken(code, state);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void testTokenControl() {
        String jwt = "jwt";
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(gubUyService).verificarJwt(jwt);

        Response response = loginGubUyEndpoint.tokenControl(jwt);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void testLogout() {
        String jwt = "jwt";
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(gubUyService).logout(jwt);

        Response response = loginGubUyEndpoint.logout(jwt);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }


}