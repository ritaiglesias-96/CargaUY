package tse.java.entity;

import tse.java.dto.FuncionarioDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name="\"Funcionario\"")
public class Funcionario extends Ciudadano implements Serializable{

    private static final long serialVersionUID = 3827070902901902553L;

    public Funcionario() {
        super();
    }

    public Funcionario(String email, String cedula) {
        super(email, cedula, RolCiudadano.FUNCIONARIO);
    }

    public FuncionarioDTO darDTO() {
        return new FuncionarioDTO(this.getIdCiudadano(), this.getEmail(), this.getCedula(), RolCiudadano.FUNCIONARIO);
    }
}
