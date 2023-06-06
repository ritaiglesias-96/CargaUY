package tse.java.entity;

import tse.java.dto.AsignacionDTO;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "\"Asignacion\"")
public class Asignacion {

    @Id
    private Long id;

    @OneToOne
    private GuiaDeViaje guia;

    private LocalDateTime fechaCambio;

    public Asignacion(Long id, GuiaDeViaje guia, LocalDateTime fechaCambio) {
        this.id = id;
        this.guia = guia;
        this.fechaCambio = fechaCambio;
    }

    public Asignacion(){}

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
}
