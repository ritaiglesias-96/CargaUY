package tse.java.persistance.impl;

import tse.java.entity.Usuario;
import tse.java.persistance.IUsuarioDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Stateless
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
}
