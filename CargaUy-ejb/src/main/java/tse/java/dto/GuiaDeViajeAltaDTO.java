package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuiaDeViajeAltaDTO{

    private String rubroCliente;
    private String tipoCarga;
    private float volumenCarga;
    private String origen;
    private String destino;
    private String paisVehiculo;
    private String matriculaVehiculo;
    private String cedulaChofer;
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

    @JsonProperty("pais")
    public String getPaisVehiculo() {
        return paisVehiculo;
    }
    @JsonProperty("pais")
    public void setPaisVehiculo(String paisVehiculo) {
        this.paisVehiculo = paisVehiculo;
    }
    @JsonProperty("matricula")
    public String getMatriculaVehiculo() {
        return matriculaVehiculo;
    }
    @JsonProperty("matricula")
    public void setMatriculaVehiculo(String matriculaVehiculo) {
        this.matriculaVehiculo = matriculaVehiculo;
    }
    @JsonProperty("cedulaChofer")
    public String getCedulaChofer() {
        return cedulaChofer;
    }

    @JsonProperty("cedulaChofer")
    public void setCedulaChofer(String cedulaChofer) {
        this.cedulaChofer = cedulaChofer;
    }
    @JsonProperty("idEmpresa")
    public int getIdEmpresa() {
        return idEmpresa;
    }

    @JsonProperty("idEmpresa")
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
