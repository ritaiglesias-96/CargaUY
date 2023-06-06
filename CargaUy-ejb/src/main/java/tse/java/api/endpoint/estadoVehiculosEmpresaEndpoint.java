package tse.java.api.endpoint;

import tse.java.dto.VehiculoDTO;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequestScoped
@Path("/vehiculos-empresa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class estadoVehiculosEmpresaEndpoint {
    @EJB
    IVehiculosService vs;

    @EJB
    IEmpresasService es;

    @GET
    @Path("/{id}")
    public Response getVehiculos(@PathParam("id") int id){
        try{
            List<VehiculoDTO> vehiculos = es.listarVehiculos(id);
            return Response.status(Response.Status.OK).entity(vehiculos).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
