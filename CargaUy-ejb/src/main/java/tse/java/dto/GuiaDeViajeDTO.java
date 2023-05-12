package tse.java.dto;

import java.util.Date;

public class GuiaDeViajeDTO {
    private Long id;

    private String rubroCliente;

    private float volumenCarga;

    private Date fecha;

    private String origen;

    private Date inicio;

    private Date fin;

    private String destino;

    public GuiaDeViajeDTO(Long id, String rubroCliente, float volumenCarga, Date fecha, String origen, Date inicio,
            Date fin, String destino) {
        this.id = id;
        this.rubroCliente = rubroCliente;
        this.volumenCarga = volumenCarga;
        this.fecha = fecha;
        this.origen = origen;
        this.inicio = inicio;
        this.fin = fin;
        this.destino = destino;
    }

    public GuiaDeViajeDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRubroCliente() {
        return rubroCliente;
    }

    public void setRubroCliente(String rubroCliente) {
        this.rubroCliente = rubroCliente;
    }

    public float getVolumenCarga() {
        return volumenCarga;
    }

    public void setVolumenCarga(float volumenCarga) {
        this.volumenCarga = volumenCarga;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    

    
}
