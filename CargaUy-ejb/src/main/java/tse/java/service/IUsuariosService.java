package tse.java.service;

import tse.java.entity.Usuario;

import javax.ejb.Local;

@Local
public interface IUsuariosService {
    boolean autenticarUsuario(String username, String password);

    Usuario getUsuario(String username);

    boolean registrarUsuario(Usuario user);

    void actualizarDatos(Usuario user);
}
