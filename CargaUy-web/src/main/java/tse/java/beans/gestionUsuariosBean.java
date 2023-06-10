package tse.java.beans;

import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import tse.java.dto.UsuarioDTO;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.enumerated.TipoUsuario;
import tse.java.exception.UsuarioExisteException;
import tse.java.service.IUsuariosService;
import tse.java.vistas.UserManager;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.sql.Date;
import java.util.*;


@Named("gestionarUsuarios")
@RequestScoped
public class gestionUsuariosBean {

    @Inject
    UserManager userManager;
    @EJB
    IUsuariosService usuariosService;

    private String selectTypeValues;
    private UsuarioDTO usuario = new UsuarioDTO();

    private List<UsuarioDTO> listaUsuarios = new ArrayList<UsuarioDTO>();

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public List<UsuarioDTO> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<UsuarioDTO> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getSelectTypeValues() {
        return selectTypeValues;
    }

    public void setSelectTypeValues(String selectTypeValues) {
        this.selectTypeValues = selectTypeValues;
    }

    @PostConstruct
    public void cargarListado() {
        listaUsuarios = usuariosService.listarUsuarios();
        listaUsuarios.sort(Comparator.comparing(UsuarioDTO::getNombre));
    }

    public void altaUsuario() {
        if (selectTypeValues.equals("Administrador")) {
            this.usuario.setTipo(TipoUsuario.ADMIN);
        } else if (selectTypeValues.equals("Autoridad")) {
            this.usuario.setTipo(TipoUsuario.AUTORIDAD);
        }
        try {
            System.out.println(usuario.getFechaNacimiento());
            if (usuario.getTipo() != null && usuariosService.registrarUsuario(this.usuario)) {
                FacesContext fc = FacesContext.getCurrentInstance();
                ExternalContext ec = fc.getExternalContext();
                ec.redirect("/CargaUy-web/admin/gestionUsuarios.xhtml");
                fc.responseComplete();
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario ingresado ya existe");
                PrimeFaces.current().dialog().showMessageDynamic(message);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void onRowEdit(RowEditEvent<UsuarioDTO> event) {
        UsuarioDTO usr = event.getObject();
        try {
            if (usr.getTipo() == TipoUsuario.ADMIN) {
                Administrador admin = usuariosService.getAdminByID(usr.getIdUsuario());
                //Cambiando campos actualizados 'actualizables'
                admin.setNombre(usr.getNombre());
                admin.setApellido(usr.getApellido());
                admin.setFechaNacimiento(Date.valueOf(usr.getFechaNacimiento()));
                admin.setUsername(usr.getUsername());
                usuariosService.actualizarDatos(admin);
            } else if (usr.getTipo() == TipoUsuario.AUTORIDAD) {
                Autoridad auto = usuariosService.getAutoByID(usr.getIdUsuario());
                //Cambiando campos actualizados
                auto.setNombre(usr.getNombre());
                auto.setApellido(usr.getApellido());
                auto.setFechaNacimiento(Date.valueOf(usr.getFechaNacimiento()));
                auto.setUsername(usr.getUsername());
                usuariosService.actualizarDatos(auto);
            }
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Usuario  actualizado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.toString());
            UsuarioDTO usraux = new UsuarioDTO(usuariosService.getUsuario(usr.getUsername()), usr.getTipo());
            event.getObject().setNombre(usraux.getNombre());
            PrimeFaces.current().dialog().showMessageDynamic(message);
        }
    }

    public void onRowCancel(RowEditEvent<UsuarioDTO> event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Información", "Edicion Cancelada.");
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    public void borrarUsuario(UsuarioDTO usr) throws UsuarioExisteException {
        if (usr.getTipo() == TipoUsuario.ADMIN) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario de tipo ADMINISTRADOR no puede ser eliminado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } else if (userManager.getCurrentUser().getIdUsuario() == usr.getIdUsuario()) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "El usuario seleccionado no puede ser eliminado.");
            PrimeFaces.current().dialog().showMessageDynamic(message);
        } else {
            usuariosService.eliminarUsuario(usr);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            try {
                ec.redirect("/CargaUy-web/admin/gestionUsuarios.xhtml");
                fc.responseComplete();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
