package tse.java.service;

import tse.java.dto.*;
import tse.java.entity.Vehiculo;
import tse.java.exception.VehiuloException;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IVehiculosService {

    public List<VehiculoDTO> obtenerVehiculos();
    public VehiculoDTO obtenerVehiculoPorId(int id) throws VehiuloException;
    public void agregarVehiculo(Vehiculo nuevoVehiculo);
    public void modificarVehiculo(VehiculoDTO vehiculoModificado);
    public void eliminarVehiculo(int id);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public List<PesajeDTO> listarGuiasDeVehiculo(int id, LocalDate fecha);
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g);
    public VehiculoDTO buscarVehiculoPorGuia(int numero);
}
