package tse.java.service;

import tse.java.dto.*;
import tse.java.entity.Vehiculo;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IVehiculosService {

    public List<VehiculoDTO> obtenerVehiculos();
    public VehiculoDTO obtenerVehiculoPorId(Long id);
    public void agregarVehiculo(Vehiculo nuevoVehiculo);
    public void modificarVehiculo(VehiculoDTO vehiculoModificado);
    public void eliminarVehiculo(Long id);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public List<PesajeDTO> listarGuiasDeVehiculo(Long id, Date fecha);
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g);
    public void asignarGuia(Long vehiculo_id, AsignacionDTO a);
    public void borrarGuia(int numero_guia);
    public VehiculoDTO buscarVehiculoPorGuia(int numero);
}
