package tse.java.service.external;

public interface GubUyAuth {

    public String getAuthenticationUrl();

    public String getTokens(String accessCode);

}
