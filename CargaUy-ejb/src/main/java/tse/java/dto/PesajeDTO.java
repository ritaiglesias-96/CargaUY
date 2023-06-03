package tse.java.dto;


import java.time.LocalDateTime;

public class PesajeDTO {

    private Long id;
    private double latitud, longuitud;
    private LocalDateTime fecha;
    private float carga;

    public PesajeDTO(Long id, double latitud, double longuitud, LocalDateTime fecha, float carga) {
        this.id = id;
        this.latitud = latitud;
        this.longuitud = longuitud;
        this.fecha = fecha;
        this.carga = carga;
    }

    public PesajeDTO(){}

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
}
