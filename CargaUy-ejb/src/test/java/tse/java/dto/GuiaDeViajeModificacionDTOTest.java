package tse.java.dto;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuiaDeViajeModificacionDTOTest {

    @Test
    public void testEmptyConstructor() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();

        // Verificar que los valores por defecto se asignaron correctamente
        assertNull(guiaDeViajeModificacionDTO.getRubroCliente());
        assertNull(guiaDeViajeModificacionDTO.getTipoCarga());
        assertEquals(0.0f, guiaDeViajeModificacionDTO.getVolumenCarga());
        assertNull(guiaDeViajeModificacionDTO.getOrigen());
        assertNull(guiaDeViajeModificacionDTO.getDestino());
        assertNull(guiaDeViajeModificacionDTO.getPaisVehiculo());
        assertNull(guiaDeViajeModificacionDTO.getMatriculaVehiculo());
        assertNull(guiaDeViajeModificacionDTO.getCedulaChofer());
       // assertEquals(0, guiaDeViajeModificacionDTO.getNumeroEmpresa());
        assertEquals(0, guiaDeViajeModificacionDTO.getNumero());
    }

    @Test
    public void testParameterizedConstructor() {
        String rubroCliente = "Cliente 1";
        String tipoCarga = "Carga 1";
        float volumenCarga = 10.0f;
        String origen = "Origen 1";
        String destino = "Destino 1";
        String paisVehiculo = "Pais 1";
        String matriculaVehiculo = "Matricula 1";
        String cedulaChofer = "Cedula 1";
        int numeroEmpresa = 1;
        int id = 1000;
        int numeroViaje = 123;

        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO(id,
                rubroCliente, tipoCarga, volumenCarga, origen, destino, paisVehiculo, matriculaVehiculo,
                cedulaChofer, numeroEmpresa, numeroViaje);

        // Verificar que los valores se asignaron correctamente
        assertEquals(rubroCliente, guiaDeViajeModificacionDTO.getRubroCliente());
        assertEquals(tipoCarga, guiaDeViajeModificacionDTO.getTipoCarga());
        assertEquals(volumenCarga, guiaDeViajeModificacionDTO.getVolumenCarga());
        assertEquals(origen, guiaDeViajeModificacionDTO.getOrigen());
        assertEquals(destino, guiaDeViajeModificacionDTO.getDestino());
        assertEquals(paisVehiculo, guiaDeViajeModificacionDTO.getPaisVehiculo());
        assertEquals(matriculaVehiculo, guiaDeViajeModificacionDTO.getMatriculaVehiculo());
        assertEquals(cedulaChofer, guiaDeViajeModificacionDTO.getCedulaChofer());
       // assertEquals(numeroEmpresa, guiaDeViajeModificacionDTO.getNumeroEmpresa());
        assertEquals(numeroViaje, guiaDeViajeModificacionDTO.getNumero());
    }

    @Test
    public void testSetter() {
        GuiaDeViajeModificacionDTO guiaDeViajeModificacionDTO = new GuiaDeViajeModificacionDTO();
        int numeroViaje = 1;
        guiaDeViajeModificacionDTO.setNumero(numeroViaje);

        assertEquals(numeroViaje,guiaDeViajeModificacionDTO.getNumero());
    }


}