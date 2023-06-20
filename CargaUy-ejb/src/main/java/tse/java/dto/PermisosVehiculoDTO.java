package tse.java.dto;

import java.util.Date;

public class PermisosVehiculoDTO {
    private Long idVehiculo;
    private Integer idEmpresa;
    private String matricula;
    private String pais;
    private Date fechaFinITV;
    private int pnc;
    private Date fechaInicioPNC;
    private Date fechaFinPNC;

    public PermisosVehiculoDTO() {
    }

    public PermisosVehiculoDTO(Long idVehiculo, Integer idEmpresa, String matricula, String pais, Date fechaFinITV, int pnc, Date fechaInicioPNC, Date fechaFinPNC) {
        this.idVehiculo = idVehiculo;
        this.idEmpresa = idEmpresa;
        this.matricula = matricula;
        this.pais = pais;
        this.fechaFinITV = fechaFinITV;
        this.pnc = pnc;
        this.fechaInicioPNC = fechaInicioPNC;
        this.fechaFinPNC = fechaFinPNC;
    }

    public PermisosVehiculoDTO(VehiculoDTO v) {
        this.idVehiculo = v.getId();
        this.idEmpresa = v.getIdEmpresa();
        this.matricula = v.getMatricula();
        this.pais = v.getPais();
        this.fechaFinITV = v.getFechaFinITV();
        this.pnc = v.getPnc();
        this.fechaInicioPNC = v.getFechaInicioPNC();
        this.fechaFinPNC = v.getFechaFinPNC();
    }

    public Long getIdVehiculo() {
        return idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPais() {
        return pais;
    }

    public Date getFechaFinITV() {
        return fechaFinITV;
    }

    public int getPnc() {
        return pnc;
    }

    public Date getFechaInicioPNC() {
        return fechaInicioPNC;
    }

    public Date getFechaFinPNC() {
        return fechaFinPNC;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }
}
