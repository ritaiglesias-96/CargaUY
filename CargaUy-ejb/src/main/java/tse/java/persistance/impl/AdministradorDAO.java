package tse.java.persistance.impl;

import tse.java.entity.Administrador;
import tse.java.persistance.IAdministradorDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.sql.Date;

@Stateless
@Local
public class AdministradorDAO implements IAdministradorDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void persist(Administrador administrador) {
        em.persist(administrador);
    }

    @Override
    public void merge(Administrador administrador) {
        em.merge(administrador);
    }

    @Override
    public List<Administrador> findAll() {
        Query q = em.createQuery("SELECT r FROM Administrador r", Administrador.class);
        return q.getResultList();
    }

    @Override
    public void delete(Administrador administrador) {
        em.remove(em.contains(administrador) ? administrador : em.merge(administrador));
    }

    @Override
    public Administrador findById(Integer id) {
        return em.find(Administrador.class, id);
    }
}
