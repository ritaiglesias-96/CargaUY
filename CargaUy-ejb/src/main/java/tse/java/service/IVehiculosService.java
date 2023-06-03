package tse.java.service;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.model.Empresas;
import tse.java.model.Vehiculos;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

@Local
public interface IVehiculosService {

    public Vehiculos obtenerVehiculos();
    public VehiculoDTO obtenerVehiculoPorId(Long id);
    public void agregarVehiculo(VehiculoDTO nuevoVehiculo);
    public void modificarVehiculo(VehiculoDTO vehiculoModificado);
    public void eliminarVehiculo(Long id);
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais);
    public List<PesajeDTO> listarGuiasDeVehiculo(Long id, Date fecha);
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g);
    public void asignarGuia(Long vehiculo_id, GuiaDeViajeDTO g);
    public void borrarGuia(int numero_guia);
    public GuiaDeViajeDTO buscarGuiaenVehiculos(VehiculoDTO v, GuiaDeViajeDTO g);
}
