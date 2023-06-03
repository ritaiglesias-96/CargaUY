package tse.java.service;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.Ciudadano;
import tse.java.model.Ciudadanos;

import javax.ejb.Local;

@Local
public interface ICiudadanosService {
    Ciudadanos obtenerCiudadanos();
    void agregarCiudadano(Ciudadano ciudadano);
    void modificarCiudadano(Ciudadano ciudadano);
    void eliminarCiudadano(Ciudadano ciudadano);
    void agregarHijoCiudadano(Ciudadano ciudadano);
    void modificarHijoCiudadano(Ciudadano ciudadano);
    void eliminarHijoCiudadano(Ciudadano ciudadano);
    public void asingarViajeChofer(int chofer_id, GuiaDeViajeDTO g);
    public void asingarViajeResponsable(int responsable_id, GuiaDeViajeDTO g);
    public boolean contieneGuiaViajeChofer(String cedula_chofer, int numero_viaje);
    public boolean contieneGuiaViajeResponsable(String cedula_responsable, int numero_viaje);
    public void borrarGuia(int numero_guia);
}
