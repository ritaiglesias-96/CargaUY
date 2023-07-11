package tse.java.api.endpoint;

import org.jetbrains.annotations.TestOnly;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import tse.java.dto.CiudadanoDTO;
import tse.java.entity.*;
import tse.java.model.Ciudadanos;
import tse.java.service.ICiudadanosService;

import javax.persistence.NoResultException;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GestionCiudadanosEndpointTest {

    @Mock
    private ICiudadanosService ciudadanosService;

    @InjectMocks
    private GestionCiudadanosEndpoint gestionCiudadanosEndpoint;

    @Test
    public void testGetCiudadanos() {
        Ciudadanos ciudadanos = new Ciudadanos();
        ArrayList<CiudadanoDTO> ciudadanosList = new ArrayList<>();
        CiudadanoDTO ciudadano = new CiudadanoDTO();
        CiudadanoDTO ciudadano1 = new CiudadanoDTO();
        ciudadanosList.add(ciudadano);
        ciudadanosList.add(ciudadano1);
        ciudadanos.setListaCiudadanos(ciudadanosList);

        when(ciudadanosService.obtenerCiudadanos()).thenReturn(ciudadanos);
        Response response = gestionCiudadanosEndpoint.getCiudadanos();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(ciudadanos, response.getEntity());
    }
    @Test
    public void testGetCiudadanosThrowError() {
        when(ciudadanosService.obtenerCiudadanos()).thenThrow(new NoResultException("Not Found"));

        Response response = gestionCiudadanosEndpoint.getCiudadanos();

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testAgregarCiudadanos() {
        Ciudadano ciudadano = new Ciudadano();
        String Ci = "244212";
        ciudadano.setCedula(Ci);

        when(ciudadanosService.obtenerCiudadanoPorCedula(Ci)).thenReturn(ciudadano);

        doAnswer((Answer<Object>) invocation -> {
            Ciudadano ciudadano1 = (Ciudadano) invocation.getArguments()[0];
            ciudadano1.setIdCiudadano(1);
            return null;
        }).when(ciudadanosService).agregarCiudadano(any(Ciudadano.class));

        Response response = gestionCiudadanosEndpoint.agregarCiudadano(ciudadano);

        assertEquals(ciudadano,response.getEntity());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

    }

    @Test
    public void testAgregarCiudadanosThrowsError() {
        String Ci = "244212";
        Ciudadano ciudadano = new Ciudadano();
        //when(ciudadanosService.agregarCiudadano(ciudadano)).thenThrow(new NoResultException("Not Found"));
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).agregarCiudadano(any(Ciudadano.class));
        Response response = gestionCiudadanosEndpoint.agregarCiudadano(ciudadano);

        assertEquals(Response.Status.CONFLICT.getStatusCode(), response.getStatus());
    }

    @Test
    public void testModificarCiudadano() {
        Ciudadano ciudadano = new Ciudadano();
        String cedula = "abcd";
        Integer id = 1;
        ciudadano.setIdCiudadano(id);
        //when(ciudadanosService.obtenerCiudadanoPorCedula(cedula)).thenReturn(ciudadano);

        Response response = gestionCiudadanosEndpoint.modificarCiudadano(ciudadano,id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
        assertEquals(ciudadano, response.getEntity()); // Verificar que la entidad de la respuesta sea el mismo ciudadano modificado
    }
    @Test
    public void testModificarCiudadanoThrowError() {
        Ciudadano ciudadano = new Ciudadano();
        String cedula = "abcd";
        Integer id = 1;
        ciudadano.setIdCiudadano(id);
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).modificarCiudadano(ciudadano);
        Response response = gestionCiudadanosEndpoint.modificarCiudadano(ciudadano,id);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
    }

    @Test
    public void testEliminarCiudadano() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).eliminarCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarCiudadano(id);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarCiudadanoThrowError() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).eliminarCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarCiudadano(id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testAgregarFuncionario() {
        Funcionario funcionario = new Funcionario();
        int id = 3;
        funcionario.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).agregarHijoCiudadano(any(Funcionario.class));

        Response response = gestionCiudadanosEndpoint.agregarFuncionario(funcionario);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }
    @Test
    public void testAgregarFuncionarioThrowError() {
        Funcionario funcionario = new Funcionario();
        int id = 3;
        funcionario.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            throw new RuntimeException("Conflict");
        }).when(ciudadanosService).agregarHijoCiudadano(any(Funcionario.class));

        Response response = gestionCiudadanosEndpoint.agregarFuncionario(funcionario);
        assertEquals(Response.Status.CONFLICT.getStatusCode(),response.getStatus());
    }

    @Test
    public void testAgregarResponsable() {
        Responsable responsable = new Responsable();
        int id = 3;
        responsable.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).agregarHijoCiudadano(any(Responsable.class));

        Response response = gestionCiudadanosEndpoint.agregarResponsable(responsable);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());

    }

    @Test
    public void testAgregarResponsableThrowError() {
        Responsable responsable = new Responsable();
        int id = 3;
        responsable.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            throw new RuntimeException("Conflict");
        }).when(ciudadanosService).agregarHijoCiudadano(any(Responsable.class));

        Response response = gestionCiudadanosEndpoint.agregarResponsable(responsable);
        assertEquals(Response.Status.CONFLICT.getStatusCode(),response.getStatus());
    }

    @Test
    public void testAgregarChofer() {
        Chofer chofer = new Chofer();
        int id = 3;
        chofer.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).agregarHijoCiudadano(any(Chofer.class));

        Response response = gestionCiudadanosEndpoint.agregarChofer(chofer);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }
    @Test
    public void testAgregarChoferThrowError() {
        Chofer chofer = new Chofer();
        int id = 3;
        chofer.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            throw new RuntimeException("Conflict");
        }).when(ciudadanosService).agregarHijoCiudadano(any(Chofer.class));

        Response response = gestionCiudadanosEndpoint.agregarChofer(chofer);
        assertEquals(Response.Status.CONFLICT.getStatusCode(),response.getStatus());
    }

    @Test
    public void testModificarFuncionario() {
        Funcionario funcionario = new Funcionario();
        int id = 3;
        funcionario.setIdCiudadano(id);

        Response response = gestionCiudadanosEndpoint.modificarFuncionario(funcionario,id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
        assertEquals(funcionario, response.getEntity()); // Verificar que la entidad de la respuesta sea el mismo ciudadano modificado
    }
    @Test
    public void testModificarFuncionarioThrowError() {
        Funcionario funcionario = new Funcionario();
        int id = 3;
        funcionario.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).modificarHijoCiudadano(funcionario);
        Response response = gestionCiudadanosEndpoint.modificarFuncionario(funcionario,id);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
    }

    @Test
    public void testModificarResponsable() {
        Responsable responsable = new Responsable();
        int id = 3;
        responsable.setIdCiudadano(id);

        Response response = gestionCiudadanosEndpoint.modificarResponsable(responsable,id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
        assertEquals(responsable, response.getEntity()); // Verificar que la entidad de la respuesta sea el mismo ciudadano modificado
    }

    @Test
    public void testModificarResponsableThrowError() {
        Responsable responsable = new Responsable();
        int id = 3;
        responsable.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).modificarHijoCiudadano(responsable);
        Response response = gestionCiudadanosEndpoint.modificarResponsable(responsable,id);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
    }

    @Test
    public void testModificarChofer() {
        Chofer chofer = new Chofer();
        int id = 3;
        chofer.setIdCiudadano(id);

        Response response = gestionCiudadanosEndpoint.modificarCiudadano(chofer,id);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
        assertEquals(chofer, response.getEntity()); // Verificar que la entidad de la respuesta sea el mismo ciudadano modificado

    }
    @Test
    public void testModificarChoferThrowError() {
        Chofer chofer = new Chofer();
        int id = 3;
        chofer.setIdCiudadano(id);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).modificarHijoCiudadano(chofer);
        Response response = gestionCiudadanosEndpoint.modificarCiudadano(chofer,id);

        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus()); // Verificar el estado de la respuesta (OK)
    }

    @Test
    public void testEliminarFuncionario() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).eliminarHijoCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarFuncionario(id);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarFuncionarioThrowError() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).eliminarHijoCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarFuncionario(id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarResponsable() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).eliminarHijoCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarResponsable(id);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarResponsableThrowError() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).eliminarHijoCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarResponsable(id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarChofer() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).eliminarHijoCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarChofer(id);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarChoferThrowError() {
        int id = 1;
        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).eliminarHijoCiudadano(id);
        Response response = gestionCiudadanosEndpoint.eliminarChofer(id);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }


    @Test
    public void testAsignarEmpresa() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).asignarEmpresa(id, empresa);
        Response response = gestionCiudadanosEndpoint.asignarEmpresa(empresa,id,empresaId);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }
    @Test
    public void testAsignarEmpresaThrowError() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).asignarEmpresa(id, empresa);
        Response response = gestionCiudadanosEndpoint.asignarEmpresa(empresa,id,empresaId);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }


    @Test
    public void testEliminarEmpresa() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).eliminarEmpresa(id, empresa);
        Response response = gestionCiudadanosEndpoint.eliminarEmpresa(empresa,id,empresaId);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarEmpresaThrowError() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).eliminarEmpresa(id, empresa);
        Response response = gestionCiudadanosEndpoint.eliminarEmpresa(empresa,id,empresaId);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testAsignarEmpresaChofer() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).asignarEmpresaChofer(id, empresa);
        Response response = gestionCiudadanosEndpoint.asignarEmpresaChofer(empresa,id,empresaId);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testAsignarEmpresaChoferThrowError() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).asignarEmpresaChofer(id, empresa);
        Response response = gestionCiudadanosEndpoint.asignarEmpresaChofer(empresa,id,empresaId);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarEmpresaChofer() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            return null;
        }).when(ciudadanosService).eliminarEmpresaChofer(id, empresa);
        Response response = gestionCiudadanosEndpoint.eliminarEmpresaChofer(empresa,id,empresaId);
        assertEquals(Response.Status.OK.getStatusCode(),response.getStatus());
    }

    @Test
    public void testEliminarEmpresaChoferThrowError() {
        Empresa empresa = new Empresa();
        int id = 1, empresaId = 2;
        empresa.setId(1);

        doAnswer((Answer<Object>) invocation -> {
            throw new NoResultException("Not Found");
        }).when(ciudadanosService).eliminarEmpresaChofer(id, empresa);
        Response response = gestionCiudadanosEndpoint.eliminarEmpresaChofer(empresa,id,empresaId);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(),response.getStatus());
    }

}