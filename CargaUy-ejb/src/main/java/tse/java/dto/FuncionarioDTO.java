package tse.java.dto;

import tse.java.enumerated.RolCiudadano;

public class FuncionarioDTO extends CiudadanoDTO {

    public FuncionarioDTO(int idCiudadano, String email, Integer cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }
}
