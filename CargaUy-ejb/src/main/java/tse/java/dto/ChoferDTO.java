package tse.java.dto;

import tse.java.enumerated.RolCiudadano;

public class ChoferDTO extends CiudadanoDTO{

    public ChoferDTO() {
    }

    public ChoferDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }
}
