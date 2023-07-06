package tse.java.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tse.java.dto.*;


@Local
public interface IEmpresasService {

    ArrayList<EmpresaDTO> obtenerEmpresas();

    EmpresaDTO obtenerEmpresa(int id);

    int agregarEmpresa(String rut);

    void modificarEmpresa(EmpresaDTO empresaDTO);

    void eliminarEmpresa(int id);

    List<PesajeDTO> listarGuias(int numero_empresa, String matricula, String pais, Date fecha);

    public void agregarVehiculoAEmpresa(int idEmpresa, VehiculoDTO vehiculo);

    public boolean empresaContieneVehiculo(EmpresaDTO e, VehiculoDTO v);

    public void borrarVehiculo(Long id);

    public List<VehiculoDTO> listarVehiculos(int id);

    public void agregarAsignacionAEmpresa(int idEmpresa, AsignacionDTO a);

    public void borrarGuia(int numeroViaje);

    public List<EmpresaDTO> listarViajesFinalizados();


}