package tse.java.persistance;

import tse.java.entity.Administrador;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAdministradorDAO {
    void persist(Administrador administrador);

    void merge(Administrador administrador);

    List<Administrador> findAll();

    void delete(Administrador administrador);

    Administrador findById(Integer id);

}
