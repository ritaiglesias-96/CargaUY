package tse.java.persistance.impl;


import tse.java.entity.Ciudadano;
import tse.java.entity.Responsable;
import tse.java.persistance.IResponsableDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class ResponsableDAO implements IResponsableDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;
    @Override
    public List<Responsable> findAll() {
        Query q = em.createNativeQuery("select * from public.\"ciudadano\" where rol='Responsable'", Ciudadano.class);
        return q.getResultList();
    }

    @Override
    public void agregarResponsable(Responsable responsable) {
        em.persist(responsable);
    }

    @Override
    public void modificarResponsable(Responsable responsable) {
        em.merge(responsable);
    }

    @Override
    public void eliminiarResponsable(Responsable responsable) {
        Responsable r = em.find(Responsable.class,responsable.getIdCiudadano());
        if(r!=null){
            em.remove(r);
        }
    }

}
