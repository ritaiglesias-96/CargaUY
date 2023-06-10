package tse.java.beans;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import tse.java.dto.RubroDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.dto.UsuarioDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Usuario;
import tse.java.enumerated.TipoUsuario;
import tse.java.exception.RubroExisteException;
import tse.java.exception.TipoCargaExisteException;
import tse.java.persistance.ITipoCargaDAO;
import tse.java.service.IUsuariosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("gestionTiposCargaBean")
@RequestScoped
public class gestionTipoCargaBean {

    @EJB
    ITipoCargaDAO tipoCargaDAO;

    @EJB
    IUsuariosService iUsuariosService;
    private String nombre;

    private TipoCargaDTO tc = new TipoCargaDTO();

    private List<TipoCargaDTO> tiposDeCarga = new ArrayList<TipoCargaDTO>();

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public TipoCargaDTO getTc() {
        return tc;
    }

    public void setTc(TipoCargaDTO tc) {
        this.tc = tc;
    }

    public List<TipoCargaDTO> getTiposDeCarga() {
        return tiposDeCarga;
    }

    public void setTiposDeCarga(List<TipoCargaDTO> tiposDeCarga) {
        this.tiposDeCarga = tiposDeCarga;
    }

    @PostConstruct
    public void init() {
        tiposDeCarga = tipoCargaDAO.listarTipoCarga();
        tiposDeCarga.sort(Comparator.comparing(TipoCargaDTO::getId));
    }

    public void borrarTipoCarga(TipoCargaDTO tc) {
        tipoCargaDAO.borrarTipoCarga(tc.getId());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("/CargaUy-web/admin/gestionTipoCarga.xhtml");
            fc.responseComplete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRowEdit(RowEditEvent<TipoCargaDTO> event) {
        TipoCargaDTO dtr = event.getObject();
        String msg = "Me pasaron para modificar el tipo de carga con id " + dtr.getId() + " con el nombre " + dtr.getNombre();
        Logger.getLogger(gestionTipoCargaBean.class.getName()).log(Level.INFO, msg);
        try {
            tipoCargaDAO.modificarTipoCarga(dtr);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Tipo de carga actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (TipoCargaExisteException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Ya existe un tipo de carga con el nombre " + dtr.getNombre());
            TipoCargaDTO dtraux = tipoCargaDAO.buscarTipoCargaPorId(dtr.getId());
            event.getObject().setNombre(dtraux.getNombre());
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void onRowCancel(RowEditEvent<RubroDTO> event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Edicion Cancelada.");
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void AltaTipoCarga() {
        TipoCargaDTO tc = new TipoCargaDTO(null, nombre);
        try {
            tipoCargaDAO.altaTipoCarga(tc);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("/CargaUy-web/admin/gestionTipoCarga.xhtml");
            fc.responseComplete();
        } catch (TipoCargaExisteException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (IOException e2) {
            throw new RuntimeException(e2);
        }
    }


}
