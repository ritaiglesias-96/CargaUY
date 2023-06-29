package tse.java.service.impl;


import tse.java.dto.*;
import tse.java.entity.Vehiculo;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;
import tse.java.soappdi.EmpresaServicePort;
import tse.java.soappdi.EmpresaServicePortService;
import tse.java.soappdi.GetEmpresaRequest;
import tse.java.soappdi.GetEmpresaResponse;

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

    private static final Logger LOGGER = Logger.getLogger(EmpresasService.class.getName());

    @EJB
    IEmpresasDAO empresasDAO;

    @EJB
    IVehiculosService vehiculosService;

    @EJB
    IVehiculosDAO vehiculosDAO;

    @Override
    public ArrayList<EmpresaDTO> obtenerEmpresas() {
        return empresasDAO.obtenerEmpresas();
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
        EmpresaDTO e = obtenerEmpresa(idEmpresa);
        List<VehiculoDTO> vehiculos = e.getVehiculos();
        vehiculos.add(vehiculo);
        e.setVehiculos(vehiculos);
        empresasDAO.modificarEmpresa(e);
    }

    @Override
    public boolean empresaContieneVehiculo(EmpresaDTO e, VehiculoDTO v) {
        List<VehiculoDTO> vehiculos = e.getVehiculos();
        for (VehiculoDTO vehiculo : vehiculos)
            if (vehiculo.getId() == v.getId())
                return true;
        return false;
    }

    public VehiculoDTO encontrarVehiculo(EmpresaDTO e, Long idVehiculo) {
        List<VehiculoDTO> vehiculos = e.getVehiculos();
        for (VehiculoDTO vehiculo : vehiculos)
            if (vehiculo.getId() == idVehiculo)
                return vehiculo;
        return null;
    }

    @Override
    public void borrarVehiculo(Long id) {
        Vehiculo vehiculo = vehiculosDAO.obtenerVehiculoId(id);
        for (EmpresaDTO e : empresasDAO.obtenerEmpresas()) {
            VehiculoDTO v = encontrarVehiculo(e, id);
            if (empresaContieneVehiculo(e, v)) {
                List<VehiculoDTO> vehiculos = e.getVehiculos();
                vehiculos.remove(v);
                e.setVehiculos(vehiculos);
                empresasDAO.modificarEmpresa(e);
            }
        }
    }


    public List<VehiculoDTO> listarVehiculos(int id) {
        EmpresaDTO empresa = obtenerEmpresa(id);
        List<VehiculoDTO> vehiculos = empresa.getVehiculos();
        return vehiculos;
    }

    @Override
    public void agregarAsignacionAEmpresa(int idEmpresa, AsignacionDTO a) {
        EmpresaDTO empresa = obtenerEmpresa(idEmpresa);
        List<AsignacionDTO> asignaciones = empresa.getAsignaciones();
        asignaciones.add(a);
        empresa.setAsignaciones(asignaciones);
        empresasDAO.modificarEmpresa(empresa);
    }

    @Override
    public void borrarGuia(int numeroViaje) {
        List<EmpresaDTO> empresas = empresasDAO.obtenerEmpresas();
        for (EmpresaDTO e : empresas) {
            List<AsignacionDTO> asignaciones = e.getAsignaciones();
            asignaciones.removeAll(listaAsignacionesConGuia(e, numeroViaje));
            e.setAsignaciones(asignaciones);
            empresasDAO.modificarEmpresa(e);
        }
    }

    // Auxiliar
    private List<AsignacionDTO> listaAsignacionesConGuia(EmpresaDTO e, int numeroGuia) {
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for (AsignacionDTO a : e.getAsignaciones()) {
            if (a.getGuia().getNumero() == numeroGuia)
                result.add(a);
        }
        return result;
    }

    private int crearEmpresaPdi(String rut){
        // 0 - no existe la empresa, 1 - Creada ok, 2 - Error al comunicarse con la plataforma
        try{
            EmpresaServicePortService empresaService = new EmpresaServicePortService();
            EmpresaServicePort empresaPort = empresaService.getEmpresaServicePortSoap11();
            GetEmpresaRequest empresaRequest = new GetEmpresaRequest();
            empresaRequest.setRut(rut);
            GetEmpresaResponse empresaResponse = empresaPort.getEmpresa(empresaRequest);
            tse.java.soappdi.Empresa empresa = empresaResponse.getEmpresa();
            if(empresa == null){
                return 0;
            } else {
                empresasDAO.guardarEmpresa(empresa.getNombrePublico(), empresa.getRazonSocial(), empresa.getNroEmpresa(), empresa.getDirPrincipal());
                return 1;
            }
        } catch (Exception e){
            LOGGER.log(Level.SEVERE, "Hubo un error al comunicarse con la plataforma", e);
            return 2;
        }
    }

}