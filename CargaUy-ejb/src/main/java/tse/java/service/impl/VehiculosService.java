package tse.java.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tse.java.dto.*;
import tse.java.entity.Vehiculo;
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
    IEmpresasDAO empresasDAO;
    @EJB
    IGuiaDeViajeDAO guiaDeViajeDAO;

    @EJB
    IAsignacionesService asignacionService;

    @Override
    public List<VehiculoDTO> obtenerVehiculos() {
        return (List<VehiculoDTO>) vehiculosDAO.obtenerVehiculos();
    }

    public void agregarVehiculo(Vehiculo nuevoVehiculo){
        vehiculosDAO.agregarVehiculo(nuevoVehiculo);
    }

    public void modificarVehiculo(VehiculoDTO vehiculoModificado){
        vehiculosDAO.modificarVehiculo(vehiculoModificado);
    }

    public void eliminarVehiculo(Long id){
        vehiculosDAO.eliminarVehiculo(id);
    }
    @Override
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais) {
        return vehiculosDAO.obtenerVehiculoMatriculaPais(matricula, pais);
    }

    @Override
    public List<PesajeDTO> listarGuiasDeVehiculo(Long id, Date fecha) {
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
                Long id = asignacionService.ultimaAsignacionViaje(g.getNumero());
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

    public VehiculoDTO obtenerVehiculoPorId(Long id) {
        Vehiculo v = vehiculosDAO.obtenerVehiculoId(id);
        return new VehiculoDTO(v);
    }
    @Override
    public void asignarGuia(Long vehiculo_id, AsignacionDTO a) {
        Vehiculo vehiculo = vehiculosDAO.obtenerVehiculoId(vehiculo_id);
        VehiculoDTO v = new VehiculoDTO(vehiculo);
        List<AsignacionDTO> asignaciones = v.getAsignaciones();
        asignaciones.add(a);
        v.setAsignaciones(asignaciones);
        vehiculosDAO.modificarVehiculo(v);
    }

    @Override
    public void borrarGuia(int numero_guia) {
        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(numero_guia);
        for(VehiculoDTO v:vehiculosDAO.obtenerVehiculos()){
            List<AsignacionDTO> asignaciones = v.getAsignaciones();
            asignaciones.removeAll(listaAsignacionesConGuia(v,numero_guia));
            v.setAsignaciones(asignaciones);
            vehiculosDAO.modificarVehiculo(v);
        }
    }

    // Auxiliar
    private List<AsignacionDTO> listaAsignacionesConGuia(VehiculoDTO v, int numeroGuia){
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for(AsignacionDTO a:v.getAsignaciones()){
            if(a.getGuia().getNumero()==numeroGuia)
                result.add(a);
        }
        return result;
    }

    @Override
    public VehiculoDTO buscarVehiculoPorGuia(int numero) {
        for(VehiculoDTO v:vehiculosDAO.obtenerVehiculos()){
            for(AsignacionDTO a:v.getAsignaciones()){
                if(a.getId().intValue()==asignacionService.ultimaAsignacionViaje(numero).intValue())
                    return v;
            }
        }
        return null;
    }

    private AsignacionDTO buscarGuiaenVehiculos(VehiculoDTO v, GuiaDeViajeDTO g) {
        for(AsignacionDTO a:v.getAsignaciones())
            if(a.getGuia().getNumero()==g.getNumero())
                return a;
        return null;
    }

}
