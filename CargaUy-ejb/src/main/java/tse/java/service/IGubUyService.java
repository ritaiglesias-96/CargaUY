package tse.java.service;

import okhttp3.Response;

import javax.ejb.Local;
import java.io.IOException;

@Local
public interface IGubUyService {
    String getAuthGubUy();
    String loginGubUy(String accessCode, String state);

    String agarrarUrl(Response r) throws IOException;
}
