package tse.java.api.endpoint;

import tse.java.dto.RubroDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.exception.RubroExisteException;
import tse.java.exception.TipoCargaExisteException;
import tse.java.persistance.IRubrosDAO;
import tse.java.persistance.ITipoCargaDAO;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Path("/front")
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public class utilitiesEndpoint {

    @EJB
    IRubrosDAO rubrosDAO;

    @EJB
    ITipoCargaDAO tipoCargaDAO;

    @GET
    @Path("/listarRubros")
    public Response listadoRubros(){
        List<String> rubros = new ArrayList<String>();
        for(RubroDTO r:rubrosDAO.listarRubros())
            rubros.add(r.getNombre());
        return Response.status(Response.Status.OK).entity(rubros).build();
    }

    @GET
    @Path("/listarTiposCarga")
    public Response listadoTiposCarga(){
        List<String> tiposCarga = new ArrayList<String>();
        for(TipoCargaDTO tc : tipoCargaDAO.listarTipoCarga())
            tiposCarga.add(tc.getNombre());
        return Response.status(Response.Status.OK).entity(tiposCarga).build();
    }

    @POST
    public Response test() throws RubroExisteException, TipoCargaExisteException {
        RubroDTO r1 = new RubroDTO(null, "Farmacos");
        RubroDTO r2 = new RubroDTO(null, "Carnes");
        rubrosDAO.altaRubro(r1);
        rubrosDAO.altaRubro(r2);
        TipoCargaDTO t1 = new TipoCargaDTO(null, "Quimicos");
        TipoCargaDTO t2 = new TipoCargaDTO(null, "Alimentos");
        tipoCargaDAO.altaTipoCarga(t1);
        tipoCargaDAO.altaTipoCarga(t2);
        return Response.status(Response.Status.OK).build();
    }

}
