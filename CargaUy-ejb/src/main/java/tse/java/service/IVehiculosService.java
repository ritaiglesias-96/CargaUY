package tse.java.service;

import tse.java.dto.*;
import tse.java.entity.Vehiculo;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IVehiculosService {

    public List<VehiculoDTO> obtenerVehiculos();
    public VehiculoDTO obtenerVehiculoPorId(int id);
    public void agregarVehiculo(Vehiculo nuevoVehiculo);
    public void modificarVehiculo(VehiculoDTO vehiculoModificado);
    public void eliminarVehiculo(int id);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public List<PesajeDTO> listarGuiasDeVehiculo(int id, Date fecha);
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g);
    public void asignarGuia(int vehiculo_id, AsignacionDTO a);
    public void borrarGuia(int numero_guia);
    public VehiculoDTO buscarVehiculoPorGuia(int numero);
}
