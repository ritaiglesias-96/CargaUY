package tse.java.service;

import javax.ejb.Local;

@Local
public interface IGubUyService {
    String getAuthGubUy();
    String loginGubUy(String accessCode, String state);
}
