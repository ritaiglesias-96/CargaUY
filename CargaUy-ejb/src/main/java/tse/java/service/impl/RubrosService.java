package tse.java.service.impl;

import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;
import tse.java.persistance.IRubrosDAO;
import tse.java.service.IRubrosService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
@Stateless
public class RubrosService implements IRubrosService {

    @EJB
    IRubrosDAO rd;

    @Override
    public List<RubroDTO> listarRubros() {
        return rd.listarRubros();
    }

    @Override
    public void altaRubro(RubroDTO r) throws RubroExisteException {
        rd.altaRubro(r);
    }

    @Override
    public void modificarRubro(RubroDTO r) throws RubroExisteException {
        rd.modificarRubro(r);
    }

    @Override
    public void eliminarRubro(Long id) {
        rd.borrarRubro(id);
    }

    @Override
    public RubroDTO buscarRubro(Long id) {
        return rd.buscarRubroPorId(id);
    }
}
