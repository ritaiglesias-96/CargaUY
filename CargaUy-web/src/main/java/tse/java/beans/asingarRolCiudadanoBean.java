package tse.java.beans;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.enumerated.RolCiudadano;
import tse.java.enumerated.TipoUsuario;
import tse.java.exception.RubroExisteException;
import tse.java.persistance.IChoferDAO;
import tse.java.persistance.ICiudadanoDAO;
import tse.java.service.ICiudadanosService;
import tse.java.service.IUsuariosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("asignarRoles")
@RequestScoped
public class asingarRolCiudadanoBean {

    @EJB
    ICiudadanosService ciudadanosService;

    @EJB
    ICiudadanoDAO ciudadanoDAO;

    @EJB
    IUsuariosService iUsuariosService;

    private List<CiudadanoDTO> listaCiudadanos = new ArrayList<CiudadanoDTO>();

    private String rol="";


    @PostConstruct
    public void init(){
        listaCiudadanos = ciudadanoDAO.listarCiudadanos();
        listaCiudadanos.sort(Comparator.comparing(CiudadanoDTO::getCedula));
    }

    public void onRowEdit(RowEditEvent<CiudadanoDTO> evegetCedulant) throws IOException {
        CiudadanoDTO dtr = evegetCedulant.getObject();
        String msg = "Me pasaron para modificar el ciudadano con id " + dtr.getIdCiudadano() + " con el documento " + dtr.getCedula();
        Logger.getLogger(asingarRolCiudadanoBean.class.getName()).log(Level.INFO, msg);
        Ciudadano c = ciudadanoDAO.buscarCiudadanoPorId(dtr.getIdCiudadano());
        if(rol.equals("Chofer") && !(c instanceof Chofer)) {
            Chofer ch = new Chofer(c.getEmail(),c.getCedula(),new ArrayList<Asignacion>());
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario))
                ciudadanosService.eliminarHijoCiudadano(c.getIdCiudadano());
            else
                ciudadanosService.eliminarCiudadano(c.getIdCiudadano());
            ciudadanosService.agregarHijoCiudadano(ch);
            recargarPagina();
        } else if(rol.equals("Responsable") && !(c instanceof Responsable)){
            Responsable r = new Responsable(c.getEmail(),c.getCedula());
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario)) {
                if (c instanceof Chofer) {
                    Chofer ch = (Chofer) c;
                    ch.setAsignaciones(new ArrayList<Asignacion>());
                    ciudadanosService.modificarHijoCiudadano(ch);
                }
                ciudadanosService.eliminarHijoCiudadano(c.getIdCiudadano());
            } else {
                ciudadanosService.eliminarCiudadano(c.getIdCiudadano());
            }
            ciudadanosService.agregarHijoCiudadano(r);
            recargarPagina();
        } else if(rol.equals("Funcionario") && !(c instanceof Funcionario)){
            Funcionario f = new Funcionario(c.getEmail(),c.getCedula());
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario)) {
                if (c instanceof Chofer) {
                    Chofer ch = (Chofer) c;
                    ch.setAsignaciones(new ArrayList<Asignacion>());
                    ciudadanosService.modificarHijoCiudadano(ch);
                }
                ciudadanosService.eliminarHijoCiudadano(c.getIdCiudadano());
            } else {
                ciudadanosService.eliminarCiudadano(c.getIdCiudadano());
            }
            ciudadanosService.agregarHijoCiudadano(f);
            recargarPagina();
        } else if(rol.equals("Ciudadano") && ((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario))){
            Ciudadano cnew = new Ciudadano(c.getEmail(),c.getCedula(),null);
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario)) {
                if (c instanceof Chofer) {
                    Chofer ch = (Chofer) c;
                    ch.setAsignaciones(new ArrayList<Asignacion>());
                    ciudadanosService.modificarHijoCiudadano(ch);
                }
                ciudadanosService.eliminarHijoCiudadano(c.getIdCiudadano());
            } else {
                ciudadanosService.eliminarCiudadano(c.getIdCiudadano());
            }
            ciudadanosService.agregarCiudadano(cnew);
            recargarPagina();
        } else {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Se intenta modificar el ciudadano por el rol que ya tiene");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void onRowCancel(RowEditEvent<RubroDTO> event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Edicion Cancelada.");
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public List<CiudadanoDTO> getListaCiudadanos() {
        return listaCiudadanos;
    }

    public void setListaCiudadanos(List<CiudadanoDTO> listaCiudadanos) {
        this.listaCiudadanos = listaCiudadanos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    private void recargarPagina(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("/CargaUy-web/admin/asignarRoles.xhtml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fc.responseComplete();
    }
}
