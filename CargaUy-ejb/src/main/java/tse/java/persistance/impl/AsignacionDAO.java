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
    public void altaAsignacion(AsignacionDTO a) {
        Asignacion anew = new Asignacion(a);
        em.persist(anew);
    }

    @Override
    public AsignacionDTO buscarAsignacion(int id) {
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
    public void borrarAsignacion(int id) {
        Asignacion a = em.find(Asignacion.class, id);
        em.remove(a);
    }

    @Override
    public AsignacionDTO ultimaIngresada() {
        Query qA = em.createQuery("select a from Asignacion a WHERE id=(SELECT max(id) FROM Asignacion )");
        Asignacion a = (Asignacion) qA.getResultList().get(0);
        return a.darDTO();
    }

}
