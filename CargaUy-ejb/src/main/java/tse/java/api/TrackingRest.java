package tse.java.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.util.Iterator;
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
    public Response addTracking(String body) throws JsonProcessingException {
        String msg = "Request: " + body;
        Logger.getLogger(TrackingRest.class.getName()).log(Level.INFO, msg);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jn = objectMapper.readTree(body);
        Iterator<JsonNode> iter = jn.elements();
        while(iter.hasNext()){
            JsonNode node = iter.next();
            String id = node.get("id").asText();
            String mat = node.get("matricula").asText();
            String pais = node.get("pais").asText();
            String lat = node.get("latitude").asText();
            String lon = node.get("longitude").asText();
            String ts = node.get("timestamp").asText();
            msg = "id: " + id + ". matricula: " + mat + ". pais: " + pais + ". longitude: " + lon + ". latitude: " + lat + ". fecha: " + ts;
            Logger.getLogger(TrackingRest.class.getName()).log(Level.INFO, msg);
            TrackingDTO aux = new TrackingDTO(Long.parseLong(id), mat, pais, lon, lat, ts);
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
