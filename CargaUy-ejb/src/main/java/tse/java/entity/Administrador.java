package tse.java.entity;


import tse.java.dto.UsuarioDTO;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;
import java.sql.Date;

@Entity
@DiscriminatorValue("administrador")
public class Administrador extends Usuario implements Serializable {

    private static final long serialVersionUID = -5754916204104481261L;

    public Administrador(String nombre, String apellido, Date fechaNacimiento, String correo, String username, String password) {
        super(nombre, apellido,fechaNacimiento,correo,username, password);
    }

    public Administrador(UsuarioDTO usuario) {
        super(usuario);
    }

    public Administrador() {
        super();
    }
}
