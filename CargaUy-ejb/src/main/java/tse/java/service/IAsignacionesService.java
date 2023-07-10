package tse.java.service;

import tse.java.dto.AsignacionDTO;

import javax.ejb.Local;

@Local
public interface IAsignacionesService {

    int ultimaAsignacionViaje(int numeroViaje);
}
