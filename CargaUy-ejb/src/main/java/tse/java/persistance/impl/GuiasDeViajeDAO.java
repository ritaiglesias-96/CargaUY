package tse.java.persistance.impl;


import java.time.LocalDateTime;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tse.java.dto.ChoferDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.*;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.service.IAsignacionesService;
import tse.java.util.qualifier.TSE2023DB;

@Stateless
public class GuiasDeViajeDAO implements IGuiaDeViajeDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public void altaGuiaDeViaje(GuiaDeViajeDTO dtg, ChoferDTO c, EmpresaDTO e, VehiculoDTO v) {
        GuiaDeViaje g = new GuiaDeViaje(dtg);
        em.persist(g);
        Asignacion a = new Asignacion(g, LocalDateTime.now());
        em.persist(a);
        Chofer chofer = em.find(Chofer.class, c.getIdCiudadano());
        chofer.getAsignaciones().add(a);
        em.merge(chofer);
        Empresa empresa = em.find(Empresa.class, e.getId());
        empresa.getAsignaciones().add(a);
        em.merge(empresa);
        Vehiculo vehiculo = em.find(Vehiculo.class, v.getId());
        vehiculo.getAsignaciones().add(a);
        em.merge(vehiculo);
    }

    @Override
    public GuiaDeViaje buscarGuiaDeViaje(int id) {
        return em.find(GuiaDeViaje.class, id);
    }

    @Override
    public List<GuiaDeViajeDTO> listarGuiasDeViaje() {
        List<GuiaDeViajeDTO> ret = new ArrayList<GuiaDeViajeDTO>();
        Query q = em.createQuery("select g from GuiaDeViaje g");
        List<GuiaDeViaje> guias = q.getResultList();
        for (GuiaDeViaje g : guias) {
            ret.add(g.darDto());
        }
        return ret;
    }

    @Override
    public void borrarGuiaDeViaje(int id, int idEmpresa) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, id);
        Empresa e = em.find(Empresa.class, idEmpresa);
        List<Asignacion> aRemover = new ArrayList<>();
        List<Asignacion> nueva = new ArrayList<>();

        if (!e.getAsignaciones().isEmpty()) {
            for (Asignacion a : e.getAsignaciones()) {
                if (Objects.equals(a.getGuia().getId(), gv.getId())) {
                    aRemover.add(a);
                } else {
                    nueva.add(a);
                }
            }
            e.setAsignaciones(nueva);
            em.merge(e);
        }
        nueva.clear();
        if (!e.getVehiculos().isEmpty()) {
            for (Vehiculo v : e.getVehiculos()) {
                if (!v.getAsignaciones().isEmpty()) {
                    for (Asignacion a : v.getAsignaciones()) {
                        if (Objects.equals(a.getGuia().getId(), gv.getId())) {
                            aRemover.add(a);
                        } else {
                            nueva.add(a);
                        }
                    }
                    v.setAsignaciones(nueva);
                    em.merge(v);
                }
            }
        }
        nueva.clear();
        if (!e.getChoferes().isEmpty()) {
            for (Chofer c : e.getChoferes()) {
                if (!c.getAsignaciones().isEmpty()) {
                    for (Asignacion a : c.getAsignaciones()) {
                        if (Objects.equals(a.getGuia().getId(), gv.getId())) {
                            aRemover.add(a);
                        } else {
                            nueva.add(a);
                        }
                    }
                    c.setAsignaciones(nueva);
                    em.merge(c);
                }
            }
        }

        if (!aRemover.isEmpty()) {
            for (Asignacion a : aRemover) {
                em.remove(a);
            }
        }

        for (Pesaje p:gv.getPesajes()){
            em.remove(p);
        }

        em.remove(gv);
    }

    @Override
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg, ChoferDTO c, EmpresaDTO e, VehiculoDTO v) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, dtg.getId());
        gv.modificarGuia(dtg);
        for (Pesaje p : gv.getPesajes()) {
            em.merge(p);
        }
        em.merge(gv);

        Asignacion a = new Asignacion(gv, LocalDateTime.now());
        em.persist(a);
        Chofer chofer = em.find(Chofer.class, c.getIdCiudadano());
        chofer.getAsignaciones().add(a);
        em.merge(chofer);
        Empresa empresa = em.find(Empresa.class, e.getId());
        empresa.getAsignaciones().add(a);
        em.merge(empresa);
        Vehiculo vehiculo = em.find(Vehiculo.class, v.getId());
        vehiculo.getAsignaciones().add(a);
        em.merge(vehiculo);
    }

    @Override
    public void modificarGuiaDeViajeSinAsignacion(GuiaDeViajeDTO guia) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, guia.getId());
        gv.modificarGuia(guia);
        for (Pesaje p : gv.getPesajes()) {
            em.merge(p);
        }
        em.merge(gv);
    }

    @Override
    public int getNextNumeroViaje() {
        Query q = em.createQuery("select max(g.numero) from GuiaDeViaje g");
        if (q.getResultList().get(0) == null)
            return 1;
        else
            return Integer.parseInt(q.getResultList().get(0).toString()) + 1;
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaViajePorNumero(int numeroGuia) {
        try{
            GuiaDeViaje g =  (GuiaDeViaje) em.createQuery("FROM GuiaDeViaje WHERE numero = :numero").setParameter("numero", numeroGuia).getSingleResult();
            return new GuiaDeViajeDTO(g);
        }catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public int cantidadViajesPorAnioRubro(int anio, String rubro) {
        Query q = em.createQuery("select count(*) from GuiaDeViaje g where g.rubroCliente='" + rubro + "' and extract(year from g.fecha)=" + anio);
        return Integer.parseInt(q.getResultList().get(0).toString());
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaViajePorId(int idGuia) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, idGuia);
        return new GuiaDeViajeDTO(gv);
    }


}
