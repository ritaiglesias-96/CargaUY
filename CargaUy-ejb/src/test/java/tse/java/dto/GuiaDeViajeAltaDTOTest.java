package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuiaDeViajeAltaDTOTest {

    @Test
    public void testConstructor() {
        // Arrange
        String rubroCliente = "Rubro";
        String tipoCarga = "Tipo de carga";
        float volumenCarga = 10.5f;
        String origen = "Origen";
        String destino = "Destino";
        String paisVehiculo = "País";
        String matriculaVehiculo = "Matrícula";
        String cedulaChofer = "Cédula";
        int numeroEmpresa = 123;

        // Act
        GuiaDeViajeAltaDTO guiaDTO = new GuiaDeViajeAltaDTO(rubroCliente, tipoCarga, volumenCarga, origen, destino,
                paisVehiculo, matriculaVehiculo, cedulaChofer, numeroEmpresa);

        // Assert
        assertEquals(rubroCliente, guiaDTO.getRubroCliente());
        assertEquals(tipoCarga, guiaDTO.getTipoCarga());
        assertEquals(volumenCarga, guiaDTO.getVolumenCarga());
        assertEquals(origen, guiaDTO.getOrigen());
        assertEquals(destino, guiaDTO.getDestino());
        assertEquals(paisVehiculo, guiaDTO.getPaisVehiculo());
        assertEquals(matriculaVehiculo, guiaDTO.getMatriculaVehiculo());
        assertEquals(cedulaChofer, guiaDTO.getCedulaChofer());
        assertEquals(numeroEmpresa, guiaDTO.getNumeroEmpresa());
    }

    @Test
    public void testDefaultConstructor() {
        // Arrange & Act
        GuiaDeViajeAltaDTO guiaDTO = new GuiaDeViajeAltaDTO();

        // Assert
        assertNull(guiaDTO.getRubroCliente());
        assertNull(guiaDTO.getTipoCarga());
        assertEquals(0.0f, guiaDTO.getVolumenCarga());
        assertNull(guiaDTO.getOrigen());
        assertNull(guiaDTO.getDestino());
        assertNull(guiaDTO.getPaisVehiculo());
        assertNull(guiaDTO.getMatriculaVehiculo());
        assertNull(guiaDTO.getCedulaChofer());
        assertEquals(0, guiaDTO.getNumeroEmpresa());
    }
    @Test
    public void testSetters() {
        // Arrange
        GuiaDeViajeAltaDTO guiaDTO = new GuiaDeViajeAltaDTO();

        // Act
        guiaDTO.setRubroCliente("Rubro");
        guiaDTO.setTipoCarga("Tipo de carga");
        guiaDTO.setVolumenCarga(10.5f);
        guiaDTO.setOrigen("Origen");
        guiaDTO.setDestino("Destino");
        guiaDTO.setPaisVehiculo("País");
        guiaDTO.setMatriculaVehiculo("Matrícula");
        guiaDTO.setCedulaChofer("Cédula");
        guiaDTO.setNumeroEmpresa(123);

        // Assert
        assertEquals("Rubro", guiaDTO.getRubroCliente());
        assertEquals("Tipo de carga", guiaDTO.getTipoCarga());
        assertEquals(10.5f, guiaDTO.getVolumenCarga());
        assertEquals("Origen", guiaDTO.getOrigen());
        assertEquals("Destino", guiaDTO.getDestino());
        assertEquals("País", guiaDTO.getPaisVehiculo());
        assertEquals("Matrícula", guiaDTO.getMatriculaVehiculo());
        assertEquals("Cédula", guiaDTO.getCedulaChofer());
        assertEquals(123, guiaDTO.getNumeroEmpresa());
    }
}