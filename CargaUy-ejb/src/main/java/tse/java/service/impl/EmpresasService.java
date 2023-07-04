package tse.java.service.impl;


import tse.java.dto.*;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;
import tse.java.soappdi.EmpresaServicePort;
import tse.java.soappdi.EmpresaServicePortService;
import tse.java.soappdi.GetEmpresaRequest;
import tse.java.soappdi.GetEmpresaResponse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("empresasService")
public class EmpresasService implements IEmpresasService {

    private static final Logger LOGGER = Logger.getLogger(EmpresasService.class.getName());

    @EJB
    IEmpresasDAO empresasDAO;

    @EJB
    IVehiculosService vehiculosService;

    @EJB
    IVehiculosDAO vehiculosDAO;

    @EJB
    IAsignacionesService asignacionService;


    @Override
    public ArrayList<EmpresaDTO> obtenerEmpresas() {
        return empresasDAO.obtenerEmpresas();
    }

    @Override
    public EmpresaDTO obtenerEmpresaPorGuia(int numeroGuia) {
        for(EmpresaDTO e:empresasDAO.obtenerEmpresas()){
            for(AsignacionDTO a:e.getAsignaciones()){
                if(a.getId() == asignacionService.ultimaAsignacionViaje(numeroGuia))
                    return e;
            }
        }
        return null;
    }

    @Override
    public EmpresaDTO obtenerEmpresa(int id) {
        return empresasDAO.obtenerEmpresaPorId(id);
    }

    @Override
    public int agregarEmpresa(String rut) {
        return crearEmpresaPdi(rut);
    }

    @Override
    public void modificarEmpresa(EmpresaDTO empresaDTO) {
        empresasDAO.modificarEmpresa(empresaDTO);
    }

    @Override
    public void eliminarEmpresa(int id) {
        empresasDAO.eliminarEmpresa(id);
    }

    @Override
    public List<PesajeDTO> listarGuias(int numero_empresa, String matricula, String pais, Date fecha) {
        String msg = "Me pasaron por rest los parametros: numemp=" + numero_empresa + ", pais=" + pais + ", matricula=" + matricula + ", fecha=" + fecha.toString();
        Logger.getLogger(EmpresasService.class.getName()).log(Level.INFO, msg);
        EmpresaDTO e = empresasDAO.obtenerEmpresaPorNumero(numero_empresa);
        VehiculoDTO v = vehiculosService.obtenerVehiculoMatriculaPais(matricula, pais);
        if (e != null && v != null && e.contieneVehiculo(v)) {
            Logger.getLogger(EmpresasService.class.getName()).log(Level.INFO, "Encontre el vehiculo y la empresa, busco las guias");
            return vehiculosService.listarGuiasDeVehiculo(v.getId(), fecha);
        } else {
            Logger.getLogger(EmpresasService.class.getName()).log(Level.INFO, "No se encontro el vehiculo y/o la empresa con los parametros ingresados");
            return new ArrayList<PesajeDTO>();
        }
    }

    @Override
    public void agregarVehiculoAEmpresa(int idEmpresa, VehiculoDTO vehiculo) {
        empresasDAO.agregarVehiculo(idEmpresa, vehiculo);
    }

    @Override
    public void borrarVehiculo(int idEmpresa, int idVehiculo) {
        empresasDAO.eliminarVehiculo(idEmpresa, idVehiculo);
    }

    public List<VehiculoDTO> listarVehiculos(int id) {
        EmpresaDTO empresa = obtenerEmpresa(id);
        return empresa.getVehiculos();
    }

    @Override
    public void agregarAsignacionAEmpresa(int idEmpresa, AsignacionDTO a) {
        EmpresaDTO empresa = obtenerEmpresa(idEmpresa);
        empresa.getAsignaciones().add(a);
        empresasDAO.modificarEmpresa(empresa);
    }

    @Override
    public boolean contieneChofer(int choferId, EmpresaDTO empresaDTO) {
        for (ChoferDTO c: empresaDTO.getChoferes()){
            if(c.getIdCiudadano() == choferId){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contieneVehiculo(int vehiculoId, EmpresaDTO empresaDTO) {
        for (VehiculoDTO v: empresaDTO.getVehiculos()){
            if(v.getId() == vehiculoId){
                return true;
            }
        }
        return false;
    }

    private int crearEmpresaPdi(String rut){
        // 0 - no existe la empresa, 1 - Creada ok, 2 - Error al comunicarse con la plataforma, 3 - La empresa ya existe
        try{
            EmpresaServicePortService empresaService = new EmpresaServicePortService();
            EmpresaServicePort empresaPort = empresaService.getEmpresaServicePortSoap11();
            GetEmpresaRequest empresaRequest = new GetEmpresaRequest();
            empresaRequest.setRut(rut);
            GetEmpresaResponse empresaResponse = empresaPort.getEmpresa(empresaRequest);
            tse.java.soappdi.Empresa empresa = empresaResponse.getEmpresa();

            if(empresa == null){
                return 0;
            } else if (empresasDAO.obtenerEmpresaPorNumero(empresa.getNroEmpresa()) != null) {
                return 3;
            }else {
                empresasDAO.guardarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
                return 1;
            }
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Hubo un error al comunicarse con la plataforma", e);
            return 2;
        }
    }

    private int crearEmpresaPdi(String rut){
        // 0 - no existe la empresa, 1 - Creada ok, 2 - Error al comunicarse con la plataforma, 3 - La empresa ya existe
        try{
            EmpresaServicePortService empresaService = new EmpresaServicePortService();
            EmpresaServicePort empresaPort = empresaService.getEmpresaServicePortSoap11();
            GetEmpresaRequest empresaRequest = new GetEmpresaRequest();
            empresaRequest.setRut(rut);
            GetEmpresaResponse empresaResponse = empresaPort.getEmpresa(empresaRequest);
            tse.java.soappdi.Empresa empresa = empresaResponse.getEmpresa();

            if(empresa == null){
                return 0;
            } else if (empresasDAO.obtenerEmpresaPorNumero(empresa.getNroEmpresa()) != null) {
                return 3;
            }else {
                empresasDAO.guardarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
                return 1;
            }
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Hubo un error al comunicarse con la plataforma", e);
            return 2;
        }
    }

}