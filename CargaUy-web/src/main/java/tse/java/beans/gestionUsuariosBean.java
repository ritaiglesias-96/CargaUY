package tse.java.beans;

import org.primefaces.PrimeFaces;
import tse.java.dto.UsuarioDTO;
import tse.java.enumerated.TipoUsuario;
import tse.java.service.IUsuariosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;


@Named("gestionarUsuarios")
@RequestScoped
public class gestionUsuariosBean {

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
}
