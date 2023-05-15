package tse.java.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import java.util.List;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;

@Entity
@Table(name = "\"GuiaDeViaje\"")
public class GuiaDeViaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rubroCliente;

    private float volumenCarga;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String origen;

    @Temporal(TemporalType.DATE)
    private Date inicio;

    @Temporal(TemporalType.DATE)
    private Date fin;

    private String destino;

    @OneToMany
    private List<Pesaje> pesajes = new ArrayList<Pesaje>();

    public GuiaDeViaje(Long id, String rubroCliente, float volumenCarga, Date fecha, String origen, Date inicio, Date fin,
            String destino, List<Pesaje> pesajes) {
        this.id = id;
        this.rubroCliente = rubroCliente;
        this.volumenCarga = volumenCarga;
        this.fecha = fecha;
        this.origen = origen;
        this.inicio = inicio;
        this.fin = fin;
        this.destino = destino;
        this.pesajes = pesajes;
    }

    public GuiaDeViaje(){}

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

    public List<PesajeDTO> procesarLista(){
        List<PesajeDTO> result = new ArrayList<PesajeDTO>();
        for(Pesaje p:pesajes){
            result.add(p.darDTO());
        }
        return result;
    }

    public GuiaDeViajeDTO darDto(){
        return new GuiaDeViajeDTO(id, rubroCliente, volumenCarga, fecha, origen, inicio, fin, destino, procesarLista());
    }

    
}
