package tse.java.api.endpoint;

import tse.java.dto.EmpresaDTO;
import tse.java.dto.PermisosVehiculoDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.model.Empresas;
import tse.java.service.IEmpresasService;
import tse.java.service.IVehiculosService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
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
            List<PermisosVehiculoDTO> permisos = new ArrayList<PermisosVehiculoDTO>();
            for(VehiculoDTO v:vehiculos){
                PermisosVehiculoDTO p = new PermisosVehiculoDTO(v);
                permisos.add(p);
            }
            return Response.status(Response.Status.OK).entity(permisos).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response getTodosVehiculos(){
        try{
            List<EmpresaDTO> empresas = es.obtenerEmpresas().getListaEmpresas();
            List<List<PermisosVehiculoDTO>> listaPermisosEmpresas = new ArrayList<>();
            for(EmpresaDTO e:empresas){
                List<PermisosVehiculoDTO> permisos = new ArrayList<PermisosVehiculoDTO>();
                for (VehiculoDTO v:e.getVehiculos()){
                    PermisosVehiculoDTO p = new PermisosVehiculoDTO(v);
                    permisos.add(p);
                }
                listaPermisosEmpresas.add(permisos);
            }
            return Response.status(Response.Status.OK).entity(listaPermisosEmpresas).build();
        }catch (NoResultException e ){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
