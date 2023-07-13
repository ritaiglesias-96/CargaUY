package tse.java.dto;

import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.enumerated.RolCiudadano;

public class CiudadanoDTO {
    private int idCiudadano;

    private String nombre;
    private String apellido;
    private String email;
    private String cedula;
    private RolCiudadano rol;


    public CiudadanoDTO() {
        super();
    }

    public CiudadanoDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, String nombre, String apellido) {
        super();
        this.idCiudadano = idCiudadano;
        this.email = email;
        this.cedula = cedula;
        this.rol = rol;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    public CiudadanoDTO(Ciudadano c){
        this.idCiudadano = c.getIdCiudadano();
        this.email = c.getEmail();
        this.cedula = c.getCedula();
        this.rol = c.getRol();
        this.nombre = c.getNombre();
        this.apellido = c.getApellido();
    }
    public int getIdCiudadano() {
        return idCiudadano;
    }

    public String getEmail() {
        return email;
    }

    public String getCedula() {
        return cedula;
    }

    public void setIdCiudadano(int idCiudadano) {
        this.idCiudadano = idCiudadano;
    }

    public void setEmail(String email) {
        this.email = email;
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
