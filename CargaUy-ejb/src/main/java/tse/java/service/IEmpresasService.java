package tse.java.service;

import javax.ejb.Local;

import tse.java.dto.EmpresaDTO;
import tse.java.model.Empresas;


@Local
public interface IEmpresasService {

    Empresas obtenerEmpresas();

    EmpresaDTO obtenerEmpresa(int id);

    public void agregarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal);

    public void modificarEmpresa(EmpresaDTO empresaDTO);

    public void eliminarEmpresa(EmpresaDTO empresaDTO);

}