package tse.java.dto;

public class GuiaDeViajeAltaDTO{

    private String rubroCliente, tipoCarga;
    private float volumenCarga;
    private String origen, destino, paisVehiculo, matriculaVehiculo;
    private int numeroEmpresa;
    private String cedulaChofer;

    public GuiaDeViajeAltaDTO(String rubroCliente, String tipoCarga, float volumenCarga, String origen, String destino, String paisVehiculo, String matriculaVehiculo, String cedulaChofer, int numeroEmpresa) {
        this.rubroCliente = rubroCliente;
        this.tipoCarga = tipoCarga;
        this.volumenCarga = volumenCarga;
        this.origen = origen;
        this.destino = destino;
        this.paisVehiculo = paisVehiculo;
        this.matriculaVehiculo = matriculaVehiculo;
        this.cedulaChofer = cedulaChofer;
        this.numeroEmpresa = numeroEmpresa;
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

    public int getNumeroEmpresa() {
        return numeroEmpresa;
    }

    public void setNumeroEmpresa(int numeroEmpresa) {
        this.numeroEmpresa = numeroEmpresa;
    }
}
