package tse.java.dto;

import java.util.Date;
import java.util.List;

public class GuiaDeViajeAltaDTO{

    private String rubroCliente, tipoCarga;
    private float volumenCarga;
    private String origen, destino, pais_vehiculo, matricula_vehiculo, cedula_chofer, cedula_responsable;

    public GuiaDeViajeAltaDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String pais_vehiculo, String matricula_vehiculo, String cedula_chofer, String cedula_responsable) {
        this.rubroCliente = rubroCliente;
        this.tipoCarga = tipoCarga;
        this.volumenCarga = volumenCarga;
        this.origen = origen;
        this.destino = destino;
        this.pais_vehiculo = pais_vehiculo;
        this.matricula_vehiculo = matricula_vehiculo;
        this.cedula_chofer = cedula_chofer;
        this.cedula_responsable = cedula_responsable;
    }

    public GuiaDeViajeAltaDTO(){}

    public String getPais_vehiculo() {
        return pais_vehiculo;
    }

    public void setPais_vehiculo(String pais_vehiculo) {
        this.pais_vehiculo = pais_vehiculo;
    }

    public String getMatricula_vehiculo() {
        return matricula_vehiculo;
    }

    public void setMatricula_vehiculo(String matricula_vehiculo) {
        this.matricula_vehiculo = matricula_vehiculo;
    }

    public String getCedula_chofer() {
        return cedula_chofer;
    }

    public void setCedula_chofer(String cedula_chofer) {
        this.cedula_chofer = cedula_chofer;
    }

    public String getCedula_responsable() {
        return cedula_responsable;
    }

    public void setCedula_responsable(String cedula_responsable) {
        this.cedula_responsable = cedula_responsable;
    }

    public String getRubroCliente() {
        return rubroCliente;
    }

    public void setRubroCliente(String rubroCliente) {
        this.rubroCliente = rubroCliente;
    }

    public float getVolumenCarga() {
        return volumenCarga;
    }

    public void setVolumenCarga(float volumenCarga) {
        this.volumenCarga = volumenCarga;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getTipoCarga() {
        return tipoCarga;
    }

    public void setTipoCarga(String tipoCarga) {
        this.tipoCarga = tipoCarga;
    }
}
