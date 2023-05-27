package tse.java.persistance;

import tse.java.entity.Funcionario;
import tse.java.entity.Responsable;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IResponsableDAO {
    List<Responsable> findAll();
    void agregarResponsable(Responsable responsable);
    void modificarResponsable(Responsable responsable);
    void eliminiarResponsable(Responsable responsable);

}
