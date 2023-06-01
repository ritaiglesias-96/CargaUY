package tse.java.vistas;


import org.primefaces.PrimeFaces;
import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.service.ISessionService;
import tse.java.service.IUsuariosService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;

@Named("UserManager")
@SessionScoped
public class UserManager implements Serializable {

    @EJB
    ISessionService iSessionService;

    private static final HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

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
        if(response.equals(AuthResponse.OK)){
            loggedIn = true;
            setCurrentUser(iSessionService.getUsuarioLogueado(username));
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Welcome", username);
            try {
                ec.redirect("/CargaUy-web/admin/index.xhtml");
                fc.responseComplete();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }else {
            message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Loggin Error", "Invalid credentials");
            this.message = "Usuario inexistente o credenciales incorrectas";
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
        PrimeFaces.current().ajax().addCallbackParam("loggedIn", loggedIn);
    }

    public void logout() {
        System.out.println("asafsaf");
        System.out.println(getCurrentUser().getUsername());
        this.setCurrentUser(null);
        System.out.println(session.getAttribute("currentUser"));
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

}


