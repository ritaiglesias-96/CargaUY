package tse.java.dto;

import java.time.LocalDate;
public class PermisosVehiculoDTO {
    private Integer idVehiculo;
    private Integer idEmpresa;
    private String matricula;
    private String pais;
    private LocalDate fechaFinITV;
    private int pnc;
    private LocalDate fechaInicioPNC;
    private LocalDate fechaFinPNC;

    public PermisosVehiculoDTO() {
    }

    public PermisosVehiculoDTO(int idVehiculo, Integer idEmpresa, String matricula, String pais, LocalDate fechaFinITV, int pnc, LocalDate fechaInicioPNC, LocalDate fechaFinPNC) {
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

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getPais() {
        return pais;
    }

    public LocalDate getFechaFinITV() {
        return fechaFinITV;
    }

    public int getPnc() {
        return pnc;
    }

    public LocalDate getFechaInicioPNC() {
        return fechaInicioPNC;
    }

    public LocalDate getFechaFinPNC() {
        return fechaFinPNC;
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }
}
