package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuiaDeViajeModificacionDTO extends GuiaDeViajeAltaDTO{

    private int numeroViaje;

    public GuiaDeViajeModificacionDTO(){}

    public GuiaDeViajeModificacionDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String paisVehiculo, String matriculaVehiculo, String cedulaChofer, int numeroEmpresa, int numeroViaje) {
        super(rubroCliente, tipoCarga, volumenCarga, origen, destino, paisVehiculo, matriculaVehiculo, cedulaChofer, numeroEmpresa);
        this.numeroViaje = numeroViaje;
    }

    @JsonProperty("numero")
    public int getNumeroViaje() { return numeroViaje; }
    @JsonProperty("numero")
    public void setNumeroViaje(int value) { this.numeroViaje = value; }
}
