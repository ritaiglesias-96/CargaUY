package tse.java.service.impl;

import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.service.ISessionService;
import tse.java.service.IUsuariosService;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.inject.Named;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Named("sessionService")
public class SessionService implements ISessionService {

    @EJB
    IUsuariosService usuariosService;
    private final Map<String, Usuario> userConectados = new HashMap<String, Usuario>();

    @Override
    public AuthResponse iniciarSesion(String username, String password){
        if(usuariosService.autenticarUsuario(username, password)){
            userConectados.put(username, usuariosService.getUsuario(username));
            return AuthResponse.OK;
        }else{
            return AuthResponse.FAILED;
        }
    }

    @Override
    public Usuario getUsuarioLogueado(String username) {
        return userConectados.get(username);
    }

    @Override
    public void cerrarSesion(String username) {
        userConectados.remove(username);
    }
}
