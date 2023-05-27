package tse.java.dto;

import java.util.Date;
import java.util.List;

public class GuiaDeViajeAltaDTO extends GuiaDeViajeDTO{

    private String pais_vehiculo, matricula_vehiculo, cedula_chofer, cedula_responsable;

    public GuiaDeViajeAltaDTO(Long id, String rubroCliente, float volumenCarga, Date fecha, String origen, Date inicio, Date fin, String destino, List<PesajeDTO> pesajes, String pais_vehiculo, String matricula_vehiculo, String cedula_chofer, String cedula_responsable) {
        super(id, rubroCliente, volumenCarga, fecha, origen, inicio, fin, destino, pesajes);
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
}
