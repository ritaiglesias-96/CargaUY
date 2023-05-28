package tse.java.beans;

import org.primefaces.PrimeFaces;
import tse.java.dto.TipoCargaDTO;
import tse.java.exception.TipoCargaExisteException;
import tse.java.persistance.ITipoCargaDAO;
import tse.java.service.IRubrosService;
import tse.java.service.ITipoCargaService;
import tse.java.service.impl.TipoCargaService;

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

@Named("gestionarTipoCargaBean")
@RequestScoped
public class GestionTipoCargaBean {

    @EJB
    ITipoCargaService tipoCargaService;

    private String nombre;

    private TipoCargaDTO tipocarga = new TipoCargaDTO();

    private List<TipoCargaDTO> listatipocarga = new ArrayList<TipoCargaDTO>();

    public TipoCargaDTO getTipocarga() {
        return tipocarga;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTipocarga(TipoCargaDTO tipocarga) {
        this.tipocarga = tipocarga;
    }

    public List<TipoCargaDTO> getListatipocarga() {
        return listatipocarga;
    }

    public void setListatipocarga(List<TipoCargaDTO> listatipocarga) {
        this.listatipocarga = listatipocarga;
    }



    @PostConstruct
    public void cargarListado(){
        listatipocarga = tipoCargaService.listarTipoCarga();
        listatipocarga.sort(Comparator.comparing(TipoCargaDTO::getId));
    }
    public void altaTipoCarga(){
        TipoCargaDTO tcdt = new TipoCargaDTO(null,nombre);
        try{
            tipoCargaService.altaTipoCarga(tcdt);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("/CargaUy-web/admin/gestionTipoCarga.xhtml");
            fc.responseComplete();
        } catch (TipoCargaExisteException e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El Tipo de carga ingresado ya existe");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void borrarTipoCarga(TipoCargaDTO tcdt){
        tipoCargaService.eliminarTipoCarga(tcdt.getId());
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        try {
            ec.redirect("/CargaUy-web/admin/gestionTipoCarga.xhtml");
            fc.responseComplete();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
