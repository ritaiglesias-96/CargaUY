package tse.java.persistance;

import tse.java.entity.Autoridad;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAutoridadDAO {

    void persist(Autoridad autoridad);

    void merge(Autoridad autoridad);

    List<Autoridad> findAll();

    void delete(Autoridad autoridad);

    Autoridad findById(Integer id);

}
