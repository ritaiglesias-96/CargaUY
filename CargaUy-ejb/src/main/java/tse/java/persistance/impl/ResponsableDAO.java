package tse.java.persistance.impl;


import tse.java.dto.ResponsableDTO;
import tse.java.entity.Chofer;
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

    @Override
    public ResponsableDTO buscarResponsablePorCedula(String cedula) {
        Query q = em.createQuery("select c from Ciudadano c where c.cedula='" + cedula + "'");
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            Object o = q.getResultList().get(0);
            if(o instanceof Responsable){
                Responsable r = (Responsable) o;
                return r.darDTO();
            } else {
                return null;
            }
        }
    }

}
