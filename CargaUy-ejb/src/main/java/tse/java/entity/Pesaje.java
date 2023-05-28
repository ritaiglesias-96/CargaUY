package tse.java.entity;

import tse.java.dto.PesajeDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "\"Pesaje\"")
public class Pesaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double latitud, longuitud;
    private LocalDateTime fecha;
    private float carga;

    public Pesaje(Long id, double latitud, double longuitud, LocalDateTime fecha, float carga) {
        this.id = id;
        this.latitud = latitud;
        this.longuitud = longuitud;
        this.fecha = fecha;
        this.carga = carga;
    }

    public Pesaje(){}

    public Pesaje(PesajeDTO pesaje){
        this.id = pesaje.getId();
        this.latitud = pesaje.getLatitud();
        this.longuitud = pesaje.getLonguitud();
        this.carga = pesaje.getCarga();
        this.fecha = pesaje.getFecha();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLonguitud() {
        return longuitud;
    }

    public void setLonguitud(double longuitud) {
        this.longuitud = longuitud;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public float getCarga() {
        return carga;
    }

    public void setCarga(float carga) {
        this.carga = carga;
    }

    public PesajeDTO darDTO(){
        return new PesajeDTO(id,latitud, longuitud, fecha, carga);
    }
}
