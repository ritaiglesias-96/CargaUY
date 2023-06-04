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
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Pesaje;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IPesajesDAO;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IPesajesService;

@Stateless
public class GuiasDeViajeService implements IGuiaDeViajesService{

    @EJB
    IGuiaDeViajeDAO guiaviajeDao;

    @EJB
    IPesajesService pesajesService;

    @EJB
    IPesajesDAO pesajesDao;


    @Override
    public void crearGuiaDeViaje(GuiaDeViajeDTO g) {
        guiaviajeDao.altaGuiaDeViaje(g);
    }

    @Override
    public void borrarGuiaDeViaje(Long id) {
        GuiaDeViaje g = guiaviajeDao.buscarGuiaDeViaje(id);
        List<Pesaje> pesajes = g.getPesajes();
        g.setPesajes(new ArrayList<Pesaje>());
        guiaviajeDao.modificarGuiaDeViaje(g.darDto());
        for(Pesaje p:pesajes){
            pesajesDao.borrarPesaje(p.getId());
        }
        guiaviajeDao.borrarGuiaDeViaje(id);
    }

    @Override
    public void modificarGuiaDeViaje(GuiaDeViajeDTO g) {
        guiaviajeDao.modificarGuiaDeViaje(g);
    }

    @Override
    public List<GuiaDeViajeDTO> listarGuiasDeViajes() {
        return guiaviajeDao.listarGuiasDeViaje();
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

    @Override
    public void asignarPesajes(int numero_viaje, List<PesajeDTO> pesajes) {
        GuiaDeViajeDTO g = guiaviajeDao.buscarGuiaViajePorNumero(numero_viaje);
        List<PesajeDTO> result = new ArrayList<PesajeDTO>();
        for(PesajeDTO p:pesajes){
            pesajesDao.altaPesaje(p);
            PesajeDTO paux = pesajesDao.buscarPesaje(pesajesDao.getLastId());
            result.add(paux);
        }
        g.setPesajes(result);
        guiaviajeDao.modificarGuiaDeViaje(g);
    }


}
