package tse.java.persistance;

import tse.java.dto.ChoferDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;
import tse.java.entity.Empresa;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IChoferDAO {
    List<Chofer> findAll();
    void agregarChofer(Chofer chofer);
    void modificarChofer(Chofer chofer);
    void eliminiarChofer(Chofer chofer);
    void asignarEmpresaChofer(int id, Empresa empresa);
    void eliminarEmpresaChofer(int id, Empresa empresa);
    ChoferDTO buscarChoferPorCedula(String cedula);


}
