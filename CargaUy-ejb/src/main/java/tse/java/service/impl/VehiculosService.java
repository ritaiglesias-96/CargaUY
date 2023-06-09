package tse.java.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tse.java.dto.*;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.model.Empresas;
import tse.java.model.Vehiculos;
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
    IGuiaDeViajeDAO guiaDeViajeDAO;

    @EJB
    IAsignacionesService asignacionService;

    @Override
    public Vehiculos obtenerVehiculos() {
        Vehiculos vehiculos = new Vehiculos();
        vehiculos.setListaVehiculos(vehiculosDAO.obtenerVehiculos());
        return vehiculos;
    }

    public void agregarVehiculo(VehiculoDTO nuevoVehiculo){
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
        VehiculoDTO v = vehiculo.darDto();
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
        return v.darDto();
    }
    @Override
    public void asignarGuia(Long vehiculo_id, AsignacionDTO a) {
        Vehiculo vehiculo = vehiculosDAO.obtenerVehiculoId(vehiculo_id);
        VehiculoDTO v = vehiculo.darDto();
        List<AsignacionDTO> asignaciones = v.getAsignaciones();
        asignaciones.add(a);
        v.setAsignaciones(asignaciones);
        vehiculosDAO.modificarVehiculo(v);
    }

    @Override
    public void borrarGuia(int numero_guia) {
        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(numero_guia);
        for(VehiculoDTO v:vehiculosDAO.obtenerVehiculos()){
            if(viajeContieneGuia(v,g)){
                List<AsignacionDTO> asignaciones = v.getAsignaciones();
                AsignacionDTO a = buscarGuiaenVehiculos(v,g);
                asignaciones.remove(a);
                v.setAsignaciones(asignaciones);
                vehiculosDAO.modificarVehiculo(v);
            }
        }
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
