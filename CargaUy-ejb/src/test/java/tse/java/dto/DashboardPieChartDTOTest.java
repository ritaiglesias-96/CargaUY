package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DashboardPieChartDTOTest {

    @Test
    public void testEmptyConstructor() {
        DashboardPieChartDTO dashboardPieChartDTO = new DashboardPieChartDTO();

        // Verificar que los valores por defecto sean null
        assertNull(dashboardPieChartDTO.getNombreItem());
        assertEquals(0, dashboardPieChartDTO.getCantidad());
    }

    @Test
    public void testParameterizedConstructor() {
        String nombreItem = "Item 1";
        int cantidad = 10;

        DashboardPieChartDTO dashboardPieChartDTO = new DashboardPieChartDTO(nombreItem, cantidad);

        // Verificar que los valores se asignaron correctamente
        assertEquals(nombreItem, dashboardPieChartDTO.getNombreItem());
        assertEquals(cantidad, dashboardPieChartDTO.getCantidad());
    }

    @Test
    public void testSetters() {
        DashboardPieChartDTO dashboardPieChartDTO = new DashboardPieChartDTO();

        String nombreItem = "Item 1";
        dashboardPieChartDTO.setNombreItem(nombreItem);
        int cantidad = 10;
        dashboardPieChartDTO.setCantidad(cantidad);

        assertEquals(nombreItem, dashboardPieChartDTO.getNombreItem());
        assertEquals(cantidad, dashboardPieChartDTO.getCantidad());
    }
}