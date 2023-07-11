package tse.java.service;

import okhttp3.Response;
import tse.java.dto.CiudadanoFrontDTO;
import tse.java.dto.CiudadanoJwtDTO;

import javax.ejb.Local;
import java.io.IOException;

@Local
public interface IGubUyService {
    String getAuthGubUy();
    CiudadanoJwtDTO loginGubUy(String accessCode, String state) throws Exception;
    void verificarJwt(String jwt);
    CiudadanoFrontDTO getCurrentUser(String jwt);
    void logout(String token);
    String generateRandomString(int length) ;
}
