package tse.java.service;

public interface GubUyAuth {

    public String getAuthenticationUrl();

    public String getTokens(String accessCode);

}
