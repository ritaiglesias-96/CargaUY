package tse.java.persistance;


import tse.java.dto.AsignacionDTO;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IAsignacionDAO {

    public Long altaAsignacion(AsignacionDTO a);
    public AsignacionDTO buscarAsignacion(Long id);
    public void modificarAsignacion(AsignacionDTO a);
    public List<AsignacionDTO> listarAsignaciones();
    public void borrarAsignacion(Long id);
}
