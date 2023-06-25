package tse.java.dto;

import java.sql.Date;
import java.time.LocalDate;

public class VehiculoAltaDTO {
    private int idEmpresa, pnc ;
    private String matricula, pais, marca, modelo;
    private Float peso;
    private Float capacidadCarga;
    private LocalDate fechaFinITV;
    private LocalDate fechaInicioPNC;
    private LocalDate fechaFinPNC;


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

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public Float getPeso() {
        return peso;
    }

    public Float getCapacidadCarga() {
        return capacidadCarga;
    }

    public LocalDate getFechaFinITV() {
        return fechaFinITV;
    }

    public LocalDate getFechaInicioPNC() {
        return fechaInicioPNC;
    }

    public LocalDate getFechaFinPNC() {
        return fechaFinPNC;
    }

    public int getPnc() { return pnc; }

}
