package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"Asignacion\"")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private GuiaDeViaje guia;

    private LocalDateTime fechaCambio;

    public Asignacion(Long id, GuiaDeViaje guia, LocalDateTime fechaCambio) {
        this.id = id;
        this.guia = guia;
        this.fechaCambio = fechaCambio;
    }

    public Asignacion(){}

    public Asignacion(AsignacionDTO a){
        this.id = a.getId();
        this.guia = convertirGuia(a.getGuia());
        this.fechaCambio = a.getFechaCambio();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuiaDeViaje getGuia() {
        return guia;
    }

    public void setGuia(GuiaDeViaje guia) {
        this.guia = guia;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }

    public AsignacionDTO darDTO(){
        return new AsignacionDTO(id, guia.darDto(), fechaCambio);
    }

    public GuiaDeViaje convertirGuia(GuiaDeViajeDTO g){
        if(g==null)
            return null;
        else
            return new GuiaDeViaje(g);
    }
}
