package tse.java.persistance;

import tse.java.dto.CiudadanoDTO;

import javax.ejb.Local;
import java.util.ArrayList;

@Local
public interface ICiudadanoDAO {
    ArrayList<CiudadanoDTO> listarCiudadanos();
}
