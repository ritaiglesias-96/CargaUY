package tse.java.entity;

import tse.java.dto.VehiculoDTO;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="\"Vehiculo\"")
@NamedQuery(name="Vehiculo.findAll", query="SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private Float peso;
    private Float capacidadCarga;
    @Temporal(TemporalType.DATE)
    private Date fechaFinITV;
    @Temporal(TemporalType.DATE)
    private Date fechaInicioPNC;
    @Temporal(TemporalType.DATE)
    private Date fechaFinPNC;

    public Vehiculo(String matricula, String marca, String modelo, Float peso, Float capacidadCarga, Date fechaFinITV, Date fechaInicioPNC, Date fechaFinPNC) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.peso = peso;
        this.capacidadCarga = capacidadCarga;
        this.fechaFinITV = fechaFinITV;
        this.fechaInicioPNC = fechaInicioPNC;
        this.fechaFinPNC = fechaFinPNC;
    }

    public Vehiculo(VehiculoDTO vehiculo) {
        this.matricula = vehiculo.getMatricula();
        this.marca = vehiculo.getMarca();
        this.modelo = vehiculo.getModelo();
        this.peso = vehiculo.getPeso();
        this.capacidadCarga = vehiculo.getCapacidadCarga();
        this.fechaFinITV = vehiculo.getFechaFinITV();
        this.fechaInicioPNC = vehiculo.getFechaInicioPNC();
        this.fechaFinPNC = vehiculo.getFechaFinPNC();
    }

    public Vehiculo() {

    }
    public Long getId() {
        return id;
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