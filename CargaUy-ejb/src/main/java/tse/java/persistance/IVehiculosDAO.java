package tse.java.persistance;

import tse.java.dto.VehiculoDTO;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface IVehiculosDAO {

    public ArrayList<VehiculoDTO> obtenerVehiculos();
    public VehiculoDTO obtenerVehiculoId(int id);
    public VehiculoDTO modificarVehiculo(VehiculoDTO vehiculo);
    public void eliminarVehiculoId(int id);
    public void  agregarVehiculo(VehiculoDTO vehiculo);

}