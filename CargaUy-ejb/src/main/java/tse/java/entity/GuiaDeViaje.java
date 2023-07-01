package tse.java.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;

@Entity
@Table(name = "\"GuiaDeViaje\"")
public class GuiaDeViaje {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private int numero;
    private String rubroCliente;
    private String tipoCarga;

    private float volumenCarga;

    @Temporal(TemporalType.DATE)
    private Date fecha;

    private String origen;

    @Temporal(TemporalType.DATE)
    private Date inicio;

    @Temporal(TemporalType.DATE)
    private Date fin;

    private String destino;

    @OneToMany(orphanRemoval = true)
    private List<Pesaje> pesajes = new ArrayList<Pesaje>();

    public GuiaDeViaje(Integer id, int numero, String rubroCliente, String tipoCarga, float volumenCarga, Date fecha, String origen, Date inicio, Date fin, String destino, List<Pesaje> pesajes) {
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

    public GuiaDeViaje(){}

    public GuiaDeViaje(GuiaDeViajeDTO guia){
        this.id = guia.getId();
        this.numero = guia.getNumero();
        this.pesajes = procesarListaPesajes(guia.getPesajes());
        this.fin = guia.getFin();
        this.destino = guia.getDestino();
        this.fecha = guia.getFecha();
        this.inicio = guia.getInicio();
        this.origen = guia.getOrigen();
        this.rubroCliente = guia.getRubroCliente();
        this.volumenCarga = guia.getVolumenCarga();
        this.tipoCarga = guia.getTipoCarga();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Pesaje> getPesajes() {
        return pesajes;
    }

    public void setPesajes(List<Pesaje> pesajes) {
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

    public List<PesajeDTO> procesarLista(){
        List<PesajeDTO> result = new ArrayList<PesajeDTO>();
        for(Pesaje p:pesajes){
            result.add(p.darDTO());
        }
        return result;
    }

    public GuiaDeViajeDTO darDto(){
        return new GuiaDeViajeDTO(id, numero, rubroCliente, tipoCarga, volumenCarga, fecha, origen, inicio, fin, destino, procesarLista());
    }

    public List<Pesaje> procesarListaPesajes(List<PesajeDTO> pesajes){
        List<Pesaje> result = new ArrayList<Pesaje>();
        for(PesajeDTO p:pesajes){
            Pesaje pnew = new Pesaje(p);
            result.add(pnew);
        }
        return result;
    }

    
}
