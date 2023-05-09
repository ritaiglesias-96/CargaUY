package persistence.impl;

import dto.UsuarioDTO;
import entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import persistence.IUsuariosDAO;
import util.qualifier.TSE2023DB;

@Stateless
public class UsuariosDAO implements IUsuariosDAO {
    @TSE2023DB
    @Inject
    public EntityManager em;

    @SuppressWarnings("unchecked")
    @Override
    public ArrayList<UsuarioDTO> obtenerUsuarios() {

        Query q = em.createNativeQuery("select * from public.\"Usuario\"", Usuario.class);

        List<Usuario> result = q.getResultList();

        ArrayList<UsuarioDTO> res = new ArrayList<UsuarioDTO>();

        result.forEach(v -> res.add(new UsuarioDTO(v)));

        return res;
    }

    @Override
    public int obtenerCantidadFilas() {
        Query q = em.createNativeQuery("select count(*) from public.\"Vehiculo\"");

        int result = ((Number)q.getSingleResult()).intValue();

        return result;
    }

    @Override
    public ArrayList<UsuarioDTO> obtenerUsuariosPaginados(int pageSize, int currentPage) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public UsuarioDTO obtenerUsuario(int id) throws NoResultException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void agregarUsuario(Usuario u){
        em.persist(u);
    }

    @Override
    public void modificarUsuario(Usuario u){
        em.merge(u);
    }

    @Override
    public void borrarUsuario(Usuario u){
        em.remove(u);
    }
}
