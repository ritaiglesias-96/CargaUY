package tse.java.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@DiscriminatorValue("funcionario")
public class Funcionario extends Usuario implements Serializable {

    private static final long serialVersionUID = -5754916204104481261L;

    public Funcionario(String nombre, String apellido, Calendar fechaNacimiento, String correo, String username, String password) {
        super(nombre, apellido,fechaNacimiento,correo,username, password);
    }

    public Funcionario() {
        super();
    }
}