package tse.java.api.endpoint;

import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.service.IEmpresasService;

@RequestScoped
@Path("/pesajes")
@Produces({ MediaType.APPLICATION_JSON })
public class NodoBalanzasEndpoint {

    @EJB
    IEmpresasService eService;

    @GET
    public Response listarBalanzas(@QueryParam("numemp") int numemp, @QueryParam("pais") String pais, @QueryParam("matricula") String mat, @QueryParam("fecha") String fec){
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fec);
            List<GuiaDeViajeDTO> guias = eService.listarGuias(numemp, mat, pais, fecha);
            if(guias.size()==0)
                return Response.status(Response.Status.OK).entity(guias).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (ParseException e) {
            Logger.getLogger(NodoBalanzasEndpoint.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).build();

        }
    }
    
}
