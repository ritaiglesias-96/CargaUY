package tse.java.dto;

import tse.java.enumerated.TipoUsuario;
import tse.java.entity.Usuario;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class UsuarioDTO {
    private int idUsuario;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String correo;
    private String username;
    private String password;
    private TipoUsuario tipo;

    public UsuarioDTO(String nombre, String apellido, LocalDate fechaNacimiento, String correo, String username, String password, TipoUsuario tipo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
        this.correo = correo;
        this.username = username;
        this.password = password;
        this.tipo = tipo;
    }

    public UsuarioDTO() {
    }

    public UsuarioDTO(Usuario usr, TipoUsuario tipo) {
        this.idUsuario = usr.getIdUsuario();
        this.nombre = usr.getNombre();
        this.apellido = usr.getApellido();
        this.fechaNacimiento = usr.getFechaNacimiento().toLocalDate();
        this.correo = usr.getCorreo();
        this.username = usr.getUsername();
        this.password = usr.getPassword();
        this.tipo = tipo;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
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

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }
}
