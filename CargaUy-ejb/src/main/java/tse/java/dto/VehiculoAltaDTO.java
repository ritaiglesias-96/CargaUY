package tse.java.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehiculoAltaDTO {
    private int idEmpresa ;
    private String matricula, pais, marca, modelo, cedula_responsable;
    private Float peso;
    private Float capacidadCarga;
    private Date fechaFinITV;
    private Date fechaInicioPNC;
    private Date fechaFinPNC;

    public VehiculoAltaDTO() {
    }

    public VehiculoAltaDTO(int idEmpresa, String matricula, String pais, String marca, String modelo, String cedula_responsable,
                           Float peso, Float capacidadCarga, Date fechaFinITV, Date fechaInicioPNC, Date fechaFinPNC) {
        this.idEmpresa = idEmpresa;
        this.matricula = matricula;
        this.pais = pais;
        this.marca = marca;
        this.modelo = modelo;
        this.cedula_responsable = cedula_responsable;
        this.peso = peso;
        this.capacidadCarga = capacidadCarga;
        this.fechaFinITV = fechaFinITV;
        this.fechaInicioPNC = fechaInicioPNC;
        this.fechaFinPNC = fechaFinPNC;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCedula_responsable() {
        return cedula_responsable;
    }

    public void setCedula_responsable(String cedula_responsable) {
        this.cedula_responsable = cedula_responsable;
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