package tse.java.persistance.impl;


import tse.java.dto.ResponsableDTO;
import tse.java.entity.*;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IResponsableDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.EJB;
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
    @EJB
    IEmpresasDAO empresasDAO;
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
    public void asignarEmpresa(int id, Empresa empresa) {
         Responsable responsable = em.find(Responsable.class, id);
         Empresa empresa1 = em.find(Empresa.class, empresa.getId());
         responsable.setEmpresa(empresa1);
         empresa1.setResponsable(responsable);
         em.merge(responsable);
         em.merge(empresa1);
    }

    @Override
    public void eliminarEmpresa(int id, Empresa empresa) {
        Responsable responsable = em.find(Responsable.class, id);
        responsable.setEmpresa(null);
        empresa.setResponsable(null);
        em.merge(responsable);
        em.merge(empresa);
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
                return r.darDto();
            } else {
                return null;
            }
        }
    }

}
