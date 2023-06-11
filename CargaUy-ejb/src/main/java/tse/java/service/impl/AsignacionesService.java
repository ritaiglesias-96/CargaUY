package tse.java.service.impl;

import tse.java.dto.AsignacionDTO;
import tse.java.persistance.IAsignacionDAO;
import tse.java.service.IAsignacionesService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AsignacionesService implements IAsignacionesService {

    @EJB
    IAsignacionDAO ad;

    @Override
    public void borrarGuiaEnAsignacion(int numero_viaje) {
        List<Long> asignaciones_borrar = new ArrayList<Long>();
        for(AsignacionDTO a : ad.listarAsignaciones()){
            if(a.getGuia().getNumero() == numero_viaje){
                a.setGuia(null);
                ad.modificarAsignacion(a);
                asignaciones_borrar.add(a.getId());
            }
        }

        for(Long id:asignaciones_borrar)
            ad.borrarAsignacion(id);
    }

    @Override
    public Long ultimaAsignacionViaje(int numero_viaje) {
        Long ret = Long.MIN_VALUE;
        for(AsignacionDTO a:ad.listarAsignaciones()){
            if(a.getGuia().getNumero()==numero_viaje){
                Long id = a.getId();
                if(id > ret){
                    ret = id;
                }
            }
        }
        return ret;
    }
}
