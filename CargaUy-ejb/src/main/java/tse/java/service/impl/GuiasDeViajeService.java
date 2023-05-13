package tse.java.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.service.IGuiaDeViajesService;

@Stateless
public class GuiasDeViajeService implements IGuiaDeViajesService{

    @EJB
    IGuiaDeViajeDAO gdao;

    @Override
    public void crearGuiaDeViaje(GuiaDeViajeDTO g) {
        gdao.altaGuiaDeViaje(g);
    }

    @Override
    public void borrarGuiaDeViaje(Long id) {
        gdao.borrarGuiaDeViaje(id);
    }

    @Override
    public void modificarGuiaDeViaje(GuiaDeViajeDTO g) {
        gdao.modificarGuiaDeViaje(g);
    }

    @Override
    public List<GuiaDeViajeDTO> listarGuiasDeViajes() {
        return gdao.listarGuiasDeViaje();
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaDeViaje(Long id) {
        return gdao.buscarGuiaDeViaje(id);
    }

    @Override
    public List<GuiaDeViajeDTO> listarGuiasDeViajesPorFecha(List<GuiaDeViajeDTO> guiasViaje, Date fecha) {
        LocalDate fechabusq = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        for(GuiaDeViajeDTO g:guiasViaje){
            LocalDate fechaguia = g.getFecha().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if(fechaguia.isEqual(fechabusq))
                result.add(g);
        }
        return result; 
    }

    
}
