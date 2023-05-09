package tse.java.persistance.impl;

import tse.java.dto.RubroDTO;
import tse.java.entity.Rubro;
import tse.java.exception.RubroExisteException;
import tse.java.persistance.IRubrosDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
@Stateless
public class RubrosDAO implements IRubrosDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaRubro(RubroDTO dtr) throws RubroExisteException {
        if(buscarRubroPorId(dtr.getId())!=null){
            throw new RubroExisteException("Ya existe un rubro con el id=" + dtr.getId());
        } else if(buscarRubroPorNombre(dtr.getNombre())!=null){
            throw new RubroExisteException("Ya existe un rubro con el nombre " + dtr.getNombre());
        } else {
            Rubro r = new Rubro();
            r.setId(dtr.getId());
            r.setNombre(dtr.getNombre());
            em.persist(r);
        }
    }

    @Override
    public RubroDTO buscarRubroPorId(Long id) {
        Rubro r = em.find(Rubro.class, id);
        return r.darDTO();
    }

    @Override
    public RubroDTO buscarRubroPorNombre(String nom) {
        Query q = em.createQuery("select r from Rubro r where r.nombre='" + nom + "'");
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            Rubro r = (Rubro) q.getResultList().get(0);
            return r.darDTO();
        }
    }

    @Override
    public List<RubroDTO> listarRubros() {
        List<RubroDTO> ret = new ArrayList<RubroDTO>();
        Query q = em.createQuery("select r from Rubro r");
        List<Rubro> rubs = q.getResultList();
        for(Rubro r:rubs){
            ret.add(r.darDTO());
        }
        return ret;
    }

    @Override
    public void borrarRubro(Long id) {
        Rubro r = em.find(Rubro.class, id);
        em.remove(r);
    }

    @Override
    public void modificarRubro(RubroDTO dtr) throws RubroExisteException {
        if(buscarRubroPorNombre(dtr.getNombre())!=null){
            throw new RubroExisteException("Ya existe un rubro con el nombre " + dtr.getNombre());
        } else {
            Rubro r = em.find(Rubro.class, dtr.getId());
            r.setNombre(dtr.getNombre());
            em.persist(r);
        }
    }
}
