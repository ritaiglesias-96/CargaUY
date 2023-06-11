package tse.java.service;

import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRubrosService {
    List<RubroDTO> listarRubros();
    void altaRubro(RubroDTO r) throws RubroExisteException;
    void modificarRubro(RubroDTO r) throws RubroExisteException;
    void eliminarRubro(Long id);
    RubroDTO buscarRubro(Long id);
}
