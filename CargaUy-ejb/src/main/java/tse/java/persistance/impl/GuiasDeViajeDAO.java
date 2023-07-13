package tse.java.persistance.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

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
            ret.add(new GuiaDeViajeDTO(g));
        }
        return ret;
    }

    @Override
    public void borrarGuiaDeViaje(int id, int idEmpresa) {
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, id);
        Empresa e = em.find(Empresa.class, idEmpresa);
        List<Asignacion> aRemover = new ArrayList<>();

        // Remove Asignaciones from Empresa
        e.getAsignaciones().removeIf(a -> {
            if (a.getGuia().getId() == id) {
                aRemover.add(a);
                return true;
            }
            return false;
        });
        em.merge(e);

        // Remove Asignaciones from Vehiculos
        e.getVehiculos().forEach(v -> {
            v.getAsignaciones().removeIf(a -> {
                if (a.getGuia().getId() == id) {
                    aRemover.add(a);
                    return true;
                }
                return false;
            });
            em.merge(v);
        });

        // Remove Asignaciones from Choferes
        e.getChoferes().forEach(c -> {
            c.getAsignaciones().removeIf(a -> {
                if (a.getGuia().getId() == id) {
                    aRemover.add(a);
                    return true;
                }
                return false;
            });
            em.merge(c);
        });

        // Remove Asignaciones from entity manager
        aRemover.forEach(em::remove);

        // Remove Pesajes
        gv.getPesajes().forEach(em::remove);

        // Remove GuiaDeViaje
        em.remove(gv);
    }


    @Override
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg, ChoferDTO c, EmpresaDTO e, VehiculoDTO v) {
        System.out.println(dtg.getId());
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
        if (gv != null)
            return new GuiaDeViajeDTO(gv);
        else
            return null;
    }


}
