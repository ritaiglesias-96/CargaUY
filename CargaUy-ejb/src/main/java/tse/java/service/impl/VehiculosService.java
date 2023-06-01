package tse.java.service.impl;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;
import tse.java.model.Empresas;
import tse.java.model.Vehiculos;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IVehiculosDAO;
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
        VehiculoDTO v = vehiculosDAO.obtenerVehiculoId(id);
        return guiasDeViajeService.listarGuiasDeViajesPorFecha(v.getGuiasDeViaje(), fecha);
    }

    @Override
    public boolean viajeContieneGuia(VehiculoDTO v, GuiaDeViajeDTO g) {
        List<GuiaDeViajeDTO> guias = v.getGuiasDeViaje();
        for(GuiaDeViajeDTO g1:guias)
            if(g1.getNumero() == g.getNumero())
                return true;
        return false;
    }

    public VehiculoDTO obtenerVehiculoPorId(Long id) {
        return vehiculosDAO.obtenerVehiculoId(id);
    }
    @Override
    public void asignarGuia(Long vehiculo_id, GuiaDeViajeDTO g) {
        VehiculoDTO v = vehiculosDAO.obtenerVehiculoId(vehiculo_id);
        List<GuiaDeViajeDTO> guias = v.getGuiasDeViaje();
        guias.add(g);
        v.setGuiasDeViaje(guias);
        vehiculosDAO.modificarVehiculo(v);
    }

    @Override
    public void borrarGuia(int numero_guia) {
        GuiaDeViajeDTO g = guiaDeViajeDAO.buscarGuiaViajePorNumero(numero_guia);
        for(VehiculoDTO v:vehiculosDAO.obtenerVehiculos()){
            if(viajeContieneGuia(v,g)){
                List<GuiaDeViajeDTO> guias = v.getGuiasDeViaje();
                GuiaDeViajeDTO gaux = buscarGuiaenVehiculos(v,g);
                guias.remove(gaux);
                v.setGuiasDeViaje(guias);
                vehiculosDAO.modificarVehiculo(v);
            }
        }
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaenVehiculos(VehiculoDTO v, GuiaDeViajeDTO g) {
        for(GuiaDeViajeDTO gaux:v.getGuiasDeViaje())
            if(gaux.getNumero()==g.getNumero())
                return gaux;
        return null;
    }


}
