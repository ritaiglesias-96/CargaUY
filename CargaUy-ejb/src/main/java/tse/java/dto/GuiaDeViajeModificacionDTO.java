package tse.java.dto;

public class GuiaDeViajeModificacionDTO extends GuiaDeViajeAltaDTO{

    private int numero_viaje;

    public GuiaDeViajeModificacionDTO(){}

    public GuiaDeViajeModificacionDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String pais_vehiculo, String matricula_vehiculo, Integer cedula_chofer, String cedula_responsable, int numero_viaje) {
        super(rubroCliente, tipoCarga, volumenCarga, origen, destino, pais_vehiculo, matricula_vehiculo, cedula_chofer, cedula_responsable);
        this.numero_viaje = numero_viaje;
    }

    public int getNumero_viaje() {
        return numero_viaje;
    }

    public void setNumero_viaje(int numero_viaje) {
        this.numero_viaje = numero_viaje;
    }
}
