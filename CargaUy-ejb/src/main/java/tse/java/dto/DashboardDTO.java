package tse.java.dto;

public class DashboardDTO {

    private String nombreTipoCarga;

    private int cantidad;

    public DashboardDTO(){}

    public DashboardDTO(String nombreTipoCarga, int cantidad) {
        this.nombreTipoCarga = nombreTipoCarga;
        this.cantidad = cantidad;
    }

    public String getNombreTipoCarga() {
        return nombreTipoCarga;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombreTipoCarga(String nombreTipoCarga) {
        this.nombreTipoCarga = nombreTipoCarga;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
