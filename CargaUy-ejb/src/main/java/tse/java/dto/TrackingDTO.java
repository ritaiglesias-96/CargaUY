package tse.java.dto;

import javax.ejb.Local;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class TrackingDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;

    private String matricula;

    private String pais;

    private String longitude;

    private String latitude;

    private String timestamp;
    public TrackingDTO(Long id) {
        this.id = id;
    }

    public TrackingDTO(Long id, String matricula, String pais, String longitude, String latitude, String timestamp) {
        this.id = id;
        this.matricula = matricula;
        this.pais = pais;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
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

    public void setMatricula(String matricula) {
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimeStamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
