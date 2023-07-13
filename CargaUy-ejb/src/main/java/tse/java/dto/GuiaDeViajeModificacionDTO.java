package tse.java.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class GuiaDeViajeModificacionDTO extends GuiaDeViajeAltaDTO{

    private int numero;

    private int id;
    public GuiaDeViajeModificacionDTO(){}

    public GuiaDeViajeModificacionDTO(int id, String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String paisVehiculo, String matriculaVehiculo, String cedulaChofer, int numeroEmpresa, int numero) {
        super(rubroCliente, tipoCarga, volumenCarga, origen, destino, paisVehiculo, matriculaVehiculo, cedulaChofer, numeroEmpresa);
        this.numero = numero;
        this.id = id;
    }

    @JsonProperty("numero")
    public int getNumero() { return numero; }
    @JsonProperty("numero")
    public void setNumero(int value) { this.numero = value; }


    @JsonProperty("id")
    public int getId() { return id; }
    @JsonProperty("id")
    public void setId(int id) { this.id = id; }
}
