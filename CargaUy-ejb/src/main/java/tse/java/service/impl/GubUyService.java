package tse.java.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import okhttp3.*;
import org.primefaces.shaded.json.JSONObject;
import tse.java.dto.CiudadanoDTO;
import tse.java.dto.UsuarioDTO;
import tse.java.entity.Ciudadano;
import tse.java.service.ICiudadanosService;
import tse.java.service.IGubUyService;


import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Random;


@Stateless
public class GubUyService implements IGubUyService {

    private static final String AUTH_ENDPOINT = "https://id.uruguay.gub.uy/oidc/v1/authorize";
    private static final String TOKEN_ENDPOINT = "https://auth-testing.iduruguay.gub.uy/oidc/v1/token";
    private static final String END_SESSION_ENDPOINT = "https://auth-testing.iduruguay.gub.uy/oidc/v1/logout";
    private static final String USERINFO_ENDPOINT = "https://auth-testing.iduruguay.gub.uy/oidc/v1/userinfo";
    private static final String CLIENT_ID = "890192" ;
    private static final String CLIENT_SECRET = "457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179";
    private static final String REDIRECT_URI = "https://openidconnect.net/callback";

    @EJB
    ICiudadanosService ciudadanosService;

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
            //url += "&redirect_uri=" + URLEncoder.encode("https://openidconnect.net/callback", StandardCharsets.UTF_8.toString());
            return url;
        } catch (UnsupportedEncodingException e) {
            System.out.println("hola");
        }
        return null;
    }

    @Override
    public String loginGubUy(String accessCode, String state) {
        JSONObject tokens = getTokens(accessCode);
        String token = tokens.getString("id_token");
        Map<String, Claim> tokenDecodeado = decodeToken(token);
        String cedulaSucia = tokenDecodeado.get("numero_documento").toString();
        String cedula = cedulaSucia.replace("\"", "");
//        Integer cedula = Integer.parseInt(cedulaLimpia);
        System.out.println(cedula);
        Ciudadano ciudadano = ciudadanosService.obtenerCiudadanoPorCedula(cedula);
        System.out.println("LLEGA ACA A A A A");
        if(ciudadano != null){
            System.out.println("LLEGA A LINEA 72 ciudadano existente: " + ciudadano.getCedula());
            return crearUsuarioJWT(ciudadano);
        }else{
            System.out.println("Linea 77");
            String email = tokenDecodeado.get("email").asString();
            System.out.println("Linea 79");
            Ciudadano ciudadanoNuevo = new Ciudadano(email,cedula,null);
            System.out.println("Linea 81");
            ciudadanosService.agregarCiudadano(ciudadanoNuevo);
            System.out.println("Linea 83");
           // ciudadano  = ciudadanosService.obtenerCiudadanoPorCedula(ciudadano.getCedula());
            System.out.println("cedula ciudadano: " + ciudadanoNuevo.getCedula());
            return crearUsuarioJWT(ciudadanoNuevo);
        }
    }

    @Override
    public String agarrarUrl(Response r) throws IOException {
        return r.body().string();
    }

    private JSONObject getTokens(String accessCode){
        String credentials = CLIENT_ID + ":" + CLIENT_SECRET;
        //Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()))
        byte[] bytes = credentials.getBytes();
        String base64 = Base64.getUrlEncoder().encodeToString(bytes);


        try {
            OkHttpClient httpClient = new OkHttpClient();

           RequestBody requestBody = new FormBody.Builder()
                    .add("grant_type", "authorization_code")
                    .add("code", accessCode)
                    .add("redirect_uri", "https://carga-uy-13.web.elasticloud.uy/CargaUy-web/") //TODO cambiar redirect uri
                  // .add("redirect_uri", "https://openidconnect.net/callback")
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
                //LOGGER.severe("Error en el intercambio de access code por token en el servicio de GubUy. Respuesta: " + response.toString());
                throw new Exception("No se pudo intercambiar el access code por el token en el servicio de GubUy.");
            }
            return new JSONObject(responseData);
        } catch (Exception e) {
            //LOGGER.severe(e.getMessage());
            //throw new MensajeErrorException(e.getMessage());
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
           // LOGGER.severe(e.getMessage());
        }
        return null;
    }
    private String crearUsuarioJWT(Ciudadano ciudadano) {
        int expireTimeMinutes = 30;
        System.out.println("Entra al crear usuario JWT");
        try {
            Algorithm alg = Algorithm.HMAC256("key");
            JWTCreator.Builder jwt = JWT.create()
//					.withIssuer("")
                    .withIssuedAt(new Date())//Se pasa la date actual para controlar la vigencia del usuario
                    .withClaim("id", ciudadano.getIdCiudadano())
                    .withClaim("cedula", ciudadano.getCedula())
                    .withClaim("email", ciudadano.getEmail());
            System.out.println("Crea el jwt  ? ? ?  ?");
            if(ciudadano.getRol()!=null){
                jwt.withClaim("rol", ciudadano.getRol().toString());
            }else{
                jwt.withClaim("rol", "ciudadano");
            }

            long expireTime = (new Date().getTime()) + (60000 * expireTimeMinutes);
            Date expireDate = new Date(expireTime);
            jwt.withExpiresAt(expireDate);
            return jwt.sign(alg);
        } catch (IllegalArgumentException e) {
            System.out.println(e);
            return null;
        }
    }
}
