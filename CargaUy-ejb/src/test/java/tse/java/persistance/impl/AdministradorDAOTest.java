package tse.java.persistance.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.entity.Administrador;
import tse.java.util.EntityManagerProducer;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class AdministradorDAOTest {

    @InjectMocks
    AdministradorDAO administradorDAO;
    @Spy
    private EntityManager entityManagerMock;


    @Test
    public void testPersist() {


        // Datos de prueba
        Administrador administrador = new Administrador();
        administrador.setNombre("John");
        administrador.setApellido("Doe");
        administrador.setId(1);

        // Llamar al método bajo prueba
        entityManagerMock.persist(administrador);

        // Verificar el resultado
        // Realiza aserciones o consultas a la base de datos para verificar la persistencia
        Administrador administradorPersistido = entityManagerMock.find(Administrador.class, 1);
        assertNotNull(administradorPersistido);
        assertEquals("John", administradorPersistido.getNombre());
        assertEquals("Doe", administradorPersistido.getApellido());
    }

}