package tse.java.service;

import tse.java.dto.CiudadanoDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.entity.Asignacion;
import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.model.Ciudadanos;

import javax.ejb.Local;

@Local
public interface ICiudadanosService {

    Ciudadano obtenerCiudadano(int id);
    Ciudadano obtenerCiudadanoPorCedula(String ci);
    Ciudadanos obtenerCiudadanos();
    void agregarCiudadano(Ciudadano ciudadano);
    void modificarCiudadano(Ciudadano ciudadano);
    void eliminarCiudadano(int id);
    void agregarHijoCiudadano(Ciudadano ciudadano);
    void modificarHijoCiudadano(Ciudadano ciudadano);
    void eliminarHijoCiudadano(int id);
    void asignarEmpresa(int responsableId, Empresa empresa);
    void eliminarEmpresa(int responsableId, Empresa empresa);
    void asignarEmpresaChofer(int choferId, Empresa empresa);
    void eliminarEmpresaChofer(int choferId, Empresa empresa);

    public void asingarViajeChofer(int chofer_id, Asignacion a);

   // public void asingarViajeResponsable(int responsable_id, Asignacion a);

    public boolean contieneGuiaViajeChofer(String cedula_chofer, int numero_viaje);

  //  public boolean contieneGuiaViajeResponsable(String cedula_responsable, int numero_viaje);
    public void borrarGuia(int numero_guia);
}
