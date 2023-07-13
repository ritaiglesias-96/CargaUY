package tse.java.dto;

import tse.java.entity.Ciudadano;
import tse.java.enumerated.RolCiudadano;

public class CiudadanoFrontDTO {
    private String nombre;
    private String apellido;
    private String email;
    private String cedula;
    private RolCiudadano rol;
    private String jwt;
    private String idToken;

    public CiudadanoFrontDTO() {
        super();
    }

    public CiudadanoFrontDTO(Ciudadano ciudadano, String jwt, String idToken) {
        super();
        this.email = ciudadano.getEmail();
        this.cedula = ciudadano.getCedula();
        this.rol = ciudadano.getRol();
        this.jwt = jwt;
        this.idToken = idToken;
        this.nombre = ciudadano.getNombre();
        this.apellido = ciudadano.getApellido();
    }

    public void setCiudadano (Ciudadano ciudadano) {
        this.email = ciudadano.getEmail();
        this.cedula = ciudadano.getCedula();
        this.rol = ciudadano.getRol();
        this.nombre = ciudadano.getNombre();
        this.apellido = ciudadano.getApellido();
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


    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }
    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
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
