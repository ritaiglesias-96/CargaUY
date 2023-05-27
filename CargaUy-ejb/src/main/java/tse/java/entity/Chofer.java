package tse.java.entity;

import tse.java.dto.ChoferDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Chofer")
public class Chofer extends Ciudadano implements Serializable {

    private static final long serialVersionUID = 3827070902901902553L;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GuiaDeViaje> guiasDeViaje = new ArrayList<GuiaDeViaje>();

    public Chofer() {
        super();
    }

    public Chofer(String email, String cedula) {
        super(email, cedula);
    }


    public List<GuiaDeViaje> getGuiasDeViaje() {
        return guiasDeViaje;
    }

    public void setGuiasDeViaje(List<GuiaDeViaje> guiasDeViaje) {
        this.guiasDeViaje = guiasDeViaje;
    }

    public Chofer(String email, String cedula, ArrayList<GuiaDeViaje> guiasDeViaje) {
        super(email, cedula);
        this.guiasDeViaje = guiasDeViaje;
    }

    public ChoferDTO darDTO(){
        return new ChoferDTO(this.getIdCiudadano(),this.getEmail(),this.getCedula(), RolCiudadano.CHOFER);
    }
}
