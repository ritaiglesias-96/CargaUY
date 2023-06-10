package tse.java.dto;

import tse.java.entity.GuiaDeViaje;
import tse.java.enumerated.RolCiudadano;

import java.util.ArrayList;
import java.util.List;

public class ResponsableDTO extends CiudadanoDTO{

    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();

    public ResponsableDTO() {
    }

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol) {
        super(idCiudadano, email, cedula, rol);
    }

    public ResponsableDTO(int idCiudadano, String email, String cedula, RolCiudadano rol, List<AsignacionDTO> asignaciones) {
        super(idCiudadano, email, cedula, rol);
        this.asignaciones = asignaciones;
    }

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }
}
