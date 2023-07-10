package tse.java.service;

import tse.java.dto.AsignacionDTO;

import javax.ejb.Local;

@Local
public interface IAsignacionesService {

    public void borrarGuiaEnAsignacion(int numeroViaje);
    public int ultimaAsignacionViaje(int numeroViaje);
    void agregarAsignacion(AsignacionDTO asignacionDTO);
    AsignacionDTO ultimaIngresada();
}
