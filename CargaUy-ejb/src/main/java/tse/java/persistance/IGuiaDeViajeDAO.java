package tse.java.persistance;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tse.java.dto.GuiaDeViajeDTO;

@Local
public interface IGuiaDeViajeDAO {
    public void altaGuiaDeViaje(GuiaDeViajeDTO dtg);
    public GuiaDeViajeDTO buscarGuiaDeViaje(Long id);
    public List<GuiaDeViajeDTO> listarGuiasDeViaje();
    public List<GuiaDeViajeDTO> listarGuiasDeViajePorFecha(Date fecha);
    public void borrarGuiaDeViaje(Long id);
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg);
}
