package tse.java.dto;

import java.io.Serializable;

public class TrackingDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String matricula;

    //private String Pais;

    private String longitude;

    private String latitude;

    public TrackingDTO(Long id) {
        this.id = id;
    }

    public TrackingDTO(Long id, String matricula, String longitude, String latitude) {
        this.id = id;
        this.matricula = matricula;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public TrackingDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String truckId) {
        this.matricula = matricula;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
}
