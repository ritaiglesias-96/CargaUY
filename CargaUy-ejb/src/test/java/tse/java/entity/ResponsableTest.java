package tse.java.entity;

import org.junit.Test;
import tse.java.dto.ResponsableDTO;
import tse.java.enumerated.RolCiudadano;

import static org.junit.jupiter.api.Assertions.*;

public class ResponsableTest {

    @Test
    public void testConstructorEmpresa() {
        // Arrange
        Empresa empresa = new Empresa();

        // Act
        Responsable responsable = new Responsable(empresa);

        // Assert
        assertEquals(empresa, responsable.getEmpresa());
    }

    @Test
    public void testConstructorEmailCedula() {
        // Arrange
        String email = "responsable@example.com";
        String cedula = "1234567890";

        // Act
        Responsable responsable = new Responsable(email, cedula);

        // Assert
        assertEquals(email, responsable.getEmail());
        assertEquals(cedula, responsable.getCedula());
        assertEquals(RolCiudadano.RESPONSABLE, responsable.getRol());
    }
    @Test
    public void testDarDto() {
        // Arrange
        Integer idCiudadano = 12;
        String email = "responsable@example.com";
        String cedula = "1234567890";
        Empresa empresa = new Empresa();

        Responsable responsable = new Responsable();
        responsable.setIdCiudadano(idCiudadano);
        responsable.setEmail(email);
        responsable.setCedula(cedula);
        responsable.setEmpresa(empresa);

        // Act
        ResponsableDTO responsableDTO = responsable.darDto();

        // Assert
        assertEquals(idCiudadano, responsableDTO.getIdCiudadano());
        assertEquals(email, responsableDTO.getEmail());
        assertEquals(cedula, responsableDTO.getCedula());
        assertEquals(RolCiudadano.RESPONSABLE, responsableDTO.getRol());
        assertEquals(empresa.getClass(),responsable.getEmpresa().getClass());
    }


}