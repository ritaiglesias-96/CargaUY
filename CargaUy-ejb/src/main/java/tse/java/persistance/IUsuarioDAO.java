package tse.java.persistance;

import tse.java.entity.Usuario;

import javax.ejb.Local;

@Local
public interface IUsuarioDAO {
    public Usuario findByUsername(String username);

    public boolean existUserByUsername(String username);
}
