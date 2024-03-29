package tse.java.entity;

import tse.java.dto.UsuarioDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name="\"Usuario\"")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM Usuario u")
@DiscriminatorColumn(name="tipo_usuario")
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 3827070902901902553L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int idUsuario;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;

    private String correo;

    /** atributos login **/

    private String username;

    private String password;


    public Usuario(String nombre, String apellido, Date fechaNacimiento, String correo, String username, String password) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.username = username;
        this.password = password;
    }

    public Usuario() {
        super();
    }

    public Usuario(UsuarioDTO usuario) {
        super();
        this.nombre = usuario.getNombre();
        this.apellido = usuario.getApellido();
        this.fechaNacimiento = Date.valueOf(usuario.getFechaNacimiento());
        this.correo = usuario.getCorreo();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(int id) {
        this.idUsuario = id;
    }
}

