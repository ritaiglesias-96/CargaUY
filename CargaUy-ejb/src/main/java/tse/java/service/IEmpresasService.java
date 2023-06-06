package tse.java.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.model.Empresas;


@Local
public interface IEmpresasService {

    Empresas obtenerEmpresas();

    EmpresaDTO obtenerEmpresa(int id);

    void agregarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal);

    void modificarEmpresa(EmpresaDTO empresaDTO);

    void eliminarEmpresa(EmpresaDTO empresaDTO);

    List<PesajeDTO> listarGuias(int numero_empresa, String matricula, String pais, Date fecha);

    public void agregarVehiculoAEmpresa(int idEmpresa, VehiculoDTO vehiculo);

    public boolean empresaContieneVehiculo(EmpresaDTO e, VehiculoDTO v);

    public void borrarVehiculo(Long id);

    public List<VehiculoDTO> listarVehiculos(int id);

}