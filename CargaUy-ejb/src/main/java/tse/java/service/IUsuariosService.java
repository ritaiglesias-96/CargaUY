package tse.java.service;

import tse.java.dto.UsuarioDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;
import tse.java.exception.UsuarioExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUsuariosService {

    List<UsuarioDTO> listarUsuarios();
    boolean autenticarUsuario(String username, String password);

    Usuario getUsuario(String username);

    Administrador getAdminByID(int id);
    Autoridad getAutoByID(int id);

    boolean registrarUsuario(UsuarioDTO user);

    void actualizarDatos(Usuario user);

    void eliminarUsuario(UsuarioDTO user) throws UsuarioExisteException;
}
