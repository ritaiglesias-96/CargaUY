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
    IAsignacionDAO asignacionDAO;



    @Override
    public int ultimaAsignacionViaje(int numeroViaje) {
        int ret = 0;
        for(AsignacionDTO a:asignacionDAO.listarAsignaciones()){
            if(a.getGuia().getNumero() == numeroViaje){
                int id = a.getId();
                if(id > ret){
                    ret = id;
                }
            }
        }
        return ret;
    }
}
