package tse.java.dto;

public class CiudadanoJwtDTO {

    private String jwt;

    private String cedula;

    private String idToken;


    public CiudadanoJwtDTO() {
    }
    public CiudadanoJwtDTO(String jwt, String cedula,String idToken) {
        this.jwt = jwt;
        this.cedula = cedula;
        this.idToken = idToken;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

}
