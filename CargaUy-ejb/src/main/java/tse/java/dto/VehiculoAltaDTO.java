package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;
import java.time.LocalDate;

public class VehiculoAltaDTO {
    private int idEmpresa ;
    private Float peso;
    private Float capacidadCarga;
    private LocalDate fechaFinITV;
    private LocalDate fechaInicioPNC;
    private LocalDate fechaFinPNC;
    private String matricula;
    private String pais;
    private String marca;
    private String modelo;
    private int pnc;


    public VehiculoAltaDTO() {
    }

    public VehiculoAltaDTO(int idEmpresa, String matricula, String pais, String marca, String modelo, Float peso,
                           Float capacidadCarga, int pnc, Date fechaFinITV, Date fechaInicioPNC, Date fechaFinPNC) {
        this.idEmpresa = idEmpresa;
        this.matricula = matricula;
        this.pais = pais;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.capacidadCarga = capacidadCarga;
        this.pnc = pnc;
        this.fechaFinITV = fechaFinITV.toLocalDate();
        this.fechaInicioPNC = fechaInicioPNC.toLocalDate();
        this.fechaFinPNC = fechaFinPNC.toLocalDate();
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
    public double getCapacidadCarga() { return capacidadCarga; }
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

}
