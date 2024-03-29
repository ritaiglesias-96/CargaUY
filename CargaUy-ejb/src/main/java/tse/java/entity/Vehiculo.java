package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.VehiculoDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "\"Vehiculo\"")
@NamedQuery(name = "Vehiculo.findAll", query = "SELECT v FROM Vehiculo v")
public class Vehiculo implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    private String matricula;
    private String pais;
    private String marca;
    private String modelo;
    private Float peso;
    private Float capacidadCarga;
    private int pnc;
    private Date fechaFinITV;
    private Date fechaInicioPNC;
    private Date fechaFinPNC;
    @ManyToMany(cascade = CascadeType.REFRESH)
    private List<Asignacion> asignaciones = new ArrayList<Asignacion>();
    @ManyToOne
    private Empresa empresa;


    public Vehiculo(int id, String matricula, String pais, String marca, String modelo, Float peso, Float capacidadCarga,
                    Date fechaFinITV, int pnc, Date fechaInicioPNC, Date fechaFinPNC, Empresa empresa, List<Asignacion> asignaciones) {
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
        this.empresa = empresa;
        this.asignaciones = asignaciones;
    }

    public Vehiculo(VehiculoDTO vehiculo) {
        this.matricula = vehiculo.getMatricula();
        this.pais = vehiculo.getPais();
        this.marca = vehiculo.getMarca();
        this.modelo = vehiculo.getModelo();
        this.peso = vehiculo.getPeso();
        this.capacidadCarga = vehiculo.getCapacidadCarga();
        this.fechaFinITV = Date.valueOf(vehiculo.getFechaFinITV());
        this.pnc = vehiculo.getPnc();
        this.fechaInicioPNC = Date.valueOf(vehiculo.getFechaInicioPNC());
        this.fechaFinPNC = Date.valueOf(vehiculo.getFechaFinPNC());
        if (vehiculo.getAsignaciones() != null) {
            this.asignaciones = procesarLista(vehiculo.getAsignaciones());
        }
    }

    public void modificarVehiculo(VehiculoDTO vehiculo) {
        this.matricula = vehiculo.getMatricula();
        this.pais = vehiculo.getPais();
        this.marca = vehiculo.getMarca();
        this.modelo = vehiculo.getModelo();
        this.peso = vehiculo.getPeso();
        this.capacidadCarga = vehiculo.getCapacidadCarga();
        this.pnc = vehiculo.getPnc();
        this.fechaFinITV = Date.valueOf(vehiculo.getFechaFinITV());
        this.fechaInicioPNC = Date.valueOf(vehiculo.getFechaInicioPNC());
        this.fechaFinPNC = Date.valueOf(vehiculo.getFechaFinPNC());
    }

    public Vehiculo() {

    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public int getPnc() {
        return pnc;
    }

    public void setPnc(int pnc) {
        this.pnc = pnc;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresas(Empresa empresa) {
        this.empresa = empresa;
    }

    public List<Asignacion> procesarLista(List<AsignacionDTO> asignaciones) {
        List<Asignacion> result = new ArrayList<Asignacion>();
        for (AsignacionDTO a : asignaciones) {
            Asignacion gnew = new Asignacion(a);
            result.add(gnew);
        }
        return result;
    }
}