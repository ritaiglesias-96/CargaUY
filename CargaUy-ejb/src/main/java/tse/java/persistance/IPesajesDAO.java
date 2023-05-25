package tse.java.persistance;

import tse.java.dto.PesajeDTO;
import tse.java.dto.RubroDTO;
import tse.java.exception.RubroExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface IPesajesDAO {
    void altaPesaje(PesajeDTO p);
    PesajeDTO buscarPesaje(Long id);
    List<PesajeDTO> listarPesajes();
    void borrarPesaje(Long id);
}
