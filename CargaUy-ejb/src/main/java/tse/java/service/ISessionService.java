package tse.java.service;

import tse.java.enumerated.AuthResponse;

import javax.ejb.Local;

@Local
public interface ISessionService {
    AuthResponse iniciarSesion(String username, String password);
}
