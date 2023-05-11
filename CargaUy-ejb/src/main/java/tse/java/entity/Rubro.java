package tse.java.entity;

import tse.java.dto.RubroDTO;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="\"Rubro\"")
@NamedQuery(name="Rubro.findAll", query="SELECT r FROM Rubro r")
public class Rubro implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nombre;

    public Rubro(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Rubro(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public RubroDTO darDTO(){
        return new RubroDTO(id,nombre);
    }
}
