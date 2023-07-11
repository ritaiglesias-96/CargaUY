package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import tse.java.entity.GuiaDeViaje;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class GuiaDeViajeDTO {
    private Integer id;

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

    public GuiaDeViajeDTO(Integer id, int numero, String rubroCliente, String tipoCarga, float volumenCarga, LocalDate fecha, String origen, LocalDate inicio, LocalDate fin, String destino, List<PesajeDTO> pesajes) {
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

    public GuiaDeViajeDTO(GuiaDeViaje guia) {
        this.id = guia.getId();
        this.numero = guia.getNumero();
        this.rubroCliente = guia.getRubroCliente();
        this.tipoCarga = guia.getTipoCarga();
        this.volumenCarga = guia.getVolumenCarga();
        this.fecha = guia.getFecha().toLocalDate();
        this.origen = guia.getOrigen();
        this.destino = guia.getDestino();
        this.pesajes = guia.procesarLista();
        if(guia.getInicio() != null)
            this.inicio = guia.getInicio().toLocalDate();
        if(guia.getFin() != null)
            this.fin = guia.getFin().toLocalDate();
    }

    public GuiaDeViajeDTO(GuiaDeViajeAltaDTO guiaDeViajeAltaDTO) {
        this.rubroCliente = guiaDeViajeAltaDTO.getRubroCliente();
        this.tipoCarga = guiaDeViajeAltaDTO.getTipoCarga();
        this.volumenCarga = guiaDeViajeAltaDTO.getVolumenCarga();
        this.origen = guiaDeViajeAltaDTO.getOrigen();
        this.destino = guiaDeViajeAltaDTO.getDestino();
    }

    public GuiaDeViajeDTO(){}

    @JsonProperty("destino")
    public String getDestino() { return destino; }
    @JsonProperty("destino")
    public void setDestino(String value) { this.destino = value; }

    @JsonProperty("fecha")
    public LocalDate getFecha() { return fecha; }
    @JsonProperty("fecha")
    public void setFecha(LocalDate value) { this.fecha = value; }

    @JsonProperty("fin")
    public LocalDate getFin() { return fin; }
    @JsonProperty("fin")
    public void setFin(LocalDate value) { this.fin = value; }
    @JsonProperty("id")
    public Integer getId() { return id; }
    @JsonProperty("id")
    public void setId(Integer value) { this.id = value; }
    @JsonProperty("inicio")
    public LocalDate getInicio() { return inicio; }
    @JsonProperty("inicio")
    public void setInicio(LocalDate value) { this.inicio = value; }
    @JsonProperty("numero")
    public int getNumero() { return numero; }
    @JsonProperty("numero")
    public void setNumero(int value) { this.numero = value; }
    @JsonProperty("origen")
    public String getOrigen() { return origen; }
    @JsonProperty("origen")
    public void setOrigen(String value) { this.origen = value; }
    @JsonProperty("pesajes")
    public List<PesajeDTO> getPesajes() { return pesajes; }
    @JsonProperty("pesajes")
    public void setPesajes(List<PesajeDTO> value) { this.pesajes = value; }
    @JsonProperty("rubroCliente")
    public String getRubroCliente() { return rubroCliente; }
    @JsonProperty("rubroCliente")
    public void setRubroCliente(String value) { this.rubroCliente = value; }
    @JsonProperty("tipoCarga")
    public String getTipoCarga() { return tipoCarga; }
    @JsonProperty("tipoCarga")
    public void setTipoCarga(String value) { this.tipoCarga = value; }

    @JsonProperty("volumenCarga")
    public float getVolumenCarga() { return volumenCarga; }
    @JsonProperty("volumenCarga")
    public void setVolumenCarga(float value) { this.volumenCarga = value; }
}
