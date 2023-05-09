package tse.java.dto;

import tse.java.entity.Vehiculo;

import java.util.Date;

public class VehiculoDTO {
    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private Float peso;
    private Float capacidadCarga;
    private Date fechaFinITV;
    private Date fechaInicioPNC;
    private Date fechaFinPNC;

    public VehiculoDTO(Long id, String matricula, String marca, String modelo, Float peso, Float capacidadCarga, Date fechaFinITV, Date fechaInicioPNC, Date fechaFinPNC) {
        this.id = id;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.capacidadCarga = capacidadCarga;
        this.fechaFinITV = fechaFinITV;
        this.fechaInicioPNC = fechaInicioPNC;
        this.fechaFinPNC = fechaFinPNC;
    }

    public VehiculoDTO(Vehiculo v) {
        this.id = v.getId();
        this.matricula = v.getMatricula();
        this.marca = v.getMarca();
        this.modelo = v.getModelo();
        this.peso = v.getPeso();
        this.capacidadCarga = v.getCapacidadCarga();
        this.fechaFinITV = v.getFechaFinITV();
        this.fechaInicioPNC = v.getFechaInicioPNC();
        this.fechaFinPNC = v.getFechaFinPNC();
    }

    public VehiculoDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public Float getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(Float capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    public Date getFechaFinITV() {
        return fechaFinITV;
    }

    public void setFechaFinITV(Date fechaFinITV) {
        this.fechaFinITV = fechaFinITV;
    }

    public Date getFechaInicioPNC() {
        return fechaInicioPNC;
    }

    public void setFechaInicioPNC(Date fechaInicioPNC) {
        this.fechaInicioPNC = fechaInicioPNC;
    }

    public Date getFechaFinPNC() {
        return fechaFinPNC;
    }

    public void setFechaFinPNC(Date fechaFinPNC) {
        this.fechaFinPNC = fechaFinPNC;
    }
}
