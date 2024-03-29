package tse.java.dto;

import java.util.ArrayList;
import java.util.List;

import tse.java.entity.Asignacion;
import tse.java.entity.Chofer;
import tse.java.entity.Empresa;
import tse.java.entity.Vehiculo;

public class EmpresaDTO {
    private Integer id;
    private String nombrePublico;
    private String razonSocial;
    private int nroEmpresa;
    private String dirPrincipal;
    private List<VehiculoDTO> vehiculos = new ArrayList<VehiculoDTO>();

    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();

    private List<ChoferDTO> choferes = new ArrayList<>();

    public EmpresaDTO() {
    }

    public EmpresaDTO(Empresa e) {
        this.id = e.getId();
        this.nombrePublico = e.getNombrePublico();
        this.razonSocial = e.getRazonSocial();
        this.nroEmpresa = e.getNroEmpresa();
        this.dirPrincipal = e.getDirPrincipal();
        if (!e.getVehiculos().isEmpty()) {
            this.vehiculos = procesarLista(e.getVehiculos());
        }
        if (!e.getChoferes().isEmpty()) {
            this.choferes = procesarChoferes(e.getChoferes());
        }
        if (!e.getAsignaciones().isEmpty()) {
            this.asignaciones = procesarListaAsignaciones(e.getAsignaciones());
        }
    }

    public EmpresaDTO(Integer id, String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal,
                      List<VehiculoDTO> vehiculos) {
        this.id = id;
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
        this.vehiculos = vehiculos;
    }

// Getters y Setters

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePublico() {
        return nombrePublico;
    }

    public void setNombrePublico(String nombrePublico) {
        this.nombrePublico = nombrePublico;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getNroEmpresa() {
        return nroEmpresa;
    }

    public void setNroEmpresa(int nroEmpresa) {
        this.nroEmpresa = nroEmpresa;
    }

    public String getDirPrincipal() {
        return dirPrincipal;
    }

    public void setDirPrincipal(String dirPrincipal) {
        this.dirPrincipal = dirPrincipal;
    }

    public List<VehiculoDTO> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<VehiculoDTO> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }


    public List<ChoferDTO> getChoferes() {
        return choferes;
    }

    public void setChoferes(List<ChoferDTO> choferes) {
        this.choferes = choferes;
    }

    public List<VehiculoDTO> procesarLista(List<Vehiculo> vehiculos) {
        List<VehiculoDTO> result = new ArrayList<VehiculoDTO>();
        for (Vehiculo v : vehiculos) {
            result.add(new VehiculoDTO(v));
        }
        return result;
    }

    public List<ChoferDTO> procesarChoferes(List<Chofer> choferes) {
        List<ChoferDTO> result = new ArrayList<>();
        for (Chofer c:choferes){
            result.add(c.darDTO());
        }
        return result;
    }

    public List<AsignacionDTO> procesarListaAsignaciones(List<Asignacion> asignaciones) {
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for (Asignacion a : asignaciones) {
            result.add(a.darDTO());
        }
        return result;
    }

    public boolean contieneVehiculo(VehiculoDTO v) {
        for (VehiculoDTO v1 : vehiculos) {
            if (v1.getId() == v.getId()) {
                return true;
            }
        }
        return false;
    }

}
