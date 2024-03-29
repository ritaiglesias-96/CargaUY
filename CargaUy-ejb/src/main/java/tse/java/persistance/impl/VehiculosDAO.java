package tse.java.persistance.impl;

import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.persistance.IVehiculosDAO;
import tse.java.util.qualifier.TSE2023DB;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class VehiculosDAO implements IVehiculosDAO {

    @TSE2023DB
    @Inject
    public EntityManager em;

    @Override
    public ArrayList<VehiculoDTO> obtenerVehiculos() {
        Query q = em.createQuery("select v from Vehiculo v");
        List<Vehiculo> result = q.getResultList();
        ArrayList<VehiculoDTO> res = new ArrayList<>();
        result.forEach( v -> res.add(new VehiculoDTO(v)));
        return res;
    }

    @Override
    public Vehiculo obtenerVehiculoId(int id) throws NoResultException {
        return em.find(Vehiculo.class, id);
    }

    @Override
    public Vehiculo modificarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo vehiculoMod = em.find(Vehiculo.class, vehiculo.getId());
        vehiculoMod.modificarVehiculo(vehiculo);
        em.merge(vehiculoMod);
        return vehiculoMod;
    }

    @Override
    public void eliminarVehiculo(int id) {
        Vehiculo v = em.find(Vehiculo.class, id);
        v.setAsignaciones(null);
        em.merge(v);
        Empresa e = em.find(Empresa.class, v.getEmpresa().getId());
        e.getVehiculos().remove(v);
        em.merge(e);
        em.remove(v);
    }

    @Override
    public void agregarVehiculo(VehiculoDTO vehiculo) {
        System.out.println("desde el dao " + vehiculo.getIdEmpresa() );
        Empresa e = em.find(Empresa.class, vehiculo.getIdEmpresa());
        System.out.println("desde el dao empresa " + e.getId() );
        Vehiculo nuevo = new Vehiculo(vehiculo);
        nuevo.setEmpresas(e);
        em.persist(nuevo);
        e.getVehiculos().add(nuevo);
        em.merge(e);
    }

    @Override
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais) {
        Query q = em.createQuery("select v from Vehiculo v where v.matricula='" + matricula + "' and v.pais='" + pais + "'");
        if(q.getResultList().isEmpty()) {
            return null;
        } else {
            Vehiculo v = (Vehiculo) q.getResultList().get(0);
            return new VehiculoDTO(v);
        }
    }

}
