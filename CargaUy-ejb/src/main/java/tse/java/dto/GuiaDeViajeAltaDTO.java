package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuiaDeViajeAltaDTO{

    private String rubroCliente, tipoCarga;
    private float volumenCarga;
    private String origen, destino, paisVehiculo, matriculaVehiculo, cedulaChofer;

    private Integer idEmpresa;

    public GuiaDeViajeAltaDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String paisVehiculo, String matriculaVehiculo, String cedulaChofer, int idEmpresa) {
        this.rubroCliente = rubroCliente;
        this.tipoCarga = tipoCarga;
        this.volumenCarga = volumenCarga;
        this.origen = origen;
        this.destino = destino;
        this.paisVehiculo = paisVehiculo;
        this.matriculaVehiculo = matriculaVehiculo;
        this.cedulaChofer = cedulaChofer;
        this.idEmpresa = idEmpresa;
    }

    public GuiaDeViajeAltaDTO(){}

    public String getPaisVehiculo() {
        return paisVehiculo;
    }

    public void setPaisVehiculo(String paisVehiculo) {
        this.paisVehiculo = paisVehiculo;
    }

    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }

    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }

    public String getCedulaChofer() {
        return cedulaChofer;
    }

    public void setCedulaChofer(String cedulaChofer) {
        this.cedulaChofer = cedulaChofer;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }


    @JsonProperty("destino")
    public String getDestino() { return destino; }
    @JsonProperty("destino")
    public void setDestino(String value) { this.destino = value; }
    @JsonProperty("origen")
    public String getOrigen() { return origen; }
    @JsonProperty("origen")
    public void setOrigen(String value) { this.origen = value; }

    @JsonProperty("rubroCliente")
    public String getRubroCliente() { return rubroCliente; }
    @JsonProperty("rubroCliente")
    public void setRubroCliente(String value) { this.rubroCliente = value; }

    @JsonProperty("tipoCarga")
    public String getTipoCarga() { return tipoCarga; }
    @JsonProperty("tipoCarga")
    public void setTipoCarga(String value) { this.tipoCarga = value; }

    @JsonProperty("volumenCarga")
    public float getVolumenCarga() { return volumenCarga; }
    @JsonProperty("volumenCarga")
    public void setVolumenCarga(float value) { this.volumenCarga = value; }
}
