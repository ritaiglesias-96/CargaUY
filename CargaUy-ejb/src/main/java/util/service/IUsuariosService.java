package util.service;

import dto.UsuarioDTO;
import javax.ejb.Local;
import javax.persistence.NoResultException;
import persistence.model.Usuarios;

@Local
public interface IUsuariosService {

    public Usuarios obtenerUsuarios();

    public Usuarios obtenerUsuariosPaginados(int pageSize, int currentPage);

    public UsuarioDTO obtenerUsuario(int id) throws NoResultException;

}
