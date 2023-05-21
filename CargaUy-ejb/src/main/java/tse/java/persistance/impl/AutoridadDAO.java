package tse.java.persistance.impl;

import tse.java.entity.Autoridad;
import tse.java.persistance.IAutoridadDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class AutoridadDAO implements IAutoridadDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void persist(Autoridad autoridad) {
        em.persist(autoridad);
    }

    @Override
    public void merge(Autoridad autoridad) {
        em.merge(autoridad);
    }

    @Override
    public List<Autoridad> findAll() {
        Query q = em.createQuery("SELECT f FROM Autoridad f", Autoridad.class);
        return q.getResultList();
    }

    @Override
    public void delete(Autoridad autoridad) {
        em.remove(em.contains(autoridad) ? autoridad : em.merge(autoridad));
    }

    @Override
    public Autoridad findById(Integer id) {
        return em.find(Autoridad.class, id);
    }
}
