package tse.java.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IPesajesService;

@Stateless
public class GuiasDeViajeService implements IGuiaDeViajesService{

    @EJB
    IGuiaDeViajeDAO gdao;

    @EJB
    IPesajesService pesajesService;

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
    public List<PesajeDTO> listarGuiasDeViajesPorFecha(List<GuiaDeViajeDTO> guiasViaje, Date fecha) {
        String msg = "Me pasaron por rest " + guiasViaje.size() + " guias de viaje y fecha=" + fecha;
        Logger.getLogger(GuiasDeViajeService.class.getName()).log(Level.INFO, msg);
        for(GuiaDeViajeDTO g:guiasViaje){
            msg = "Busco la guiaid=" + g.getId();
            Logger.getLogger(GuiasDeViajeService.class.getName()).log(Level.INFO, msg);
            Date fechaInicioGuia = g.getInicio();
            if(fecha.after(fechaInicioGuia) && g.getFin()==null) {
                return pesajesService.listarPesajesDeGuia(g, fecha);
            }
        }
        return new ArrayList<PesajeDTO>();
    }

    
}
