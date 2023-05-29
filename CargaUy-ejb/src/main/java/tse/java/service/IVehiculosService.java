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
    //public void eliminarVehiculo(int id);
    public void modificarVehiculo(VehiculoDTO vehiculoModificado);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public List<PesajeDTO> listarGuiasDeVehiculo(Long id, Date fecha);
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g);
    public void asignarGuia(Long vehiculo_id, GuiaDeViajeDTO g);
}
