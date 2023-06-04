package tse.java.persistance;

import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRubrosDAO {
    void altaRubro(RubroDTO dtr) throws RubroExisteException;
    RubroDTO buscarRubroPorId(Long id);
    RubroDTO buscarRubroPorNombre(String nom);
    List<RubroDTO> listarRubros();
    void borrarRubro(Long id);
    void modificarRubro(RubroDTO dtr) throws RubroExisteException;
}
