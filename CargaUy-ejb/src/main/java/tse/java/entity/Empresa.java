package tse.java.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.EmpresaDTO;
import tse.java.dto.VehiculoDTO;

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

    @OneToOne
    @JoinColumn(name = "responsable_id", nullable = true)
    private Responsable responsable;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Vehiculo> vehiculos = new ArrayList<>();
    /* private ArrayList<Choferes> choferes TODO */

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    private List<Asignacion> asignaciones = new ArrayList<Asignacion>();

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

    public Empresa(Integer id, String nombrePublico, String razonSocial, int nroEmpresa, String dirPrincipal, Responsable responsable, List<Vehiculo> vehiculos, List<Asignacion> asignaciones) {
        this.id = id;
        this.nombrePublico = nombrePublico;
        this.razonSocial = razonSocial;
        this.nroEmpresa = nroEmpresa;
        this.dirPrincipal = dirPrincipal;
        this.responsable = responsable;
        this.vehiculos = vehiculos;
        this.asignaciones = asignaciones;
    }

    public Empresa(EmpresaDTO e){
        this.id = e.getId();
        this.nombrePublico = e.getNombrePublico();
        this.razonSocial = e.getRazonSocial();
        this.nroEmpresa = e.getNroEmpresa();
        this.dirPrincipal = e.getDirPrincipal();

        if(!e.getAsignaciones().isEmpty()) {
            for (AsignacionDTO a : e.getAsignaciones()) {
                this.asignaciones.add(new Asignacion(a));
            }
        }
        if(!e.getVehiculos().isEmpty()) {
            for (VehiculoDTO v : e.getVehiculos()) {
                this.vehiculos.add(new Vehiculo(v));
            }
        }
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
    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public List<Asignacion> getAsignaciones() {
        return asignaciones;
    }

    public void setAsignaciones(List<Asignacion> asignaciones) {
        this.asignaciones = asignaciones;
    }

    public List<Asignacion> procesarListaAsignaciones(List<AsignacionDTO> asignaciones){
        List<Asignacion> result = new ArrayList<Asignacion>();
        for(AsignacionDTO a : asignaciones){
            Asignacion anew = new Asignacion(a);
            result.add(anew);
        }
        return result;
    }
}
