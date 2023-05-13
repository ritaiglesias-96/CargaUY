package tse.java.service.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IVehiculosService;

public class VehiculosService implements IVehiculosService{

    @EJB
    IVehiculosDAO vehiculosDAO;

    @EJB
    IGuiaDeViajesService guiasDeViajeService;

    @Override
    public VehiculoDTO obtenerVehiculoMatriculaPais(String matricula, String pais) {
        return vehiculosDAO.obtenerVehiculoMatriculaPais(matricula, pais);
    }

    @Override
    public List<GuiaDeViajeDTO> listarGuiasDeVehiculo(Long id, Date fecha) {
        VehiculoDTO v = vehiculosDAO.obtenerVehiculoId(id);
        return guiasDeViajeService.listarGuiasDeViajesPorFecha(v.getGuiasDeViaje(), fecha);
    }


}
