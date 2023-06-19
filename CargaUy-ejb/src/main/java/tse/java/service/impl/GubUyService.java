package tse.java.service.impl;


import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.primefaces.shaded.json.JSONObject;
import tse.java.service.IGubUyService;


import javax.ejb.Stateless;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;
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

    @Override
    public String getAuthGubUy() {
        try {
            String randomState = generateRandomString(20);
            String url = "https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?";
            url += "response_type=code";
            url += "&scope=openid%20personal_info%20document%20email";
            url += "&client_id=" +"890192";
            url += "&state=" + randomState;
            url += "&redirect_uri=" + URLEncoder.encode("https://openidconnect.net/callback", StandardCharsets.UTF_8.toString());

            return url;
        } catch (UnsupportedEncodingException e) {
            System.out.println("hola");
        }
        return null;
    }

    @Override
    public String loginGubUy(String accessCode, String state) {
        JSONObject tokens = getTokens(accessCode);
        return tokens.getString("id_token"); //TODO ? PREGUNTAR SOBRE ESTO
    }
    private JSONObject getTokens(String accessCode){
        try {
            OkHttpClient httpClient = new OkHttpClient();

            RequestBody requestBody = new FormBody.Builder()
                    .add("grant_type", "authorization_code")
                    .add("code", accessCode)
                    .add("redirect_uri", "https://openidconnect.net/callback")
                    .build();

            Request request = new Request.Builder()
                    .url("https://auth-testing.iduruguay.gub.uy/oidc/v1/token")//TODO VER COMO PONER ESTO EN OTRO LADO
                    .addHeader("Authorization", "Basic " + Base64.getUrlDecoder().decode(CLIENT_ID + ":" + CLIENT_SECRET))
                    .addHeader("Content-Type", "application/x-www-form-urlencoded")
                    .addHeader("Accept", "application/json")
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
}
