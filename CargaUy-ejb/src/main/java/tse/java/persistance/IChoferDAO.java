package tse.java.persistance;

import tse.java.dto.ChoferDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IChoferDAO {
    List<Chofer> findAll();
    void agregarChofer(Chofer chofer);
    void modificarChofer(Chofer chofer);
    void eliminiarChofer(Chofer chofer);
    ChoferDTO buscarChoferPorCedula(String cedula);


}
