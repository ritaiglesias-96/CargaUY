package tse.java.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tse.java.dto.*;


@Local
public interface IEmpresasService {

    ArrayList<EmpresaDTO> obtenerEmpresas();
    EmpresaDTO obtenerEmpresaPorGuia(int numeroGuia);
    EmpresaDTO obtenerEmpresa(int id);
    int agregarEmpresa(String rut);
    void modificarEmpresa(EmpresaDTO empresaDTO);
    void eliminarEmpresa(int id);
    List<PesajeDTO> listarGuias(int numero_empresa, String matricula, String pais, LocalDate fecha);
    List<VehiculoDTO> listarVehiculos(int id);
    void agregarAsignacionAEmpresa(int idEmpresa, AsignacionDTO a);
    void agregarVehiculoAEmpresa(int idEmpresa, VehiculoDTO vehiculo);
    void borrarVehiculo(int idEmpresa, int idVehiculo);
    boolean contieneChofer(int choferId, EmpresaDTO empresaDTO);
    boolean contieneVehiculo(int vehiculoId, EmpresaDTO empresaDTO);
    List<EmpresaDTO> listarViajesFinalizados();

}