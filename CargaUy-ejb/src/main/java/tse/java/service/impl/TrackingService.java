package tse.java.service.impl;

import tse.java.dto.TrackingDTO;
import tse.java.entity.Tracking;
import tse.java.persistance.ITrackingDAO;
import tse.java.service.ITrackingService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@Named("trackingService")
public class TrackingService implements ITrackingService {

    @EJB
    ITrackingDAO td;

    @Override
    public List<Tracking> listAll(Integer startPosition, Integer maxResult) {
        return td.listAll(startPosition, maxResult);
    }

    @Override
    public TrackingDTO find(String matricula, String pais) {
        return td.find(matricula, pais);
    }

    @Override
    public void create(Tracking entity) {
        td.create(entity);
    }

    @Override
    public void update(Tracking entity) {
        td.update(entity);
    }
}