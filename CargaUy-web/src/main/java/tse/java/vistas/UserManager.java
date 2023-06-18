package tse.java.vistas;


import org.primefaces.PrimeFaces;
import tse.java.entity.Administrador;
import tse.java.entity.Autoridad;
import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.service.ISessionService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

@Named("UserManager")
@SessionScoped
public class UserManager implements Serializable {
    private static final long serialVersionUID = -4166151468721069760L;
    @EJB
    ISessionService iSessionService;

    private static HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

    private String username;
    private String password;
    private String message;
    private Usuario currentUser;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Usuario getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Usuario currentUser) {
        this.currentUser = currentUser;
    }


    public void login() {
        FacesMessage message = null;
        boolean loggedIn = false;
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        AuthResponse response = iSessionService.iniciarSesion(username, password);
        if (response.equals(AuthResponse.OK)) {
            loggedIn = true;
            setCurrentUser(iSessionService.getUsuarioLogueado(username));
            session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("currentUser", currentUser);
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            try {
                if (currentUser instanceof Administrador)
                    ec.redirect("/CargaUy-web/admin/index.xhtml");
                else
                    ec.redirect("/CargaUy-web/autoridad/index.xhtml");
                fc.responseComplete();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        } else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            this.message = "Usuario inexistente o credenciales incorrectas";
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

    public void logout() {
        System.out.println(getCurrentUser().getUsername());
        iSessionService.cerrarSesion(getCurrentUser().getUsername());
        this.setCurrentUser(null);
        session.removeAttribute("currentUser");
        try {
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("/CargaUy-web/");
            fc.responseComplete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public Map<String, String> getOpciones() {
        TreeMap<String, String> opciones = new TreeMap<String, String>();

        if (currentUser != null) {
            if (currentUser instanceof Administrador) {
                opciones.put("Gestión de usuarios", getDirVirtual(currentUser) + "gestionUsuarios.xhtml");
                opciones.put("Gestión de rubros", getDirVirtual(currentUser) + "gestionRubros.xhtml");
                opciones.put("Gestión de tipos de carga", getDirVirtual(currentUser) + "gestionTipoCarga.xhtml");
                opciones.put("Gestión de roles ciudadanos", getDirVirtual(currentUser) + "asignarRoles.xhtml");
            } else {
                if (currentUser instanceof Autoridad) {
                    opciones.put("Dashboard", getDirVirtual(currentUser) + "dashboardAutoridad.xhtml");
                }
            }
        }

        return opciones;
    }


    private static String getDirVirtual(Usuario currentUser) {
        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        if (origRequest.getRequestURI().contains("admin") || origRequest.getRequestURI().contains("autoridad"))
            return "";

        if (currentUser instanceof Administrador)
            return "admin/";
        else if (currentUser instanceof Autoridad)
            return "autoridad/";

        return "error";


    }

}


