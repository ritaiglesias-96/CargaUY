package tse.java.entity;

import tse.java.enumerated.RolCiudadano;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Entity
@DiscriminatorValue("Responsable")
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

    public Responsable(String email, String cedula) {
        super(email, cedula);
    }


    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
