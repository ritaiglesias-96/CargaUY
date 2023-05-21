package tse.java.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tse.java.dto.EmpresaDTO;
@Entity
@Table(name="\"Empresa\"")
@NamedQuery(name="Empresa.findAll", query="SELECT e FROM Empresa e")
public class Empresa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String nombrePublico;
    private String razonSocial;
    private int nroEmpresa;
    private String dirPrincipal;

    @OneToMany
    private List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
    /*private ArrayList<Choferes> choferes
TODO    */
    public Empresa(){

    }

    public Empresa(String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal) {
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
    }

    public Empresa(Integer id,String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal) {
        this.id = id;
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
    }

    public Empresa(Integer id, String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal,
            ArrayList<Vehiculo> vehiculos) {
        this.id = id;
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
        this.vehiculos = vehiculos;
    }

    public Empresa(EmpresaDTO e){
        this.id = e.getId();
        this.nombrePublico = e.getNombrePublico();
        this.razonSocial = e.getRazonSocial();
        this.nroEmpresa = e.getNroEmpresa();
        this.dirPrincipal = e.getDirPrincipal() ;
    }

    // Getters y Setters
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombrePublico() {
        return nombrePublico;
    }

    public void setNombrePublico(String nombrePublico) {
        this.nombrePublico = nombrePublico;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getNroEmpresa() {
        return nroEmpresa;
    }

    public void setNroEmpresa(int nroEmpresa) {
        this.nroEmpresa = nroEmpresa;
    }

    public String getDirPrincipal() {
        return dirPrincipal;
    }

    public void setDirPrincipal(String dirPrincipal) {
        this.dirPrincipal = dirPrincipal;
    }

    public List<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(List<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }


}
