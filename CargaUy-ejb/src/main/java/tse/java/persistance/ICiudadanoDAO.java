package tse.java.persistance;

import tse.java.dto.CiudadanoDTO;
import tse.java.entity.Ciudadano;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface ICiudadanoDAO {
    ArrayList<CiudadanoDTO> listarCiudadanos();

    Ciudadano buscarCiudadanoPorId(int id);
}
