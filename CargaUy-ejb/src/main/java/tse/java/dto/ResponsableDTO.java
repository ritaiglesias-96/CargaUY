package tse.java.dto;

import tse.java.enumerated.RolCiudadano;

public class ResponsableDTO extends CiudadanoDTO {

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, String nombre, String apellido) {
        super(idCiudadano, email, cedula, rol, nombre, apellido);
    }

}
