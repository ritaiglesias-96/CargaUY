package tse.java.dto;

import tse.java.entity.Asignacion;
import tse.java.entity.Vehiculo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDTO {
    private Long id;
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
    private int empresaId;
    private List<AsignacionDTO> asignaciones = new ArrayList<AsignacionDTO>();


    public VehiculoDTO(Long id, String matricula, String pais, String marca, String modelo, Float peso, Float capacidadCarga,
                       LocalDate fechaFinITV, int pnc, LocalDate fechaInicioPNC, LocalDate fechaFinPNC, int empresaId, List<AsignacionDTO> asignaciones) {
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
        this.empresaId = empresaId;
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
            this.empresaId = v.getEmpresa().getId();
        }
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

    public LocalDate getFechaFinITV() {
        return fechaFinITV;
    }

    public void setFechaFinITV(LocalDate fechaFinITV) {
        this.fechaFinITV = fechaFinITV;
    }

    public LocalDate getFechaInicioPNC() {
        return fechaInicioPNC;
    }

    public void setFechaInicioPNC(LocalDate fechaInicioPNC) {
        this.fechaInicioPNC = fechaInicioPNC;
    }

    public LocalDate getFechaFinPNC() {
        return fechaFinPNC;
    }

    public void setFechaFinPNC(LocalDate fechaFinPNC) {
        this.fechaFinPNC = fechaFinPNC;
    }

    public List<AsignacionDTO> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<AsignacionDTO> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public int getPnc() {
        return pnc;
    }

    public void setPnc(int pnc) {
        this.pnc = pnc;
    }

    public int getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(int empresaId) {
        this.empresaId = empresaId;
    }

    public List<AsignacionDTO> procesarLista(List<Asignacion> asignaciones) {
        List<AsignacionDTO> result = new ArrayList<AsignacionDTO>();
        for (Asignacion a : asignaciones) {
            result.add(a.darDTO());
        }
        return result;
    }

}
