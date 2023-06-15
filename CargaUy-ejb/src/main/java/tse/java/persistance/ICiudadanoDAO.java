package tse.java.persistance;

import tse.java.dto.CiudadanoDTO;
import tse.java.entity.Chofer;
import tse.java.entity.Ciudadano;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface ICiudadanoDAO {
    ArrayList<CiudadanoDTO> listarCiudadanos();
    void agregarCiudadano(Ciudadano ciudadano);
    void modificarCiudadano(Ciudadano ciudadano);
    void eliminiarCiudadano(int id);
    Ciudadano buscarCiudadanoPorId(int id);

    Ciudadano buscarCiudadanoPorCedula(String cedula);
}
