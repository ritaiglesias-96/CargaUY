package tse.java.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import tse.java.dto.*;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Pesaje;
import tse.java.persistance.IAsignacionDAO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IPesajesDAO;
import tse.java.service.IAsignacionesService;
import tse.java.service.IGuiaDeViajesService;
import tse.java.service.IPesajesService;

@Stateless
public class GuiasDeViajeService implements IGuiaDeViajesService{

    @EJB
    IGuiaDeViajeDAO guiaviajeDao;

    @EJB
    IPesajesService pesajesService;

    @EJB
    IPesajesDAO pesajesDAO;

    @EJB
    IAsignacionesService asignacionesService;

    @EJB
    IAsignacionDAO asignacionDAO;

    @Override
    public void crearGuiaDeViaje(GuiaDeViajeDTO g, ChoferDTO c, EmpresaDTO e, VehiculoDTO v) {
        guiaviajeDao.altaGuiaDeViaje(g, c, e, v);
    }

    @Override
    public void borrarGuiaDeViaje(int id, int idEmpresa) {
        guiaviajeDao.borrarGuiaDeViaje(id, idEmpresa);
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
        AsignacionDTO a = asignacionDAO.buscarAsignacion(asignacionesService.ultimaAsignacionViaje(numero_viaje));
        GuiaDeViajeDTO g = a.getGuia();
        List<PesajeDTO> result = new ArrayList<PesajeDTO>();
        for(PesajeDTO p:pesajes){
            pesajesDAO.altaPesaje(p);
            PesajeDTO paux = pesajesDAO.buscarPesaje(pesajesDAO.getLastId());
            result.add(paux);
        }
        g.setPesajes(result);
        guiaviajeDao.modificarGuiaDeViaje(g);
    }

    @Override
    public int getNextNumeroViaje() {
        return guiaviajeDao.getNextNumeroViaje();
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaViajePorNumero(int numeroGuia) {
        return guiaviajeDao.buscarGuiaViajePorNumero(numeroGuia);
    }

    @Override
    public GuiaDeViajeDTO buscarGuiaViajePorId(int numeroGuia) {
        System.out.println("llega service");
        return guiaviajeDao.buscarGuiaViajePorId(numeroGuia);
    }
}
