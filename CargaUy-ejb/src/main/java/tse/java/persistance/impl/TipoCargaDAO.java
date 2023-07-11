package tse.java.persistance.impl;

import tse.java.dto.RubroDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.entity.Rubro;
import tse.java.entity.TipoCarga;
import tse.java.exception.RubroExisteException;
import tse.java.exception.TipoCargaExisteException;
import tse.java.persistance.ITipoCargaDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TipoCargaDAO implements ITipoCargaDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaTipoCarga(TipoCargaDTO tdt) throws TipoCargaExisteException {
        System.out.println(buscarTipoCargaPorId(tdt.getId())!=null);
        if(buscarTipoCargaPorId(tdt.getId())!=null){
            throw new TipoCargaExisteException("Ya existe un tipo de carga con el id=" + tdt.getId());
        } else if(buscarTipoCargaPorNombre(tdt.getNombre())!=null){
            throw new TipoCargaExisteException("Ya existe un tipo de carga con el nombre " + tdt.getNombre());
        } else {
            TipoCarga tc = new TipoCarga();
            tc.setId(tdt.getId());
            tc.setNombre(tdt.getNombre());
            em.persist(tc);
        }

    }

    @Override
    public TipoCargaDTO buscarTipoCargaPorId(Long id) {
        System.out.println(id);
        if (id == null) {
            return null;
        } else {
            return em.find(TipoCarga.class, id).darDTO();
        }
    }

    @Override
    public TipoCargaDTO buscarTipoCargaPorNombre(String nombre) {
        Query q = em.createQuery("select t from TipoCarga t where t.nombre='" + nombre + "'");
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            TipoCarga tc = (TipoCarga) q.getResultList().get(0);
            return tc.darDTO();
        }
    }

    @Override
    public List<TipoCargaDTO> listarTipoCarga() {
        List<TipoCargaDTO> ret = new ArrayList<TipoCargaDTO>();
        Query q = em.createQuery("select t from TipoCarga t");
        List<TipoCarga> tipocargas = q.getResultList();
        for(TipoCarga t : tipocargas){
            ret.add(t.darDTO());
        }
        return ret;
    }

    @Override
    public void borrarTipoCarga(Long id) {
        TipoCarga tc = em.find(TipoCarga.class, id);
        em.remove(tc);
    }

    @Override
    public void modificarTipoCarga(TipoCargaDTO tdt) throws TipoCargaExisteException {
        if(buscarTipoCargaPorNombre(tdt.getNombre())!=null){
            throw new TipoCargaExisteException("Ya existe un tipo de carga con el nombre " + tdt.getNombre());
        } else {
            TipoCarga t = em.find(TipoCarga.class, tdt.getId());
            t.setNombre(tdt.getNombre());
            em.persist(t);
        }
    }
}
