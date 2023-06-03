package tse.java.service.impl;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
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
    public void modificarVehiculo(VehiculoDTO vehiculoModificado) {
        vehiculosDAO.modificarVehiculo(vehiculoModificado);
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

    @Override
    public VehiculoDTO buscarVehiculoPorGuia(int numero_guia) {
        for(VehiculoDTO v:vehiculosDAO.obtenerVehiculos()){
            for(GuiaDeViajeDTO g:v.getGuiasDeViaje()) {
                if(g.getNumero()==numero_guia) {
                    return v;
                }
            }
        }
        return null;
    }


}
