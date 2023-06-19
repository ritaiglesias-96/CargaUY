package tse.java.dto;

import tse.java.entity.GuiaDeViaje;
import tse.java.enumerated.RolCiudadano;

import java.util.ArrayList;
import java.util.List;

public class ResponsableDTO extends CiudadanoDTO{


    public ResponsableDTO() {
    }

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }


}
