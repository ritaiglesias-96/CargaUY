package tse.java.dto;

public class DashboardBarChartDTO extends DashboardPieChartDTO{

    private String nombreCategoria;

    public DashboardBarChartDTO(){}

    public DashboardBarChartDTO(String nombreItem, int cantidad, String nombreCategoria) {
        super(nombreItem, cantidad);
        this.nombreCategoria = nombreCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}
