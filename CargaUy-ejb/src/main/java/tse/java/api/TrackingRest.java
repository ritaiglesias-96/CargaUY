package tse.java.api;


import tse.java.entity.Tracking;
import tse.java.service.ITrackingService;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    public Response addTracking(Tracking tracking){
        service.create(tracking);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/trackings")
    public Response updateTracking(Tracking tracking){
        service.update(tracking);
        return Response.status(Response.Status.OK).build();
    }


    @GET
    @Path("/trackings")
    public void getTracking(@QueryParam("matricula") String matricula,@QueryParam("pais") String pais){
        service.find(matricula, pais);
    }

}
