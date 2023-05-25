package tse.java.service;

import tse.java.dto.UsuarioDTO;
import tse.java.entity.Usuario;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUsuariosService {

    List<UsuarioDTO> listarUsuarios();
    boolean autenticarUsuario(String username, String password);

    Usuario getUsuario(String username);

    boolean registrarUsuario(UsuarioDTO user);

    void actualizarDatos(Usuario user);
}
