package tse.java.api;

import tse.java.dto.CiudadanoFrontDTO;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named("SessionBean")
@SessionScoped
public class SessionBean implements Serializable {
    private static final long serialVersionUID = -4166151468721069760L;

    private String idToken;
    private String jwt;

    private CiudadanoFrontDTO currentUser;

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public CiudadanoFrontDTO getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(CiudadanoFrontDTO currentUser) {
        this.currentUser = currentUser;
    }


}
