package tse.java.vistas;


import tse.java.entity.Administrador;
import tse.java.entity.Usuario;
import tse.java.enumerated.AuthResponse;
import tse.java.service.ISessionService;
import tse.java.service.IUsuariosService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Named("UserManager")
@SessionScoped
public class UserManager implements Serializable {

    @EJB
    ISessionService iSessionService;
    @EJB
    IUsuariosService iUsuariosService;
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
        AuthResponse response = iSessionService.iniciarSesion(username, password);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        if(response.equals(AuthResponse.OK)){
            setCurrentUser(iSessionService.getUsuarioLogueado(username));
            if(currentUser instanceof Administrador) {
                System.out.println("type is admin");
            } else {
                System.out.println("is autoridad");
            }
            try {
                ec.redirect("/CargaUy-web/admin/index.xhtml");
                fc.responseComplete();
            } catch (IOException e2) {
                throw new RuntimeException(e2);
            }
        }else {
            this.message = "Usuario inexistente o credenciales incorrectas";
        }
    }
//
//    @PostConstruct
//    public void registrarAdmin() throws ParseException {
//        Calendar cal = Calendar.getInstance();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        cal.setTime(sdf.parse("12-06-1996 00:00:00"));
//        Usuario admin = new Administrador("Rita", "Iglesias", cal, "rita.iglesias.adrover@gmail.com", "admin", "admin");
//        iUsuariosService.registrarUsuario(admin);
//    }

}


