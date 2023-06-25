package tse.java.persistance.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.entity.Asignacion;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Pesaje;
import tse.java.entity.Rubro;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.util.qualifier.TSE2023DB;

@Stateless
public class GuiasDeViajeDAO implements IGuiaDeViajeDAO{

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaGuiaDeViaje(GuiaDeViajeDTO dtg) {
        GuiaDeViaje g = new GuiaDeViaje(dtg.getId(), dtg.getNumero(), dtg.getRubroCliente(), dtg.getTipoCarga(),dtg.getVolumenCarga(), dtg.getFecha(), dtg.getOrigen(), dtg.getInicio(), dtg.getFin(), dtg.getDestino(), new ArrayList<Pesaje>());
        em.persist(g);
    }

    @Override
    public GuiaDeViaje buscarGuiaDeViaje(Long id) {
        return em.find(GuiaDeViaje.class, id);
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
        gv.setTipoCarga(dtg.getTipoCarga());
        gv.setPesajes(gv.procesarListaPesajes(dtg.getPesajes()));
        em.persist(gv);
    }

    @Override
    public int getNextNumeroViaje() {
        Query q = em.createQuery("select max(g.numero) from GuiaDeViaje g");
        if(q.getResultList().get(0)==null)
            return 1;
        else
            return Integer.parseInt(q.getResultList().get(0).toString())+1;
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaViajePorNumero(int numero_guia) {
        Query q = em.createQuery("select g from GuiaDeViaje g where g.numero=" + numero_guia);
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            GuiaDeViaje g = (GuiaDeViaje) q.getResultList().get(0);
            return g.darDto();
        }
    }

    @Override
    public int cantidadViajesPorAnioRubro(int anio, String rubro) {
        Query q = em.createQuery("select count(*) from GuiaDeViaje g where g.rubroCliente='" + rubro + "' and extract(year from g.fecha)=" + anio);
        return Integer.parseInt(q.getResultList().get(0).toString());
    }


}
