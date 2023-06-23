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
    public Vehiculo obtenerVehiculoId(Long id) throws NoResultException {
        Vehiculo result = em.find(Vehiculo.class, id);
        return result;
    }

    @Override
    public Vehiculo modificarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo vehiculoMod = new Vehiculo(vehiculo);
        em.merge(vehiculoMod);
        return vehiculoMod;
    }

    @Override
    public void eliminarVehiculo(Long id) {
        Vehiculo v = em.find(Vehiculo.class, id);
        Empresa e = em.find(Empresa.class, v.getEmpresa().getId());
        e.getVehiculos().remove(v);
        em.merge(e);
        em.remove(v);
    }

    @Override
    public void agregarVehiculo(Vehiculo vehiculo) {
        Empresa e = em.find(Empresa.class, vehiculo.getEmpresa().getId());
        e.getVehiculos().add(vehiculo);
        em.persist(vehiculo);
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

    public Long getNextIdVehiculo(){
        Query q = em.createQuery("select max(g.id) from GuiaDeViaje g");
        if(q.getResultList().get(0)==null)
            return (long) 1;
        else
            return Long.parseLong(q.getResultList().get(0).toString())+1;
    }

}
