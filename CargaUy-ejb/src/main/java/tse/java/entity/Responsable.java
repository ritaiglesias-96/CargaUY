package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.ResponsableDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Responsable extends Ciudadano implements Serializable {

    private static final long serialVersionUID = 3827070902901902553L;
    @OneToOne
    @JoinColumn(name = "empresa_id", nullable = true)
    private Empresa empresa;

    public Responsable() {
        super();
    }

    public Responsable(Empresa empresa) {
        this.empresa = empresa;
    }

    public Responsable(String email, Integer cedula) {
        super(email, cedula, RolCiudadano.RESPONSABLE);
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public ResponsableDTO darDto(){
        return new ResponsableDTO(this.getIdCiudadano(),this.getEmail(),this.getCedula(),RolCiudadano.RESPONSABLE);
    }

}
