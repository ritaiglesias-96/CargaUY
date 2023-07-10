package tse.java.dto;

import tse.java.entity.Ciudadano;

import java.util.ArrayList;
import java.util.List;

public class ChoferFrontDTO  extends CiudadanoFrontDTO{

    private List<Integer> idEmpresas = new ArrayList<Integer>();
    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();

    public ChoferFrontDTO() {
        super();
    }

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public List<Integer> getIdEmpresas() {
        return idEmpresas;
    }

    public void setIdEmpresas(List<Integer> idEmpresas) {
        this.idEmpresas = idEmpresas;
    }
}
