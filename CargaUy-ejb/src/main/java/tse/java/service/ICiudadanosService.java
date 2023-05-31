package tse.java.service;

import tse.java.entity.Ciudadano;
import tse.java.model.Ciudadanos;

import javax.ejb.Local;

@Local
public interface ICiudadanosService {
    Ciudadanos obtenerCiudadanos();
    void agregarCiudadano(Ciudadano ciudadano);
    void modificarCiudadano(Ciudadano ciudadano);
    void eliminarCiudadano(Ciudadano ciudadano);
}
