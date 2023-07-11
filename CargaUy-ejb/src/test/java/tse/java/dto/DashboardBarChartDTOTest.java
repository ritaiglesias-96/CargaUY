package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardBarChartDTOTest {

    @Test
    public void testConstructors() {
        String nombreItem = "Item";
        int cantidad = 10;
        String nombreCategoria = "Categoria";

        // Constructor sin argumentos
        DashboardBarChartDTO chartDTO1 = new DashboardBarChartDTO();
        assertNull(chartDTO1.getNombreItem());
        assertEquals(0, chartDTO1.getCantidad());
        assertNull(chartDTO1.getNombreCategoria());

        chartDTO1.setNombreCategoria(nombreCategoria);
        assertEquals(nombreCategoria,chartDTO1.getNombreCategoria() );

        // Constructor con argumentos
        DashboardBarChartDTO chartDTO2 = new DashboardBarChartDTO(nombreItem, cantidad, nombreCategoria);
        assertEquals(nombreItem, chartDTO2.getNombreItem());
        assertEquals(cantidad, chartDTO2.getCantidad());
        assertEquals(nombreCategoria, chartDTO2.getNombreCategoria());
    }

}