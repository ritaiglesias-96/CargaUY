package tse.java.persistance.impl;

import tse.java.dto.TrackingDTO;
import tse.java.entity.Tracking;
import tse.java.persistance.ITrackingDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class TrackingDAO implements ITrackingDAO {



    @PersistenceContext(unitName = "PersistenceJPA")
    private EntityManager entityManager;

    @Override
    public List<Tracking> listAll(Integer startPosition, Integer maxResult) {
        TypedQuery<Tracking> query = entityManager.createQuery("FROM Tracking", Tracking.class);

        if (startPosition != null) {
            query.setFirstResult(startPosition);
        }
        if (maxResult != null) {
            query.setMaxResults(maxResult);
        }
        return query.getResultList();
    }

    @Override
    public TrackingDTO find(String matricula, String pais) {
        Query q = entityManager.createQuery("select t from Tracking t where t.matricula='" + matricula + "' and t.pais='" + pais + "'");
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            Tracking t = (Tracking) q.getResultList().get(0);
            return t.getDto();
        }
    }

    @Override
    public void create(Tracking entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Tracking entity) {
        entityManager.merge(entity);
    }
/*
    @Override
    public void delete(String matricula, String pais) {
        Query q = entityManager.createQuery("select t from Tracking t where t.matricula='" + nom + "'");
        Tracking entity = entityManager.find(Tracking.class, id);
        if (entity != null)
            entityManager.remove(entity);
    }*/
}
