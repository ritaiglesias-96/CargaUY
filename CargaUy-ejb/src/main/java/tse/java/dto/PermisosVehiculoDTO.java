package tse.java.dto;

import java.sql.Date;

public class PermisosVehiculoDTO {
    private Integer idVehiculo;
    private Integer idEmpresa;
    private String matricula;
    private String pais;
    private Date fechaFinITV;
    private int pnc;
    private Date fechaInicioPNC;
    private Date fechaFinPNC;

    public PermisosVehiculoDTO() {
    }

    public PermisosVehiculoDTO(int idVehiculo, Integer idEmpresa, String matricula, String pais, Date fechaFinITV, int pnc, Date fechaInicioPNC, Date fechaFinPNC) {
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
        this.idEmpresa = v.getEmpresaId();
        this.matricula = v.getMatricula();
        this.pais = v.getPais();
        this.fechaFinITV = Date.valueOf(v.getFechaFinITV());
        this.pnc = v.getPnc();
        this.fechaInicioPNC = Date.valueOf(v.getFechaInicioPNC());
        this.fechaFinPNC = Date.valueOf(v.getFechaFinPNC());
    }

    public int getIdVehiculo() {
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
