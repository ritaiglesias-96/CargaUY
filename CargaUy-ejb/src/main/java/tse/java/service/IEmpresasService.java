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

    public void agregarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal);

    public void modificarEmpresa(EmpresaDTO empresaDTO);

    public void eliminarEmpresa(EmpresaDTO empresaDTO);

    public List<PesajeDTO> listarGuias(int numero_empresa, String matricula, String pais, Date fecha);

    public List<VehiculoDTO> listarVehiculos(int id);

}