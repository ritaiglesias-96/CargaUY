package service.impl;

import dto.UsuarioDTO;
import javax.ejb.EJB;
import javax.persistence.NoResultException;
import persistence.model.Usuarios;
import persistence.IUsuariosDAO;
import service.IUsuariosService;

public class UsuariosService implements IUsuariosService{

    @EJB
    IUsuariosDAO usuariosDAO;

    @Override
    public Usuarios obtenerUsuarios() {

        Usuarios u = new Usuarios();
        u.setListaUsuarios(usuariosDAO.obtenerUsuarios());


        return u;
    }

    @Override
    public Usuarios obtenerUsuariosPaginados(int pageSize, int currentPage) {

        Usuarios u = new Usuarios();
        u.setListaUsuarios(usuariosDAO.obtenerUsuariosPaginados(pageSize, currentPage));
        u.setTotalRows(usuariosDAO.obtenerCantidadFilas());

        return u;
    }

    @Override
    public UsuarioDTO obtenerUsuario(int id) throws NoResultException {
        UsuarioDTO u = usuariosDAO.obtenerUsuario(id);

        return u;
    }

}
