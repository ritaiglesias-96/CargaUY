package tse.java.entity;


import tse.java.dto.CiudadanoDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="\"Ciudadano\"")
public class Ciudadano  implements Serializable {
    private static final long serialVersionUID = 3827070902901902553L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCiudadano;
    private String nombre;
    private String apellido;
    private String email;
    @Column(unique = true)
    private String cedula;

    @Column (name = "rol")
    private RolCiudadano rol;


    public Ciudadano() {
        super();
    }

    public Ciudadano(String email, String cedula, RolCiudadano rol, String nombre, String apellido) {
        super();
        this.email = email;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        if (rol!=null){
            this.rol=rol;
        }else {
            this.rol = RolCiudadano.CIUDADANO;
        }
    }


    public int getIdCiudadano() {
        return idCiudadano;
    }

    public void setIdCiudadano(int idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public RolCiudadano getRol() {
        return rol;
    }

    public void setRol(RolCiudadano rol) {
        this.rol = rol;
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

}
