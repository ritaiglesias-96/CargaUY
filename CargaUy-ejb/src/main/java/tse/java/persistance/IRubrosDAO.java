package tse.java.persistance;

import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRubrosDAO {
    public void altaRubro(RubroDTO dtr) throws RubroExisteException;
    public RubroDTO buscarRubroPorId(Long id);
    public RubroDTO buscarRubroPorNombre(String nom);
    public List<RubroDTO> listarRubros();
    public void borrarRubro(Long id);
    public void modificarRubro(RubroDTO dtr) throws RubroExisteException;
}
