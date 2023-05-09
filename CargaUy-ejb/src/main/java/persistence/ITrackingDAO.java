package persistence;

import entity.Tracking;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITrackingDAO {

    public List<Tracking> listAll(Integer startPosition, Integer maxResult);
    public Tracking findById(Long id);

    public void create(Tracking entity);

    public void update(Tracking entity);

    public void deleteById(Long id);
}
