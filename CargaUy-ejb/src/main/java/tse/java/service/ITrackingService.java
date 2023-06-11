package tse.java.service;

import tse.java.dto.TrackingDTO;
import tse.java.entity.Tracking;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITrackingService {


    public List<Tracking> listAll(Integer startPosition, Integer maxResult);
    public TrackingDTO find(String matricula, String pais);

    public void create(Tracking entity);

    public void update(Tracking entity);

}