package tse.java.persistance.impl;

import tse.java.dto.UsuarioDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;
import tse.java.enumerated.TipoUsuario;
import tse.java.persistance.IUsuarioDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
@Local
public class UsuarioDAO implements IUsuarioDAO {
    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public Usuario findByUsername(String username) {
        try {
            return (Usuario)em.createQuery("FROM Usuario WHERE username = :username").setParameter("username", username).getSingleResult();
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public boolean existUserByUsername(String username) {
        try {
            em.createQuery("FROM Usuario WHERE username = :username").setParameter("username", username).getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }
    }

    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioDTO> lista = new ArrayList<>();
        Query q = em.createQuery("select u from Usuario u");
        List<Usuario> usuarios = q.getResultList();
        for(Usuario u:usuarios) {
            if(u instanceof Administrador){
                lista.add(new UsuarioDTO(u, TipoUsuario.ADMIN));
            }
            if(u instanceof Autoridad){
                lista.add(new UsuarioDTO(u, TipoUsuario.AUTORIDAD));
            }
        }
        return lista;
    }
}
