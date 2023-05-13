package tse.java.persistance.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.GuiaDeViaje;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.util.qualifier.TSE2023DB;

@Stateless
public class GuiasDeViajeDAO implements IGuiaDeViajeDAO{

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaGuiaDeViaje(GuiaDeViajeDTO dtg) {
        GuiaDeViaje g = new GuiaDeViaje(dtg.getId(), dtg.getRubroCliente(), dtg.getVolumenCarga(), dtg.getFecha(), dtg.getOrigen(), dtg.getInicio(), dtg.getFin(), dtg.getDestino());
        em.persist(g);
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaDeViaje(Long id) {
        GuiaDeViaje g = em.find(GuiaDeViaje.class, id);
        return g.darDto();
    }

    @Override
    public List<GuiaDeViajeDTO> listarGuiasDeViaje() {
        List<GuiaDeViajeDTO> ret = new ArrayList<GuiaDeViajeDTO>();
        Query q = em.createQuery("select g from GuiaDeViaje g");
        List<GuiaDeViaje> guias = q.getResultList();
        for(GuiaDeViaje g:guias){
            ret.add(g.darDto());
        }
        return ret;
    }

    @Override
    public void borrarGuiaDeViaje(Long id) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, id);
        em.remove(gv);
    }

    @Override
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, dtg.getId());
        gv.setDestino(dtg.getDestino());
        gv.setFecha(dtg.getFecha());
        gv.setFin(dtg.getFin());
        gv.setInicio(dtg.getInicio());
        gv.setOrigen(dtg.getOrigen());
        gv.setRubroCliente(dtg.getRubroCliente());
        gv.setVolumenCarga(dtg.getVolumenCarga());
        em.persist(gv);
    }
    
}
