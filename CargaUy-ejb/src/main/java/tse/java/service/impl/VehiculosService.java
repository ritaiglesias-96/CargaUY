package tse.java.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tse.java.dto.*;
import tse.java.entity.Vehiculo;
import tse.java.exception.VehiuloException;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;

@Stateless
public class VehiculosService implements IVehiculosService{

    @EJB
    IVehiculosDAO vehiculosDAO;

    @EJB
    IGuiaDeViajesService guiasDeViajeService;

    @EJB
    IAsignacionesService asignacionService;

    @Override
    public List<VehiculoDTO> obtenerVehiculos() {
        return vehiculosDAO.obtenerVehiculos();
    }

    public void agregarVehiculo(VehiculoDTO nuevoVehiculo){
        vehiculosDAO.agregarVehiculo(nuevoVehiculo);
    }

    public void modificarVehiculo(VehiculoDTO vehiculoModificado){
        vehiculosDAO.modificarVehiculo(vehiculoModificado);
    }

    public void eliminarVehiculo(int id){
        vehiculosDAO.eliminarVehiculo(id);
    }
    @Override
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais) {
        return vehiculosDAO.obtenerVehiculoMatriculaPais(matricula, pais);
    }

    @Override
    public List<PesajeDTO> listarGuiasDeVehiculo(int id, LocalDate fecha) {
        String msg = "Me pasaron por rest los parametros: idvehiculo=" + id + ", fechaViajes=" + fecha;
        Logger.getLogger(VehiculosService.class.getName()).log(Level.INFO, msg);
        Vehiculo vehiculo = vehiculosDAO.obtenerVehiculoId(id);
        VehiculoDTO v = new VehiculoDTO(vehiculo);
        List<AsignacionDTO> asignaciones = v.getAsignaciones();
        List<GuiaDeViajeDTO> guias = new ArrayList<GuiaDeViajeDTO>();
        for(AsignacionDTO a:asignaciones)
            guias.add(a.getGuia());
        return guiasDeViajeService.listarGuiasDeViajesPorFecha(guias, fecha);
    }

    @Override
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g) {
        List<AsignacionDTO> asignaciones = v.getAsignaciones();
        for(AsignacionDTO a:asignaciones){
            if(a.getGuia().getNumero() == g.getNumero()){
                int id = asignacionService.ultimaAsignacionViaje(g.getNumero());
                if(a.getId()==id){
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }

    public VehiculoDTO obtenerVehiculoPorId(int id) throws VehiuloException {
        Vehiculo v = vehiculosDAO.obtenerVehiculoId(id);
        if (v == null)
            throw new VehiuloException("El vehiculo no existe");
        return new VehiculoDTO(v);
    }
    @Override
    public VehiculoDTO buscarVehiculoPorGuia(int numero) {
        for(VehiculoDTO v:vehiculosDAO.obtenerVehiculos()){
            for(AsignacionDTO a:v.getAsignaciones()){
                if(a.getId() == asignacionService.ultimaAsignacionViaje(numero))
                    return v;
            }
        }
        return null;
    }

}
