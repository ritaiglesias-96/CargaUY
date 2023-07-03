package tse.java.persistance;


import java.util.List;

import javax.ejb.Local;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.Asignacion;
import tse.java.entity.GuiaDeViaje;

@Local
public interface IGuiaDeViajeDAO {
    public void altaGuiaDeViaje(GuiaDeViajeDTO dtg);
    public GuiaDeViaje buscarGuiaDeViaje(Long id);
    public List<GuiaDeViajeDTO> listarGuiasDeViaje();
    public void borrarGuiaDeViaje(Long id);
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg);
    public int getNextNumeroViaje();
    public GuiaDeViajeDTO buscarGuiaViajePorNumero(int numero_guia);
    public int cantidadViajesPorAnioRubro(int anio, String rubro);
}
