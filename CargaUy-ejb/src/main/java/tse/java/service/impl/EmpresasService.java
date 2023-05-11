package tse.java.service.impl;

import tse.java.dto.EmpresaDTO;
import tse.java.model.Empresas;
import tse.java.persistance.IEmpresasDAO;
import tse.java.service.IEmpresasService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("empresasService")
public class EmpresasService implements IEmpresasService {

    @EJB
    IEmpresasDAO empresasDAO;

    @Override
    public Empresas obtenerEmpresas() {
        Empresas e = new Empresas();
        e.setListaEmpresas(empresasDAO.obtenerEmpresas());

        return e;
    }

    @Override
    public EmpresaDTO obtenerEmpresa(int id) {
            return empresasDAO.obtenerEmpresaPorId(id);
    }

    @Override
    public void agregarEmpresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal) {
        empresasDAO.guardarEmpresa(nombrePublico,razonSocial,nroEmpresa,dirPrincipal);
    }

    @Override
    public void modificarEmpresa(EmpresaDTO empresaDTO) {
        empresasDAO.modificarEmpresa(empresaDTO);
    }

    @Override
    public void eliminarEmpresa(EmpresaDTO empresaDTO) {
        empresasDAO.eliminarEmpresa(empresaDTO);
    }

}