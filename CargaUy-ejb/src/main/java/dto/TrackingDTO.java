package dto;

import java.io.Serializable;

public class TrackingDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String truckId;

    private Number longitude;

    private Number latitude;

    public TrackingDTO(Long id) {
        this.id = id;
    }

    public TrackingDTO(Long id, String truckId, Number longitude, Number latitude) {
        this.id = id;
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
}
