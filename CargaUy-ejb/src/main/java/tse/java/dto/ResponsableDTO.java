package tse.java.dto;

import tse.java.enumerated.RolCiudadano;

public class ResponsableDTO extends CiudadanoDTO {

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }

}
