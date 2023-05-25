package tse.java.entity;

import tse.java.dto.UsuarioDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Date;

@Entity
@DiscriminatorValue("autoridad")
public class Autoridad extends Usuario implements Serializable {

    private static final long serialVersionUID = -5754916204104481261L;

    public Autoridad(String nombre, String apellido, Date fechaNacimiento, String correo, String username, String password) {
        super(nombre, apellido,fechaNacimiento,correo,username, password);
    }

    public Autoridad() {
        super();
    }

    public Autoridad(UsuarioDTO usuario) {
        super(usuario);
    }
}