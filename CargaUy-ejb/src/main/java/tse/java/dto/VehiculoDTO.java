package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import tse.java.entity.Asignacion;
import tse.java.entity.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDTO {
    private Integer id;
    private String matricula;
    private String pais;
    private String marca;
    private String modelo;
    private Float peso;
    private Float capacidadCarga;
    private LocalDate fechaFinITV;
    private int pnc;
    private LocalDate fechaInicioPNC;
    private LocalDate fechaFinPNC;
    private int idEmpresa;
    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();


    public VehiculoDTO(int id, String matricula, String pais, String marca, String modelo, Float peso, Float capacidadCarga,
                       LocalDate fechaFinITV, int pnc, LocalDate fechaInicioPNC, LocalDate fechaFinPNC, int idEmpresa, List<AsignacionDTO> asignaciones) {
        this.id = id;
        this.matricula = matricula;
        this.pais = pais;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.capacidadCarga = capacidadCarga;
        this.fechaFinITV = fechaFinITV;
        this.pnc = pnc;
        this.fechaInicioPNC = fechaInicioPNC;
        this.fechaFinPNC = fechaFinPNC;
        this.idEmpresa = idEmpresa;
        this.asignaciones = asignaciones;
    }

    public VehiculoDTO(Vehiculo v) {
        this.id = v.getId();
        this.matricula = v.getMatricula();
        this.pais = v.getPais();
        this.marca = v.getMarca();
        this.modelo = v.getModelo();
        this.peso = v.getPeso();
        this.capacidadCarga = v.getCapacidadCarga();
        this.fechaFinITV = v.getFechaFinITV().toLocalDate();
        this.pnc = v.getPnc();
        this.fechaInicioPNC = v.getFechaInicioPNC().toLocalDate();
        this.fechaFinPNC = v.getFechaFinPNC().toLocalDate();
        this.asignaciones = procesarLista(v.getAsignaciones());
        if (v.getEmpresa() != null) {
            this.idEmpresa = v.getEmpresa().getId();
        }
    }

    public VehiculoDTO(VehiculoAltaDTO v) {
        this.matricula = v.getMatricula();
        this.pais = v.getPais();
        this.marca = v.getMarca();
        this.modelo = v.getModelo();
        this.peso = v.getPeso();
        this.capacidadCarga = (float) v.getCapacidadCarga();
        this.pnc = v.getPnc();
        this.idEmpresa = v.getIdEmpresa();
        this.fechaFinITV = v.getFechaFinITV();
        this.fechaInicioPNC = v.getFechaInicioPNC();
        this.fechaFinPNC = v.getFechaFinPNC();
    }

    public VehiculoDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @JsonProperty("idEmpresa")
    public int getIdEmpresa() { return idEmpresa; }
    @JsonProperty("idEmpresa")
    public void setIdEmpresa(int value) { this.idEmpresa = value; }

    @JsonProperty("matricula")
    public String getMatricula() { return matricula; }
    @JsonProperty("matricula")
    public void setMatricula(String value) { this.matricula = value; }

    @JsonProperty("pais")
    public String getPais() { return pais; }
    @JsonProperty("pais")
    public void setPais(String value) { this.pais = value; }

    @JsonProperty("marca")
    public String getMarca() { return marca; }
    @JsonProperty("marca")
    public void setMarca(String value) { this.marca = value; }

    @JsonProperty("modelo")
    public String getModelo() { return modelo; }
    @JsonProperty("modelo")
    public void setModelo(String value) { this.modelo = value; }

    @JsonProperty("peso")
    public float getPeso() { return peso; }
    @JsonProperty("peso")
    public void setPeso(double value) { this.peso = (float) value; }

    @JsonProperty("capacidadCarga")
    public float getCapacidadCarga() { return capacidadCarga; }
    @JsonProperty("capacidadCarga")
    public void setCapacidadCarga(double value) { this.capacidadCarga = (float) value; }

    @JsonProperty("pnc")
    public int getPnc() { return pnc; }
    @JsonProperty("pnc")
    public void setPnc(int value) { this.pnc = value; }

    @JsonProperty("fechaFinITV")
    public LocalDate getFechaFinITV() { return fechaFinITV; }
    @JsonProperty("fechaFinITV")
    public void setFechaFinITV(LocalDate value) { this.fechaFinITV = value; }

    @JsonProperty("fechaInicioPNC")
    public LocalDate getFechaInicioPNC() { return fechaInicioPNC; }
    @JsonProperty("fechaInicioPNC")
    public void setFechaInicioPNC(LocalDate value) { this.fechaInicioPNC = value; }

    @JsonProperty("fechaFinPNC")
    public LocalDate getFechaFinPNC() { return fechaFinPNC; }
    @JsonProperty("fechaFinPNC")
    public void setFechaFinPNC(LocalDate value) { this.fechaFinPNC = value; }

    @JsonProperty("asignaciones")
    public List<AsignacionDTO> getAsignaciones() { return asignaciones; }
    @JsonProperty("asignaciones")
    public void setAsignaciones(List<AsignacionDTO> value) { this.asignaciones = value; }

    public List<AsignacionDTO> procesarLista(List<Asignacion> asignaciones) {
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for (Asignacion a : asignaciones) {
            result.add(a.darDTO());
        }
        return result;
    }

}
