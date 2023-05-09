package tse.java.service.impl;

import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;
import tse.java.service.IRubrosService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
@Stateless
public class RubrosService implements IRubrosService {

    @EJB
    IRubrosService rs;

    @Override
    public List<RubroDTO> listarRubros() {
        return rs.listarRubros();
    }

    @Override
    public void altaRubro(RubroDTO r) throws RubroExisteException {
        rs.altaRubro(r);
    }

    @Override
    public void modificarRubro(RubroDTO r) throws RubroExisteException {
        rs.modificarRubro(r);
    }

    @Override
    public void eliminarRubro(Long id) {
        rs.eliminarRubro(id);
    }

    @Override
    public RubroDTO buscarRubro(Long id) {
        return rs.buscarRubro(id);
    }
}
