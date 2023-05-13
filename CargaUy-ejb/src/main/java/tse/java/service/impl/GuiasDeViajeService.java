package tse.java.service.impl;

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
    
}
