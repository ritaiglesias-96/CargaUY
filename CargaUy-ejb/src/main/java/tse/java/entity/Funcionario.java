package tse.java.entity;

import tse.java.enumerated.RolCiudadano;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.List;

@Entity
@DiscriminatorValue("Funcionario")
public class Funcionario extends Ciudadano implements Serializable{

    private static final long serialVersionUID = 3827070902901902553L;

    public Funcionario() {
        super();
    }

    public Funcionario(String email, String cedula) {
        super(email, cedula);
    }

}