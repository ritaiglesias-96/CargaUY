package tse.java.service;

import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IRubrosService {
    public List<RubroDTO> listarRubros();
    public void altaRubro(RubroDTO r) throws RubroExisteException;
    public void modificarRubro(RubroDTO r) throws RubroExisteException;
    public void eliminarRubro(Long id);
    public RubroDTO buscarRubro(Long id);
}
