package tse.java.entity;

import tse.java.dto.ResponsableDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("Responsable")
public class Responsable extends Ciudadano implements Serializable {

    private static final long serialVersionUID = 3827070902901902553L;
    @OneToOne
    @JoinColumn(name = "empresa_id", nullable = true)
    private Empresa empresa;

    @OneToMany(cascade = CascadeType.ALL)
    private List<GuiaDeViaje> guiasDeViaje = new ArrayList<GuiaDeViaje>();

    public Responsable() {
        super();
    }

    public Responsable(Empresa empresa) {
        this.empresa = empresa;
    }

    public Responsable(String email, String cedula) {
        super(email, cedula);
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<GuiaDeViaje> getGuiasDeViaje() {
        return guiasDeViaje;
    }

    public void setGuiasDeViaje(List<GuiaDeViaje> guiasDeViaje) {
        this.guiasDeViaje = guiasDeViaje;
    }

    public Responsable(String email, String cedula, ArrayList<GuiaDeViaje> guiasDeViaje) {
        super(email, cedula);
        this.guiasDeViaje = guiasDeViaje;
    }

    public ResponsableDTO darDTO(){
        return new ResponsableDTO(this.getIdCiudadano(),this.getEmail(),this.getCedula(),RolCiudadano.RESPONSABLE);
    }
}
