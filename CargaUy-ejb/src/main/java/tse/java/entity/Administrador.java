package tse.java.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = -5754916204104481261L;

    public Administrador(String nombre, String apellido, Calendar fechaNacimiento, String correo, String username, String password) {
        super(nombre, apellido,fechaNacimiento,correo,username, password);
    }

    public Administrador() {
        super();
    }
}
