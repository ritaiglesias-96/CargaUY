package tse.java.beans;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import tse.java.dto.*;
import tse.java.entity.*;
import tse.java.enumerated.RolCiudadano;
import tse.java.enumerated.TipoUsuario;
import tse.java.exception.RubroExisteException;
import tse.java.persistance.ICiudadanoDAO;
import tse.java.service.ICiudadanosService;
import tse.java.service.IUsuariosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
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
        UsuarioDTO u2 = new UsuarioDTO("Rita", "Iglesias", LocalDate.now(), "rita.iglesias.adrover@gmail.com", "admin", "admin", TipoUsuario.ADMIN);
        iUsuariosService.registrarUsuario(u2);
        if(ciudadanoDAO.buscarCiudadanoPorId(1)==null){
            Chofer c = new Chofer("marce","1234");
            c.setAsignaciones(new ArrayList<Asignacion>());
            ciudadanosService.agregarHijoCiudadano(c);
        }
        if(ciudadanoDAO.buscarCiudadanoPorId(2)==null) {
            Ciudadano a = new Ciudadano("ciuda", "1111", new ArrayList<Asignacion>());
            ciudadanosService.agregarCiudadano(a);
        }
        listaCiudadanos = ciudadanoDAO.listarCiudadanos();
        listaCiudadanos.sort(Comparator.comparing(CiudadanoDTO::getCedula));
    }

    public void onRowEdit(RowEditEvent<CiudadanoDTO> event) {
        CiudadanoDTO dtr = event.getObject();
        String msg = "Me pasaron para modificar el ciudadano con id " + dtr.getIdCiudadano() + " con el documento " + dtr.getCedula();
        Logger.getLogger(asingarRolCiudadanoBean.class.getName()).log(Level.INFO, msg);
        Ciudadano c = ciudadanoDAO.buscarCiudadanoPorId(dtr.getIdCiudadano());
        if(rol.equals("Chofer") && !(c instanceof Chofer)) {
            Chofer ch = new Chofer(c.getEmail(),c.getCedula());
            ch.setAsignaciones(new ArrayList<Asignacion>());
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario))
                ciudadanosService.eliminarHijoCiudadano(c);
            else
                ciudadanosService.eliminarCiudadano(c);
            ciudadanosService.agregarHijoCiudadano(ch);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ciudadano actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } else if(rol.equals("Responsable") && !(c instanceof Responsable)){
            Responsable r = new Responsable(c.getEmail(),c.getCedula());
            r.setAsignaciones(new ArrayList<Asignacion>());
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario))
                ciudadanosService.eliminarHijoCiudadano(c);
            else
                ciudadanosService.eliminarCiudadano(c);
            ciudadanosService.agregarHijoCiudadano(r);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ciudadano actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } else if(rol.equals("Funcionario") && !(c instanceof Funcionario)){
            Funcionario f = new Funcionario(c.getEmail(),c.getCedula());
            f.setAsignaciones(new ArrayList<Asignacion>());
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario))
                ciudadanosService.eliminarHijoCiudadano(c);
            else
                ciudadanosService.eliminarCiudadano(c);
            ciudadanosService.agregarHijoCiudadano(f);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ciudadano actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } else if(rol.equals("Ciudadano") && !(c instanceof Ciudadano)){
            if((c instanceof Chofer) || (c instanceof Responsable) || (c instanceof Funcionario))
                ciudadanosService.eliminarHijoCiudadano(c);
            else
                ciudadanosService.eliminarCiudadano(c);
            ciudadanosService.agregarCiudadano(c);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Ciudadano actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
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

    public String getRolUsuario(CiudadanoDTO ciud){
        Ciudadano c = ciudadanoDAO.buscarCiudadanoPorId(ciud.getIdCiudadano());
        if(c instanceof Chofer)
            return "Chofer";
        else if(c instanceof Funcionario)
            return "Funcionario";
        else if(c instanceof Responsable)
            return "Responsable";
        else
            return "Ciudadano";
    }
}
