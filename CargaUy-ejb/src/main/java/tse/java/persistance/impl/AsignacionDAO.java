package tse.java.persistance.impl;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.RubroDTO;
import tse.java.entity.Asignacion;
import tse.java.entity.Rubro;
import tse.java.persistance.IAsignacionDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AsignacionDAO implements IAsignacionDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public Long altaAsignacion(AsignacionDTO a) {
        Asignacion anew = new Asignacion(a);
        em.persist(anew);
        Query q = em.createQuery("select max(a.id) from Asignacion a");
        return Long.valueOf(q.getResultList().get(0).toString());
    }

    @Override
    public AsignacionDTO buscarAsignacion(Long id) {
        return em.find(Asignacion.class, id).darDTO();
    }

    @Override
    public void modificarAsignacion(AsignacionDTO a) {
        Asignacion amod = em.find(Asignacion.class, a.getId());
        amod.setFechaCambio(a.getFechaCambio());
        amod.setGuia(amod.convertirGuia(a.getGuia()));
        em.persist(amod);
    }

    @Override
    public List<AsignacionDTO> listarAsignaciones() {
        List<AsignacionDTO> ret = new ArrayList<AsignacionDTO>();
        Query q = em.createQuery("select a from Asignacion a");
        List<Asignacion> asignaciones = q.getResultList();
        for(Asignacion a : asignaciones){
            ret.add(a.darDTO());
        }
        return ret;
    }

    @Override
    public void borrarAsignacion(Long id) {
        Asignacion a = em.find(Asignacion.class, id);
        em.remove(a);
    }

}
