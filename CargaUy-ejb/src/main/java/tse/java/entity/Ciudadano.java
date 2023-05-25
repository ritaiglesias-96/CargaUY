package tse.java.entity;

import tse.java.dto.CiudadanoDTO;
import tse.java.enumerated.RolCiudadano;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name="\"Ciudadano\"")
@DiscriminatorColumn(name = "rol")
public abstract class Ciudadano  implements Serializable {

    private static final long serialVersionUID = 3827070902901902553L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int idCiudadano;
    private String email;
    @Column(unique = true)
    private String cedula;


    public Ciudadano() {
        super();
    }

    public Ciudadano(String email, String cedula) {
        super();
        this.email = email;
        this.cedula = cedula;
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

}
