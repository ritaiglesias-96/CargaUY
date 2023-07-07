package tse.java.service;

import okhttp3.Response;
import tse.java.dto.CiudadanoJwtDTO;

import javax.ejb.Local;
import java.io.IOException;

@Local
public interface IGubUyService {
    String getAuthGubUy();
    CiudadanoJwtDTO loginGubUy(String accessCode, String state);
    String agarrarUrl(Response r) throws IOException;

    void verificarJwt(String jwt);
}
