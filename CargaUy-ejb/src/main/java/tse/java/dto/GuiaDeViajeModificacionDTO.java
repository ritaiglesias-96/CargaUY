package tse.java.dto;

public class GuiaDeViajeModificacionDTO extends GuiaDeViajeAltaDTO{

    private int numeroViaje;

    public GuiaDeViajeModificacionDTO(){}

    public GuiaDeViajeModificacionDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String pais_vehiculo, String matricula_vehiculo, String cedula_chofer, int numero_emp, int numeroViaje) {
        super(rubroCliente, tipoCarga, volumenCarga, origen, destino, pais_vehiculo, matricula_vehiculo, cedula_chofer, numero_emp);
        this.numeroViaje = numeroViaje;
    }

    public int getNumeroViaje() {
        return numeroViaje;
    }

    public void setNumeroViaje(int numeroViaje) {
        this.numeroViaje = numeroViaje;
    }
}
