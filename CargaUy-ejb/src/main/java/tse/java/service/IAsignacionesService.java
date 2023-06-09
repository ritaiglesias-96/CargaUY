package tse.java.service;

import javax.ejb.Local;

@Local
public interface IAsignacionesService {

    public void borrarGuiaEnAsignacion(int numero_viaje);
    public Long ultimaAsignacionViaje(int numero_viaje);
}
