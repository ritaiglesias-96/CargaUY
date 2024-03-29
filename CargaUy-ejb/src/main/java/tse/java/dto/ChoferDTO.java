package tse.java.dto;

import tse.java.enumerated.RolCiudadano;

import java.util.ArrayList;
import java.util.List;

public class ChoferDTO extends CiudadanoDTO{

    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();


    public ChoferDTO(){
        super();
    };
    public ChoferDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, String nombre, String apellido) {
        super(idCiudadano, email, cedula, rol, nombre, apellido);
    }

    public ChoferDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, String nombre, String apellido, List<AsignacionDTO> asignaciones) {
        super(idCiudadano, email, cedula, rol, nombre, apellido);
        this.asignaciones = asignaciones;
    }

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }
}
