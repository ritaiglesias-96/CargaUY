package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.ChoferDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Chofer extends Ciudadano implements Serializable {

    private static final long serialVersionUID = 3827070902901902553L;
    @ManyToMany(cascade =  CascadeType.REFRESH)
    private List<Asignacion> asignaciones = new ArrayList<Asignacion>();

    public Chofer() {
        super();
    }

    public Chofer(String email, String cedula){
        super(email, cedula,RolCiudadano.CHOFER);

    }

    public Chofer(String email, String cedula, List<Asignacion> asignaciones) {
        super(email, cedula, RolCiudadano.CHOFER);
        this.asignaciones = asignaciones;
    }

    public Chofer(ChoferDTO c) {
        super(c.getEmail(), c.getCedula(), c.getRol());
        for(AsignacionDTO a:c.getAsignaciones()){
            this.asignaciones.add(new Asignacion(a));
        }
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
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
