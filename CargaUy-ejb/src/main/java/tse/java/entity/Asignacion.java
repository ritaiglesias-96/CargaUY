package tse.java.entity;

import tse.java.dto.AsignacionDTO;
import tse.java.dto.GuiaDeViajeDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"Asignacion\"")
public class Asignacion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne
    private GuiaDeViaje guia;

    private LocalDateTime fechaCambio;

    public Asignacion(int id, GuiaDeViaje guia, LocalDateTime fechaCambio) {
        this.id = id;
        this.guia = guia;
        this.fechaCambio = fechaCambio;
    }

    public Asignacion(GuiaDeViaje guia, LocalDateTime fechaCambio) {
        this.guia = guia;
        this.fechaCambio = fechaCambio;
    }
    public Asignacion(){}

    public Asignacion(AsignacionDTO a){
        this.id = a.getId();
        this.guia = convertirGuia(a.getGuia());
        this.fechaCambio = a.getFechaCambio();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        return new AsignacionDTO(id, new GuiaDeViajeDTO(guia), fechaCambio);
    }

    public GuiaDeViaje convertirGuia(GuiaDeViajeDTO g){
        if(g==null)
            return null;
        else
            return new GuiaDeViaje(g);
    }
}
