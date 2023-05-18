package tse.java.api;


import tse.java.dto.TrackingDTO;
import tse.java.entity.Tracking;
import tse.java.service.ITrackingService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@Path("/tracking")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class TrackingRest {

    @EJB
    ITrackingService service;


    public TrackingRest() {
    }

    @POST
    @Path("/trackings")
    public Response addTracking(ArrayList<TrackingDTO> list){
        String msg = "Me pasaron para agregar una lista de tracking de tamaño " + list.size();
        Logger.getLogger(TrackingRest.class.getName()).log(Level.INFO, msg);
        for (TrackingDTO aux : list) {
            msg = "id: " + aux.getId() + ". matricula: " + aux.getMatricula() + ". pais: " + aux.getPais() + ". longitude: " + aux.getLongitude() + ". latitude: " + aux.getLatitude() + ". fecha: " + aux.getTimestamp() +  ". class: " + aux.getClass();
            Logger.getLogger(TrackingRest.class.getName()).log(Level.INFO, msg);
            Tracking t = new Tracking(aux);
            service.create(t);
        }
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/trackings")
    public TrackingDTO getTracking(@QueryParam("matricula") String matricula,@QueryParam("pais") String pais){
        return service.find(matricula, pais);
    }

}
