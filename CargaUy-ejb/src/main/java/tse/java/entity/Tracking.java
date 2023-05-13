package tse.java.entity;

import tse.java.dto.TrackingDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tracking {
    @Id
    @SequenceGenerator(name = "tracking_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tracking_seq")
    private Long id;

    private String matricula;

    private String pais;

    private String longitude;

    private String latitude;

    private Date timeStamp;

    public Tracking() {
        super();
    }

    public Tracking(String matricula, String pais, String longitude, String latitude, Date timeStamp) {
        super();
        this.matricula = matricula;
        this.pais = pais;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeStamp = timeStamp;
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

    public TrackingDTO getDto() {
        return new TrackingDTO(this.getId(), this.getMatricula(), this.getLatitude(), this.getLongitude());
    }


}
