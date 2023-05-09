package tse.java.entity;

import tse.java.dto.RubroDTO;

import javax.persistence.*;

@Entity
public class Rubro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
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
