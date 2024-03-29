package tse.java.entity;

import tse.java.dto.TipoCargaDTO;

import javax.persistence.*;

@Entity
@Table(name = "\"TipoCarga\"")
public class TipoCarga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;

    public TipoCarga(){}

    public TipoCarga(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

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

    public TipoCargaDTO darDTO(){
        return new TipoCargaDTO(id, nombre);
    }
}
