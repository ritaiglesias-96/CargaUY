package tse.java.service;

import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;

import javax.ejb.Local;

@Local
public interface ISessionService {
    AuthResponse iniciarSesion(String username, String password);

    Usuario getUsuarioLogueado(String username);

    void cerrarSesion(String username);
}
