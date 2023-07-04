package tse.java.persistance.impl;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
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

    @EJB
    IAsignacionesService asignacionesService;

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
        System.out.println("gv en eliminar:" + gv.getId());
        Empresa e = em.find(Empresa.class, idEmpresa);
        System.out.println("e en eliminar:" + e.getId());
        List<Asignacion> aRemover = new ArrayList<>();
        List<Asignacion> nueva = new ArrayList<>();

        System.out.println("hay asignaciones e: " + !e.getAsignaciones().isEmpty());
        if (!e.getAsignaciones().isEmpty()) {
            for (Asignacion a : e.getAsignaciones()) {
                System.out.println("Hay asignaciones");
                System.out.println("Hay asignaciones con esa guia: " + Objects.equals(a.getGuia().getId(), gv.getId()));
                if (Objects.equals(a.getGuia().getId(), gv.getId())) {
                    aRemover.add(a);
                } else {
                    nueva.add(a);
                }
            }
            e.setAsignaciones(nueva);
            em.merge(e);
            System.out.println("hay asignaciones e: " + !e.getAsignaciones().isEmpty());
        }
        nueva.clear();
        if (!e.getVehiculos().isEmpty()) {
            System.out.println("Hay vehiculos");
            for (Vehiculo v : e.getVehiculos()) {
                if (!v.getAsignaciones().isEmpty()) {
                    System.out.println("Hay asignaciones v");
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
            System.out.println("Hay choferes");
            for (Chofer c : e.getChoferes()) {
                if (!c.getAsignaciones().isEmpty()) {
                    System.out.println("Hay choferes cn asig");
                    for (Asignacion a : c.getAsignaciones()) {
                        if (Objects.equals(a.getGuia().getId(), gv.getId())) {
                            System.out.println("Hay choferes cn asig con gv");
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

        em.remove(gv);
    }

    @Override
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg, ChoferDTO c, EmpresaDTO e, VehiculoDTO v) {
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
        em.merge(gv);
    }

    ;

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
        Query q = em.createQuery("select g from GuiaDeViaje g where g.numero=" + numeroGuia);
        if (q.getResultList().isEmpty()) {
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

    @Override
    public GuiaDeViajeDTO buscarGuiaViajePorId(int idGuia) {
        System.out.println("llega dao");
        GuiaDeViaje gv = em.find(GuiaDeViaje.class, idGuia);
        System.out.println("obtiene gv:" + gv.getId());
        GuiaDeViajeDTO gvDTO = gv.darDto();
        System.out.println("devuelve el dt:" + gvDTO.getId());
        return gvDTO;
    }


}
