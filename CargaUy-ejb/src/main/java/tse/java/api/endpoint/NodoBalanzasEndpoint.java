package tse.java.api.endpoint;

import java.util.ArrayList;
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

import tse.java.dto.EmpresaDTO;
import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.dto.VehiculoDTO;
import tse.java.entity.Empresa;
import tse.java.entity.GuiaDeViaje;
import tse.java.entity.Vehiculo;
import tse.java.persistance.IEmpresasDAO;
import tse.java.persistance.IGuiaDeViajeDAO;
import tse.java.persistance.IVehiculosDAO;
import tse.java.service.IEmpresasService;

@RequestScoped
@Path("/pesajes")
@Produces({ MediaType.APPLICATION_JSON })
public class NodoBalanzasEndpoint {

    @EJB
    IEmpresasService eService;

    @GET
    public Response listarBalanzas(@QueryParam("numemp") int numemp, @QueryParam("pais") String pais, @QueryParam("matricula") String mat, @QueryParam("fecha") String fec){
        String msg = "Me pasaron por rest los parametros: numemp=" + numemp + ", pais=" + pais + ", matricula=" + mat + ", fecha=" + fec;
        Logger.getLogger(NodoBalanzasEndpoint.class.getName()).log(Level.INFO, msg);
        Date fecha = null;
        try {
            fecha = new SimpleDateFormat("dd/MM/yyyy").parse(fec);
            List<PesajeDTO> pesajes = eService.listarGuias(numemp, mat, pais, fecha);
            if(pesajes.size()>0)
                return Response.status(Response.Status.OK).entity(pesajes).build();
            else
                return Response.status(Response.Status.NOT_FOUND).build();
        } catch (ParseException e) {
            Logger.getLogger(NodoBalanzasEndpoint.class.getName()).log(Level.SEVERE, null, e);
            return Response.status(Response.Status.BAD_REQUEST).build();

        }
    }
    
}
