package tse.java.service.external;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLParameters;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadLocalRandom;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class GubUyAuthImpl implements GubUyAuth{
    private static final String AUTH_ENDPOINT = "https://auth-testing.iduruguay.gub.uy/oidc/v1/authorize?";
    private static final String CLIENT_ID = "890192";
    private static final String CLIENT_SECRET ="457d52f181bf11804a3365b49ae4d29a2e03bbabe74997a2f510b179" ;
    private static final String ENDPOINT_TOKEN = "https://auth-testing.iduruguay.gub.uy/oidc/v1/token";
    private static final String REDIRECT_URI = "https://openidconnect.net/";


    @Override
    public String getAuthenticationUrl() {
        int random = ThreadLocalRandom.current().nextInt(0, 999999);
        String state = "asdf" + random;
        String url = AUTH_ENDPOINT;
        url += "response_type=code";
        url += "&scope=openid%20personal%20email";
        url += "&client_id=" + CLIENT_ID;
        url += "&state=" + state;
        url += "&redirect_uri=" + URLEncoder.encode(REDIRECT_URI);
        return url;
    }

    @Override
    public String getTokens(String accessCode) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(ENDPOINT_TOKEN))
                .POST(HttpRequest.BodyPublishers.ofString("{\"grant_type\":\"authorization_code\",\"code\":\""+accessCode+"\",\"redirect_uri\":\"https://openidconnect.net/\""))
                .header("Authorization", "Basic " + Base64.getEncoder().encodeToString((CLIENT_ID + ":" + CLIENT_SECRET).getBytes()))
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept", "application/json")
                .build();
        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            String responseBody = response.body();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
