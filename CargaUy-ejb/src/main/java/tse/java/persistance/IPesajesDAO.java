package tse.java.persistance;

import tse.java.dto.PesajeDTO;
import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPesajesDAO {
    public void altaPesaje(PesajeDTO p);
    public PesajeDTO buscarPesaje(Long id);
    public List<PesajeDTO> listarPesajes();
    public void borrarPesaje(Long id);
    public Long getLastId();
}
