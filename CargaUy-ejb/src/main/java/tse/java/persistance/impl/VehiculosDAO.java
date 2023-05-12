package tse.java.persistance.impl;

import tse.java.dto.VehiculoDTO;
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
    public VehiculoDTO obtenerVehiculoId(int id) throws NoResultException {
        Vehiculo result = em.find(Vehiculo.class, id);
        return new VehiculoDTO(result);
    }

    @Override
    public VehiculoDTO modificarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo updated = new Vehiculo(vehiculo);
        Vehiculo newOne = em.merge(updated);
        return new VehiculoDTO(newOne);
    }

    @Override
    public void eliminarVehiculoId(int id) {
        Vehiculo result = em.find(Vehiculo.class, id);
        em.remove(result);
    }

    @Override
    public void agregarVehiculo(VehiculoDTO vehiculo) {
        Vehiculo nuevo = new Vehiculo(vehiculo);
        em.merge(nuevo);
    }
}
