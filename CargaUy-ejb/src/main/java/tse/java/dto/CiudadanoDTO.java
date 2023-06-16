package tse.java.dto;

import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;
import tse.java.enumerated.RolCiudadano;

public class CiudadanoDTO {
    private int idCiudadano;
    private String email;
    private String cedula;
    private RolCiudadano rol;


    public CiudadanoDTO() {
        super();
    }

    public CiudadanoDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super();
        this.idCiudadano = idCiudadano;
        this.email = email;
        this.cedula = cedula;
        this.rol = rol;

    }
    public CiudadanoDTO(Ciudadano c){
        this.idCiudadano = c.getIdCiudadano();
        this.email = c.getEmail();
        this.cedula = c.getCedula();
        this.rol = c.getRol();

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


}
