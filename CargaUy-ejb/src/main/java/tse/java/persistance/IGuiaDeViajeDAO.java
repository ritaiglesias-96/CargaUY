package tse.java.persistance;


import java.util.List;

import javax.ejb.Local;

import tse.java.dto.ChoferDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.GuiaDeViaje;

@Local
public interface IGuiaDeViajeDAO {
    public void altaGuiaDeViaje(GuiaDeViajeDTO dtg, ChoferDTO c, EmpresaDTO e, VehiculoDTO v);
    public GuiaDeViaje buscarGuiaDeViaje(int id);
    public List<GuiaDeViajeDTO> listarGuiasDeViaje();
    public void borrarGuiaDeViaje(int id, int idEmpresa);
    public void modificarGuiaDeViaje(GuiaDeViajeDTO dtg, ChoferDTO c, EmpresaDTO e, VehiculoDTO v);
    void modificarGuiaDeViajeSinAsignacion(GuiaDeViajeDTO guia);
    public int getNextNumeroViaje();
    public int cantidadViajesPorAnioRubro(int anio, String rubro);
    public GuiaDeViajeDTO buscarGuiaViajePorNumero(int numeroGuia);
    public GuiaDeViajeDTO buscarGuiaViajePorId(int idGuia);

}
