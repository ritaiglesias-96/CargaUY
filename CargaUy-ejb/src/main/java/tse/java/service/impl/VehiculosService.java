package tse.java.service.impl;

import tse.java.dto.VehiculoDTO;
import tse.java.model.Vehiculos;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.persistence.NoResultException;

public class VehiculosService implements IVehiculosService {
    @EJB
    IVehiculosDAO vehiculosDAO;
    @Override
    public Vehiculos obtenerVehiculos() {
        Vehiculos v = new Vehiculos();
        v.setListaVehiculos(vehiculosDAO.obtenerVehiculos());
        return v;
    }

    @Override
    public VehiculoDTO obtenerVehiculoId(int id) throws NoResultException {
        return vehiculosDAO.obtenerVehiculoId(id);
    }

    @Override
    public void agregarVehiculo(VehiculoDTO nuevoVehiculo) {
        vehiculosDAO.agregarVehiculo(nuevoVehiculo);
    }

    @Override
    public VehiculoDTO modificarVehiculo(VehiculoDTO vehiculoModificado) {
        return vehiculosDAO.modificarVehiculo(vehiculoModificado);
    }

    @Override
    public void eliminarVehiculo(int id) {
        vehiculosDAO.eliminarVehiculoId(id);
    }
}
