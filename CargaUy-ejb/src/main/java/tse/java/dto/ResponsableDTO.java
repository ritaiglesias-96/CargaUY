package tse.java.dto;

import tse.java.entity.GuiaDeViaje;
import tse.java.enumerated.RolCiudadano;

import java.util.ArrayList;
import java.util.List;

public class ResponsableDTO extends CiudadanoDTO{

    private List<GuiaDeViajeDTO> guiasDeViaje = new ArrayList<GuiaDeViajeDTO>();

    public ResponsableDTO() {
    }

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }


    public List<GuiaDeViajeDTO> getGuiasDeViaje() {
        return guiasDeViaje;
    }

    public void setGuiasDeViaje(List<GuiaDeViajeDTO> guiasDeViaje) {
        this.guiasDeViaje = guiasDeViaje;
    }

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, ArrayList<GuiaDeViajeDTO> guiasDeViaje) {
        super(idCiudadano, email, cedula, rol);
        this.guiasDeViaje = guiasDeViaje;
    }
}
