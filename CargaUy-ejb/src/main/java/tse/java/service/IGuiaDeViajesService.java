package tse.java.service;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import tse.java.dto.*;

@Local
public interface IGuiaDeViajesService {

    public void crearGuiaDeViaje(GuiaDeViajeDTO g, ChoferDTO c, EmpresaDTO e, VehiculoDTO v);
    public void borrarGuiaDeViaje(int id, int idEmpresa);
    public void modificarGuiaDeViaje(GuiaDeViajeDTO g);
    public List<GuiaDeViajeDTO> listarGuiasDeViajes();
    public List<PesajeDTO> listarGuiasDeViajesPorFecha(List<GuiaDeViajeDTO> guiasViaje, Date fecha);
    public void asignarPesajes(int numeroViaje, List<PesajeDTO> pesajes);
    int getNextNumeroViaje();
    GuiaDeViajeDTO buscarGuiaViajePorNumero(int numeroGuia);
    GuiaDeViajeDTO buscarGuiaViajePorId(int numeroGuia);
}
