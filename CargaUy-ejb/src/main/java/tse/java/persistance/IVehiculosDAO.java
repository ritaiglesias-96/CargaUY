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
    public Vehiculo obtenerVehiculoId(Long id);
    public VehiculoDTO modificarVehiculo(VehiculoDTO vehiculo);
    public void eliminarVehiculo(Long id);
    public void agregarVehiculo(VehiculoDTO vehiculo);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public Long getNextIdVehiculo();
    public EmpresaDTO obtenerEmpresaDeVehiculo(Long id);
}