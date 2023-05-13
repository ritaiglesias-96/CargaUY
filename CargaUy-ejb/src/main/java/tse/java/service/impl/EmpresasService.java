package tse.java.service.impl;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.model.Empresas;
import tse.java.persistance.IEmpresasDAO;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("empresasService")
public class EmpresasService implements IEmpresasService {

    @EJB
    IEmpresasDAO empresasDAO;

    @EJB
    IVehiculosService vehiculosService;

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

    @Override
    public List<GuiaDeViajeDTO> listarGuias(int numero_empresa, String matricula, String pais, Date fecha) {
        EmpresaDTO e = empresasDAO.obtenerEmpresaPorNumero(numero_empresa);
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais);
        if(e != null && v != null && e.getVehiculos().contains(v)){
            return vehiculosService.listarGuiasDeVehiculo(v.getId(), fecha);
        } else {
            return new ArrayList<GuiaDeViajeDTO>();
        }
    }

}