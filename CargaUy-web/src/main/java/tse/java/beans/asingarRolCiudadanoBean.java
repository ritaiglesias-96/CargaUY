package tse.java.beans;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import tse.java.dto.CiudadanoDTO;
import tse.java.dto.RubroDTO;
import tse.java.dto.UsuarioDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.entity.Funcionario;
import tse.java.entity.Responsable;
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

    @PostConstruct
    public void init(){
        listaCiudadanos = ciudadanosService.obtenerCiudadanos().getListaCiudadanos();
        listaCiudadanos.sort(Comparator.comparing(CiudadanoDTO::getIdCiudadano));

        UsuarioDTO u2 = new UsuarioDTO("Rita", "Iglesias", LocalDate.now(), "rita.iglesias.adrover@gmail.com", "admin", "admin", TipoUsuario.ADMIN);
        iUsuariosService.registrarUsuario(u2);
    }

    public void onRowEdit(RowEditEvent<CiudadanoDTO> event) {
        CiudadanoDTO dtr = event.getObject();
        String msg = "Me pasaron para modificar el ciudadano con id " + dtr.getIdCiudadano() + " con el documento " + dtr.getCedula();
        Logger.getLogger(asingarRolCiudadanoBean.class.getName()).log(Level.INFO, msg);
        RolCiudadano r1;
        Ciudadano c = ciudadanoDAO.buscarCiudadanoPorId(dtr.getIdCiudadano());
        if(c instanceof Chofer && dtr.getRol()!= RolCiudadano.CHOFER) {
            Chofer ch = (Chofer) c;
            ciudadanosService.eliminarCiudadano(c);
            ciudadanosService.agregarHijoCiudadano(ch);
            r1 = RolCiudadano.CHOFER;
        } else if(c instanceof Responsable  && dtr.getRol()!= RolCiudadano.RESPONSABLE){
            Responsable r = (Responsable) c;
            ciudadanosService.eliminarCiudadano(c);
            ciudadanosService.agregarHijoCiudadano(r);
            r1 = RolCiudadano.RESPONSABLE;
        } else {
            if(dtr.getRol()!=RolCiudadano.FUNCIONARIO) {
                Funcionario f = (Funcionario) c;
                ciudadanosService.eliminarCiudadano(c);
                ciudadanosService.agregarHijoCiudadano(f);
                r1 = RolCiudadano.FUNCIONARIO;
            }
        }
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Rubro actualizado.");
        PrimeFaces.current().dialog().showMessageDynamic(message);
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
}
