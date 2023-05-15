package tse.java.persistance.impl;

import tse.java.dto.PesajeDTO;
import tse.java.dto.RubroDTO;
import tse.java.entity.Pesaje;
import tse.java.entity.Rubro;
import tse.java.persistance.IPesajesDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PesajeDAO implements IPesajesDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaPesaje(PesajeDTO p) {
        Pesaje p1 = new Pesaje(null, p.getLatitud(),p.getLonguitud(),p.getFecha(),p.getCarga());
        em.persist(p1);
    }

    @Override
    public PesajeDTO buscarPesaje(Long id) {
        Pesaje p = em.find(Pesaje.class, id);
        return p.darDTO();
    }

    @Override
    public List<PesajeDTO> listarPesajes() {
        List<PesajeDTO> ret = new ArrayList<PesajeDTO>();
        Query q = em.createQuery("select p from Pesaje p");
        List<Pesaje> pesajes = q.getResultList();
        for(Pesaje p:pesajes){
            ret.add(p.darDTO());
        }
        return ret;
    }

    @Override
    public void borrarPesaje(Long id) {
        Pesaje p = em.find(Pesaje.class, id);
        em.remove(p);
    }
}
