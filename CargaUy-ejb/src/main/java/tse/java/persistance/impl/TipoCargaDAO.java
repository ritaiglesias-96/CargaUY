package tse.java.persistance.impl;

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
import java.util.List;

@Stateless
public class TipoCargaDAO implements ITipoCargaDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaTipoCarga(TipoCargaDTO dttc) throws TipoCargaExisteException {
        if(buscarTipoCargaPorId(dttc.getId())!=null){
            throw new TipoCargaExisteException("Ya existe un tipo de carga con el id: "+dttc.getId());
        }else{
            TipoCarga tc = new TipoCarga();
            tc.setId(dttc.getId());
            tc.setNombre(dttc.getNombre());
            em.persist(tc);
        }
    }

    @Override
    public TipoCargaDTO buscarTipoCargaPorId(Long id) {
        if (id == null) {
            return null;
        } else {
            TipoCarga tc = em.find(TipoCarga.class, id);
            return tc.getDTO();
        }
    }

    @Override
    public TipoCargaDTO buscarTipoCargaPorNombre(String nom) {
        Query q = em.createQuery("SELECT tc FROM TipoCarga tc WHERE tc.nombre='" + nom + "'");
        if(q.getResultList().isEmpty()){
            return null;
        }else {
            TipoCarga tc = (TipoCarga) q.getResultList().get(0);
            return tc.getDTO();
        }
    }

    @Override
    public List<TipoCargaDTO> listarTipoCarga() {
        return null;
    }

    @Override
    public void borrarTipoCarga(Long id) {
        TipoCarga tc = em.find(TipoCarga.class, id);
        em.remove(tc);
    }

    @Override
    public void modificarTipoCarga(TipoCargaDTO dttc) throws TipoCargaExisteException {
        if(buscarTipoCargaPorNombre(dttc.getNombre())!=null){
            throw new TipoCargaExisteException("Ya existe un tipo de carga con el nombre " + dttc.getNombre());
        } else {
            TipoCarga tc = em.find(TipoCarga.class, dttc.getId());
            tc.setNombre(dttc.getNombre());
            em.merge(tc);
        }
    }
}
