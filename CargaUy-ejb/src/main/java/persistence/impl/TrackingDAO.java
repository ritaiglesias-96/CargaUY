package persistence.impl;

import entity.Tracking;
import persistence.ITrackingDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public Tracking findById(Long id) {
        return entityManager.find(Tracking.class, id);
    }

    @Override
    public void create(Tracking entity) {
        entityManager.persist(entity);
    }

    @Override
    public void update(Tracking entity) {
        entityManager.merge(entity);
    }

    @Override
    public void deleteById(Long id) {
        Tracking entity = entityManager.find(Tracking.class, id);
        if (entity != null)
            entityManager.remove(entity);
    }
}
