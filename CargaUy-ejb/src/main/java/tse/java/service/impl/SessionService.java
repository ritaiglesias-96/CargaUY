package tse.java.service.impl;

import tse.java.enumerated.AuthResponse;
import tse.java.service.ISessionService;
import tse.java.service.IUsuariosService;

import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class SessionService implements ISessionService {

    @EJB
    private IUsuariosService usuariosService;

    @Override
    public AuthResponse iniciarSesion(String username, String password){
        if(usuariosService.autenticarUsuario(username, password)){
            return AuthResponse.OK;
        }else{
            return AuthResponse.FAILED;
        }
    }
}
