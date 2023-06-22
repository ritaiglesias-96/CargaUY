package tse.java.persistance;


import javax.ejb.Local;
import java.util.ArrayList;

import tse.java.dto.EmpresaDTO;
import tse.java.entity.Empresa;

@Local
public interface IEmpresasDAO {

    EmpresaDTO obtenerEmpresaPorId(int id);

    EmpresaDTO obtenerEmpresaPorNumero(int numero_empresa);

    ArrayList<EmpresaDTO> obtenerEmpresas();

    void guardarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal);

    Empresa modificarEmpresa(EmpresaDTO empresaDTO);

    void eliminarEmpresa(EmpresaDTO empresaDTO);
}