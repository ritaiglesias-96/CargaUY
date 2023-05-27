package tse.java.entity;

import tse.java.enumerated.RolCiudadano;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

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
}
