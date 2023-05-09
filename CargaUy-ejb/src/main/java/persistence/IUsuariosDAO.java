package persistence;

import dto.UsuarioDTO;
import entity.Usuario;
import java.util.ArrayList;
import javax.ejb.Local;
import javax.persistence.NoResultException;


@Local
public interface IUsuariosDAO {

    public ArrayList<UsuarioDTO> obtenerUsuarios();

    public int obtenerCantidadFilas();

    public ArrayList<UsuarioDTO> obtenerUsuariosPaginados(int pageSize, int currentPage);

    public UsuarioDTO obtenerUsuario(int id) throws NoResultException;

    public void agregarUsuario(Usuario u);

    public void modificarUsuario(Usuario u);

    public void borrarUsuario(Usuario u);
}
