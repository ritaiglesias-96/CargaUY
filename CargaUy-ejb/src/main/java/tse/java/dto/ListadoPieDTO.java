package tse.java.dto;

public class ListadoPieDTO {

    private String nombreTipoCarga;

    private int cantidad;

    public ListadoPieDTO(){}

    public ListadoPieDTO(String nombreTipoCarga, int cantidad) {
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
