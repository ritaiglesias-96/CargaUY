package tse.java.service;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IVehiculosService {
    //public Vehiculos obtenerVehiculos();
    //public VehiculoDTO obtenerVehiculoId(int id);
    //public void agregarVehiculo(VehiculoDTO nuevoVehiculo);
    //public VehiculoDTO modificarVehiculo(VehiculoDTO vehiculoModificado);
    //public void eliminarVehiculo(int id);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public List<PesajeDTO> listarGuiasDeVehiculo(Long id, Date fecha);
}
