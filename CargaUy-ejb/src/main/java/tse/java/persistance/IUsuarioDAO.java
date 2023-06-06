package tse.java.persistance;

import tse.java.dto.UsuarioDTO;
import tse.java.entity.Usuario;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IUsuarioDAO {
    Usuario findByUsername(String username);
    List<UsuarioDTO> listarUsuarios();
    boolean existUserByUsername(String username);
}
