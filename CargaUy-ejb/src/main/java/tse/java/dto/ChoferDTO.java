package tse.java.dto;

import tse.java.enumerated.RolCiudadano;

import java.util.ArrayList;
import java.util.List;

public class ChoferDTO extends CiudadanoDTO{

    private List<GuiaDeViajeDTO> guiasDeViaje = new ArrayList<GuiaDeViajeDTO>();

    public ChoferDTO() {
    }

    public ChoferDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }

    public List<GuiaDeViajeDTO> getGuiasDeViaje() {
        return guiasDeViaje;
    }

    public void setGuiasDeViaje(List<GuiaDeViajeDTO> guiasDeViaje) {
        this.guiasDeViaje = guiasDeViaje;
    }

    public ChoferDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, List<GuiaDeViajeDTO> guiasDeViaje) {
        super(idCiudadano, email, cedula, rol);
        this.guiasDeViaje = guiasDeViaje;
    }
}
