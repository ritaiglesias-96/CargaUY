package tse.java.service;

import tse.java.dto.VehiculoDTO;
import tse.java.model.Vehiculos;

import javax.ejb.Local;

@Local
public interface IVehiculosService {
    public Vehiculos obtenerVehiculos();
    public VehiculoDTO obtenerVehiculoId(int id);
    public void agregarVehiculo(VehiculoDTO nuevoVehiculo);
    public VehiculoDTO modificarVehiculo(VehiculoDTO vehiculoModificado);
    public void eliminarVehiculo(int id);

}
