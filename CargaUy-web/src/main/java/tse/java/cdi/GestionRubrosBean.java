package tse.java.cdi;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;
import tse.java.service.IRubrosService;
import tse.java.service.impl.RubrosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("gestionarRubrosBean")
@RequestScoped
public class GestionRubrosBean {

    @EJB
    IRubrosService rubejb;

    private String nombre;

    private RubroDTO rubro = new RubroDTO();

    private List<RubroDTO> rubros = new ArrayList<RubroDTO>();

    public List<RubroDTO> getRubros() {
        return rubros;
    }

    public void setRubros(List<RubroDTO> rubros) {
        this.rubros = rubros;
    }

    public RubroDTO getRubro() {
        return rubro;
    }

    public void setRubro(RubroDTO rubro) {
        this.rubro = rubro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @PostConstruct
    public void cargarListado() {
        rubros = rubejb.listarRubros();
        rubros.sort(Comparator.comparing(RubroDTO::getId));
    }

    public void borrarRubro(RubroDTO dtr) {
        rubejb.eliminarRubro(dtr.getId());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("/CargaUy-web/admin/gestionRubros.xhtml");
            fc.responseComplete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRowEdit(RowEditEvent<RubroDTO> event) {
        RubroDTO dtr = event.getObject();
        String msg = "Me pasaron para modificar el rubro con id " + dtr.getId() + " con el nombre " + dtr.getNombre();
        Logger.getLogger(GestionRubrosBean.class.getName()).log(Level.INFO, msg);
        try {
            rubejb.modificarRubro(dtr);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Rubro actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (RubroExisteException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe un rubro con el nombre " + dtr.getNombre());
            RubroDTO dtraux = rubejb.buscarRubro(dtr.getId());
            event.getObject().setNombre(dtraux.getNombre());
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void onRowCancel(RowEditEvent<RubroDTO> event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Edicion Cancelada.");
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void AltaRubro() {
        RubroDTO dtr = new RubroDTO(null, nombre);
        try {
            rubejb.altaRubro(dtr);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("/CargaUy-web/admin/gestionRubros.xhtml");
            fc.responseComplete();
        } catch (RubroExisteException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }
}