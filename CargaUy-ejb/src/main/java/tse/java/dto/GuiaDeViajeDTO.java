package tse.java.dto;

import tse.java.entity.Pesaje;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GuiaDeViajeDTO {
    private Long id;

    private int numero;
    private String rubroCliente;

    private String tipoCarga;

    private float volumenCarga;

    private LocalDate fecha;

    private String origen;

    private LocalDate inicio;

    private LocalDate fin;

    private String destino;

    private List<PesajeDTO> pesajes = new ArrayList<PesajeDTO>();

    public GuiaDeViajeDTO(Long id, int numero, String rubroCliente, String tipoCarga, float volumenCarga, LocalDate fecha, String origen, LocalDate inicio, LocalDate fin, String destino, List<PesajeDTO> pesajes) {
        this.id = id;
        this.numero = numero;
        this.rubroCliente = rubroCliente;
        this.tipoCarga = tipoCarga;
        this.volumenCarga = volumenCarga;
        this.fecha = fecha;
        this.origen = origen;
        this.inicio = inicio;
        this.fin = fin;
        this.destino = destino;
        this.pesajes = pesajes;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public List<PesajeDTO> getPesajes() {
        return pesajes;
    }

    public void setPesajes(List<PesajeDTO> pesajes) {
        this.pesajes = pesajes;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }
}
