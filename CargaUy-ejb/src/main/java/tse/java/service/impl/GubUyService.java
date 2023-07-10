package tse.java.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import okhttp3.*;
import org.primefaces.shaded.json.JSONObject;
import tse.java.api.SessionBean;
import tse.java.dto.ChoferFrontDTO;
import tse.java.dto.CiudadanoFrontDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.ResponsableFrontDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.enumerated.RolCiudadano;
import tse.java.exception.CiudadanoPDIException;
import tse.java.service.ICiudadanosService;
import tse.java.service.IGubUyService;
import tse.java.soappdi.EmpresaServicePort;
import tse.java.soappdi.EmpresaServicePortService;
import tse.java.soappdi.GetCiudadanoRequest;
import tse.java.soappdi.GetCiudadanoResponse;
import tse.java.dto.CiudadanoJwtDTO;


import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


@Stateless
public class GubUyService implements IGubUyService {

    private static final Logger LOGGER = Logger.getLogger(GubUyService.class.getName());
    private static final String TOKEN_ENDPOINT = "https://auth-testing.iduruguay.gub.uy/oidc/v1/token";
    private static final String END_SESSION_ENDPOINT = "https://auth-testing.iduruguay.gub.uy/oidc/v1/logout";
    private static final String CLIENT_ID = "890192" ;
    private static final String CLIENT_SECRET = "457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179";
    private static final String key = "xbmbLFSsxidkGlcKEQPTBhsIvoOOACgROSKMhCcQLILuNamzTZtFjXgShyqs";

    @EJB
    ICiudadanosService ciudadanosService;

    @Inject
    SessionBean sessionBean;

    @Override
    public String getAuthGubUy() {
        try {
            String randomState = generateRandomString(20);
            String url = "https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?";
            url += "response_type=code";
            url += "&scope=openid%20personal_info%20document%20email";
            url += "&client_id=" +"890192";
            url += "&state=" + randomState;
            url += "&redirect_uri=" + URLEncoder.encode("https://carga-uy-13.web.elasticloud.uy/CargaUy-web/api/gubuy/tokens", StandardCharsets.UTF_8.toString());

            return url;
        } catch (UnsupportedEncodingException e) {
            System.out.println("hola");
        }
        return null;
    }

    @Override
    public CiudadanoJwtDTO loginGubUy(String accessCode, String state) throws Exception {
        JSONObject tokens = getTokens(accessCode);
        String token = tokens.getString("id_token");
        sessionBean.setIdToken(token);
        Map<String, Claim> tokenDecodeado = decodeToken(token);
        String cedulaSucia = tokenDecodeado.get("numero_documento").toString();
        String cedula = cedulaSucia.replace("\"", "");
        Ciudadano ciudadano = ciudadanosService.obtenerCiudadanoPorCedula(cedula);
        if(ciudadano!=null){
            return crearUsuarioJWT(ciudadano,token);
        }else{
            try {
                Ciudadano ciudadanoPDI = crearCiudadanoPdi(cedula);
                String email = tokenDecodeado.get("email").asString();
                Ciudadano ciudadanoNuevo = new Ciudadano(email, cedula, null);
                ciudadanosService.agregarCiudadano(ciudadanoNuevo);
                return crearUsuarioJWT(ciudadanoNuevo, token);
            }catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public String agarrarUrl(Response r) throws IOException {
        return r.body().string();
    }

    @Override
    public void verificarJwt(String jwt) {
        System.out.println(jwt);
        System.out.println(sessionBean.getJwt());
        System.out.println(jwt.equals(sessionBean.getJwt()));
        Algorithm algorithm = Algorithm.HMAC256(key);
        JWTVerifier jwtVerifier = JWT.require(algorithm)
                .acceptExpiresAt(60) // Permite 60 segundos de gracia
                .build();
        try {
            DecodedJWT decodedJWT = jwtVerifier.verify(jwt);
        } catch (JWTVerificationException e) {
            throw new RuntimeException("La verificación falló:  " + e.getMessage());
        }

    }

    @Override
    public CiudadanoFrontDTO getCurrentUser(String jwt) {
        Map<String, Claim> tokenDecoded = decodeToken(jwt);
        String cedula = tokenDecoded.get("cedula").asString();
        String token = sessionBean.getIdToken();
        Ciudadano ciudadano = ciudadanosService.obtenerCiudadanoPorCedula(cedula);

        if(ciudadano.getRol() == RolCiudadano.RESPONSABLE) {
            EmpresaDTO empresa = ciudadanosService.obtenerEmpresaPorResponsable(ciudadano.getCedula());
            if (empresa != null) {
                ResponsableFrontDTO responsableFrontDTO = new ResponsableFrontDTO();
                responsableFrontDTO.setJwt(jwt);
                responsableFrontDTO.setIdToken(token);
                responsableFrontDTO.setCiudadano(ciudadano);
                responsableFrontDTO.setIdEmpresa(empresa.getId());
                return responsableFrontDTO;
            }
        } else if (ciudadano.getRol() == RolCiudadano.CHOFER) {
            ChoferFrontDTO chofer = new ChoferFrontDTO();
            chofer.setCiudadano(ciudadano);
            chofer.setJwt(jwt);
            chofer.setIdToken(token);
            if(ciudadano instanceof Chofer && !((Chofer) ciudadano).getAsignaciones().isEmpty()){
                chofer.setAsignaciones(((Chofer) ciudadano).procesarListaAsignaciones(((Chofer) ciudadano).getAsignaciones()));
            }
            return chofer;
        }
        return new CiudadanoFrontDTO(ciudadano, jwt, token);

    }

    private JSONObject getTokens(String accessCode){
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        byte[] bytes = credentials.getBytes();
        String base64 = Base64.getUrlEncoder().encodeToString(bytes);
        try {
            OkHttpClient httpClient = new OkHttpClient();

           RequestBody requestBody = new FormBody.Builder()
                    .add("grant_type", "authorization_code")
                    .add("code", accessCode)
                    .add("redirect_uri", "https://carga-uy-13.web.elasticloud.uy/CargaUy-web/") //TODO cambiar redirect uri
                    .build();

            Request request = new Request.Builder()
                    .url(TOKEN_ENDPOINT)  //TODO VER COMO PONER ESTO EN OTRO LADO
                    .addHeader("Authorization", "Basic " + base64)
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .post(requestBody)
                    .build();
            Response response = httpClient.newCall(request).execute();

            String responseData = response.body().string();

            if (!response.isSuccessful()) {
                throw new Exception("No se pudo intercambiar el access code por el token en el servicio de GubUy.");
            }
            return new JSONObject(responseData);
        } catch (Exception e) {
            JSONObject a = new JSONObject();
            return a;
        }
    }
    public String generateRandomString(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder(length);
        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            sb.append(randomChar);
        }

        return sb.toString();
    }

    public Map<String, Claim> decodeToken(String token) {
        try {
            return JWT.decode(token).getClaims();
        } catch (JWTDecodeException e) {
            LOGGER.severe(e.getMessage());
        }
        return null;
    }

    private CiudadanoJwtDTO crearUsuarioJWT(Ciudadano ciudadano, String token) {
        int expireTimeMinutes = 30;
        try {
            System.out.println("Key: " + key);
            Algorithm alg = Algorithm.HMAC256(key);
            System.out.println("Algoritmo: " + alg);
            JWTCreator.Builder jwt = JWT.create()
                    .withIssuedAt(new Date())//Se pasa la date actual para controlar la vigencia del usuario
                    .withClaim("id", ciudadano.getIdCiudadano())
                    .withClaim("cedula", ciudadano.getCedula())
                    .withClaim("email", ciudadano.getEmail());
            if(ciudadano.getRol()!=null){
                jwt.withClaim("rol", ciudadano.getRol().toString());
            }else{
                jwt.withClaim("rol", "ciudadano");
            }

            long expireTime = (new Date().getTime()) + (60000 * expireTimeMinutes);
            Date expireDate = new Date(expireTime);
            jwt.withExpiresAt(expireDate);
            sessionBean.setJwt(jwt.sign(alg));
            CiudadanoJwtDTO ciudadanoJwtDTO = new CiudadanoJwtDTO(jwt.sign(alg), ciudadano.getCedula(),token);
            return ciudadanoJwtDTO;
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
    }
    private SecretKey getKey(){
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[32];
        secureRandom.nextBytes(keyBytes);
        SecretKey signingKey = new SecretKeySpec(keyBytes, "HMACSHA256");
        return signingKey;
    }

    private Ciudadano crearCiudadanoPdi(String cedula) throws Exception {
        try{
            EmpresaServicePortService empresaService = new EmpresaServicePortService();
            EmpresaServicePort empresaPort = empresaService.getEmpresaServicePortSoap11();
            GetCiudadanoRequest ciudadanoRequest = new GetCiudadanoRequest();
            ciudadanoRequest.setCedula(cedula);
            GetCiudadanoResponse ciudadanoResponse = empresaPort.getCiudadano(ciudadanoRequest);
            tse.java.soappdi.Ciudadano ciudadano = ciudadanoResponse.getCiudadano();
            if(ciudadano == null){
                LOGGER.warning("No existe un ciudadano con el documento " + cedula);
                throw new CiudadanoPDIException("No existe un ciudadano con el documento " + cedula);
            } else {
                LOGGER.info("Datos adicionales del ciudadano " + ciudadano.getCedula() + ": Nombre -> " + ciudadano.getNombre() + ", Apellido -> " + ciudadano.getApellido());
                return new Ciudadano(ciudadano.getEmail(), ciudadano.getCedula(), null);
            }
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Hubo un error al comunicarse con la plataforma", e);
            throw new Exception("Hubo un error al comunicarse con la plataforma", e);
        }
    }
}
