package tse.java.persistance.impl;

import tse.java.entity.Administrador;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.persistance.IChoferDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Local
public class ChoferDAO implements IChoferDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public List<Chofer> findAll() {
        Query q = em.createNativeQuery("select * from public.\"ciudadano\" where rol='Chofer'", Ciudadano.class);
        return q.getResultList();
    }

    @Override
    public void agregarChofer(Chofer chofer) {
        em.persist(chofer);
    }

    @Override
    public void modificarChofer(Chofer chofer) {
        em.merge(chofer);
    }

    @Override
    public void eliminiarChofer(Chofer chofer) {
        Chofer c = em.find(Chofer.class,chofer.getIdCiudadano());
        if(c!=null){
            em.remove(c);
        }
    }
}
