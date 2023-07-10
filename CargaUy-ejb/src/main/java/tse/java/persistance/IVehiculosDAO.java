package tse.java.persistance;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface IVehiculosDAO {

    public ArrayList<VehiculoDTO> obtenerVehiculos();

    public Vehiculo obtenerVehiculoId(int id);

    public Vehiculo modificarVehiculo(VehiculoDTO vehiculo);
  
    public void eliminarVehiculo(int id);

    void agregarVehiculo(Vehiculo vehiculo);

    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);

}