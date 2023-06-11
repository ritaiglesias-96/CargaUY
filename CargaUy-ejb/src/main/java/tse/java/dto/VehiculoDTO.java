package tse.java.dto;

import tse.java.entity.Asignacion;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Vehiculo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VehiculoDTO {
    private Long id;
    private String matricula;
    private String pais;
    private String marca;
    private String modelo;
    private Float peso;
    private Float capacidadCarga;
    private Date fechaFinITV;
    private int pnc;
    private Date fechaInicioPNC;
    private Date fechaFinPNC;
    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();

    

    public VehiculoDTO(Long id, String matricula, String pais, String marca, String modelo, Float peso, Float capacidadCarga,
            Date fechaFinITV, int pnc, Date fechaInicioPNC, Date fechaFinPNC, List<AsignacionDTO> asignaciones) {
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
        this.fechaFinITV = v.getFechaFinITV();
        this.pnc = v.getPnc();
        this.fechaInicioPNC = v.getFechaInicioPNC();
        this.fechaFinPNC = v.getFechaFinPNC();
        this.asignaciones = procesarLista(v.getAsignaciones());
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }
    
    public int getPnc() { return pnc; }

    public void setPnc(int pnc) { this.pnc = pnc; }

    public List<AsignacionDTO> procesarLista(List<Asignacion> asignaciones){
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for(Asignacion a:asignaciones){
            result.add(a.darDTO());
        }
        return result;
    }

}
