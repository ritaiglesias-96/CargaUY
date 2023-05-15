package tse.java.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String msg = "Me pasaron por rest " + guiasViaje.size() + " guias de viaje y fecha=" + fecha;
        Logger.getLogger(GuiasDeViajeService.class.getName()).log(Level.INFO, msg);
        List<GuiaDeViajeDTO> result = new ArrayList<GuiaDeViajeDTO>();
        for(GuiaDeViajeDTO g:guiasViaje){
            msg = "Busco la guiaid=" + g.getId();
            Logger.getLogger(GuiasDeViajeService.class.getName()).log(Level.INFO, msg);
            Date fechaguia = g.getFecha();
            if(fecha.getYear()==fechaguia.getYear() && fecha.getMonth()==fechaguia.getMonth() && fecha.getDay()==fechaguia.getDay()) {
                msg = "Son iguales...Fecha guia=" + g.getFecha() + ", Fecha busqueda=" + fecha;
                Logger.getLogger(GuiasDeViajeService.class.getName()).log(Level.INFO, msg);
                result.add(g);
            }
        }
        return result; 
    }

    
}
