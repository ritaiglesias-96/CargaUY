package tse.java.dto;

import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;

import java.util.ArrayList;
import java.util.List;

public class ResponsableFrontDTO extends CiudadanoFrontDTO{

    private Integer idEmpresa;
    private String nombrePublico;
    private String razonSocial;
    private int nroEmpresa;
    private String dirPrincipal;
    private List<VehiculoDTO> vehiculos = new ArrayList<VehiculoDTO>();
    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();

    public ResponsableFrontDTO() {
        super();
    }

    public ResponsableFrontDTO(Ciudadano ciudadano, String jwt, String idToken, Integer idEmpresa, String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal, List<VehiculoDTO> vehiculos, List<AsignacionDTO> asignaciones) {
        super(ciudadano, jwt, idToken);
        this.idEmpresa = idEmpresa;
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
        if(!vehiculos.isEmpty()) this.vehiculos = vehiculos;
        if(!asignaciones.isEmpty()) this.asignaciones = asignaciones;
    }

    public void setEmpresa (EmpresaDTO empresa) {
        this.idEmpresa = empresa.getId();
        this.nombrePublico = empresa.getNombrePublico();
        this.razonSocial = empresa.getRazonSocial();
        this.nroEmpresa = empresa.getNroEmpresa();
        this.dirPrincipal = empresa.getDirPrincipal();
        if(!empresa.getVehiculos().isEmpty()) this.vehiculos = empresa.getVehiculos();
        if(!empresa.getAsignaciones().isEmpty()) this.asignaciones = empresa.getAsignaciones();
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
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
}
