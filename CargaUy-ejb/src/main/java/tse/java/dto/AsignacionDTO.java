package tse.java.dto;

import tse.java.entity.GuiaDeViaje;

import javax.persistence.OneToOne;
import java.time.LocalDateTime;

public class AsignacionDTO {

    private Long id;

    private GuiaDeViajeDTO guia;

    private LocalDateTime fechaCambio;

    public AsignacionDTO(Long id, GuiaDeViajeDTO guia, LocalDateTime fechaCambio) {
        this.id = id;
        this.guia = guia;
        this.fechaCambio = fechaCambio;
    }

    public AsignacionDTO(){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GuiaDeViajeDTO getGuia() {
        return guia;
    }

    public void setGuia(GuiaDeViajeDTO guia) {
        this.guia = guia;
    }

    public LocalDateTime getFechaCambio() {
        return fechaCambio;
    }

    public void setFechaCambio(LocalDateTime fechaCambio) {
        this.fechaCambio = fechaCambio;
    }
}
