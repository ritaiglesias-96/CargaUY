package tse.java.service.impl;


import tse.java.dto.EmpresaDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Vehiculo;
import tse.java.model.Empresas;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    @EJB
    IVehiculosDAO vehiculosDAO;

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
    public List<PesajeDTO> listarGuias(int numero_empresa, String matricula, String pais, Date fecha) {
        String msg = "Me pasaron por rest los parametros: numemp=" + numero_empresa + ", pais=" + pais + ", matricula=" + matricula + ", fecha=" + fecha.toString();
        Logger.getLogger(EmpresasService.class.getName()).log(Level.INFO, msg);
        EmpresaDTO e = empresasDAO.obtenerEmpresaPorNumero(numero_empresa);
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais);
        if(e != null && v != null && e.contieneVehiculo(v)){
            Logger.getLogger(EmpresasService.class.getName()).log(Level.INFO, "Encontre el vehiculo y la empresa, busco las guias");
            return vehiculosService.listarGuiasDeVehiculo(v.getId(), fecha);
        } else {
            Logger.getLogger(EmpresasService.class.getName()).log(Level.INFO, "No se encontro el vehiculo y/o la empresa con los parametros ingresados");
            return new ArrayList<PesajeDTO>();
        }
    }

    public void agregarVehiculoAEmpresa(int idEmpresa, VehiculoDTO vehiculo){
        EmpresaDTO e = obtenerEmpresa(idEmpresa);
        List<VehiculoDTO> vehiculos = e.getVehiculos();
        vehiculos.add(vehiculo);
        e.setVehiculos(vehiculos);
        empresasDAO.modificarEmpresa(e);}

}