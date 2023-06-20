package tse.java.dto;

public class GuiaDeViajeModificacionDTO extends GuiaDeViajeAltaDTO{

    private int numeroViaje;

    public GuiaDeViajeModificacionDTO(){}

    public GuiaDeViajeModificacionDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String paisVehiculo, String matriculaVehiculo, String cedulaChofer, int numeroEmpresa, int numeroViaje) {
        super(rubroCliente, tipoCarga, volumenCarga, origen, destino, paisVehiculo, matriculaVehiculo, cedulaChofer, numeroEmpresa);
        this.numeroViaje = numeroViaje;
    }

    public int getNumeroViaje() {
        return numeroViaje;
    }

    public void setNumeroViaje(int numeroViaje) {
        this.numeroViaje = numeroViaje;
    }
}
