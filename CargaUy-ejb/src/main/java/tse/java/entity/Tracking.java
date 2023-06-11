package tse.java.entity;

import tse.java.dto.TrackingDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private LocalDateTime timeStamp;

    public Tracking() {
        super();
    }

    public Tracking(String matricula, String pais, String longitude, String latitude, LocalDateTime timeStamp) {
        super();
        this.matricula = matricula;
        this.pais = pais;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timeStamp = timeStamp;
    }

    public Tracking(TrackingDTO dto) {
        super();
        this.matricula = dto.getMatricula();
        this.pais = dto.getPais();
        this.longitude = dto.getLongitude();
        this.latitude = dto.getLatitude();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.timeStamp = LocalDateTime.parse(dto.getTimestamp(),dateTimeFormatter);
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

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TrackingDTO getDto() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String strDate = this.getTimeStamp().format(dateTimeFormatter);
        return new TrackingDTO(this.getId(), this.getMatricula(), this.getPais(), this.getLatitude(), this.getLongitude(), strDate);
    }


}
