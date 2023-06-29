package tse.java.dto;

public class DashboardPieChartDTO {

    private String nombreItem;

    private int cantidad;

    public DashboardPieChartDTO(){}

    public DashboardPieChartDTO(String nombreItem, int cantidad) {
        this.nombreItem = nombreItem;
        this.cantidad = cantidad;
    }

    public String getNombreItem() {
        return nombreItem;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setNombreItem(String nombreItem) {
        this.nombreItem = nombreItem;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
