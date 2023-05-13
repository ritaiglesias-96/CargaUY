package tse.java.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tse.java.dto.GuiaDeViajeDTO;

@Local
public interface IGuiaDeViajesService {

    public void crearGuiaDeViaje(GuiaDeViajeDTO g);
    public void borrarGuiaDeViaje(Long id);
    public void modificarGuiaDeViaje(GuiaDeViajeDTO g);
    public List<GuiaDeViajeDTO> listarGuiasDeViajes();
    public List<GuiaDeViajeDTO> listarGuiasDeViajesPorFecha(List<GuiaDeViajeDTO> guiasViaje, Date fecha);
    public GuiaDeViajeDTO buscarGuiaDeViaje(Long id);

    
}
