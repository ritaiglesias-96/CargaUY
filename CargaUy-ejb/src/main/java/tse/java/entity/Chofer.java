package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.ChoferDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Chofer")
public class Chofer extends Ciudadano implements Serializable {

    private static final long serialVersionUID = 3827070902901902553L;

    public Chofer() {
        super();
    }

    public Chofer(String email, String cedula) {
        super(email, cedula);
    }

    public List<AsignacionDTO> procesarListaAsignaciones(List<Asignacion> asignaciones){
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for(Asignacion a:asignaciones)
            result.add(a.darDTO());
        return result;
    }
    public ChoferDTO darDTO(){
        return new ChoferDTO(this.getIdCiudadano(),this.getEmail(),this.getCedula(), RolCiudadano.CHOFER, procesarListaAsignaciones(this.getAsignaciones()));
    }
}
