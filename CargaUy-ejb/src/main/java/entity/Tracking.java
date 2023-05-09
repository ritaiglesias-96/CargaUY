package entity;

import dto.TrackingDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Tracking {
    @Id
    @SequenceGenerator(name = "tracking_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tracking_seq")
    private Long id;

    private String truckId;
    //matricula y pais

    private Number longitude;

    private Number latitude;

    private Date timeStamp;

    /*@OneToMany(mappedBy = "organizacionPropuso", cascade = { CascadeType.PERSIST, CascadeType.REFRESH })
    private List<Iniciativa> iniciativasPropuestas = new ArrayList<Iniciativa>();*/

    public Tracking() {
        super();
    }

    public Tracking(String truckId, Number longitude, Number latitude) {
        super();
        this.truckId = truckId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTruckId() {
        return truckId;
    }

    public void setTruckId(String truckId) {
        this.truckId = truckId;
    }

    public Number getLongitude() {
        return longitude;
    }

    public void setLongitude(Number longitude) {
        this.longitude = longitude;
    }

    public Number getLatitude() {
        return latitude;
    }

    public void setLatitude(Number latitude) {
        this.latitude = latitude;
    }

    public TrackingDTO getDto() {
        return new TrackingDTO(this.getId(), this.getTruckId(), this.getLatitude(), this.getLongitude());
    }


}
