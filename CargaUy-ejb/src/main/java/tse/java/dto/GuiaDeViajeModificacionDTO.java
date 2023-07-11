package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuiaDeViajeModificacionDTO extends GuiaDeViajeAltaDTO{

    private int numero;

    public GuiaDeViajeModificacionDTO(){}

    public GuiaDeViajeModificacionDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String paisVehiculo, String matriculaVehiculo, String cedulaChofer, int numeroEmpresa, int numero) {
        super(rubroCliente, tipoCarga, volumenCarga, origen, destino, paisVehiculo, matriculaVehiculo, cedulaChofer, numeroEmpresa);
        this.numero = numero;
    }

    @JsonProperty("numero")
    public int getNumero() { return numero; }
    @JsonProperty("numero")
    public void setNumero(int value) { this.numero = value; }
}
