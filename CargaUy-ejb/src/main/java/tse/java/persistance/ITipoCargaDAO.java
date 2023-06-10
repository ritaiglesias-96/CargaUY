package tse.java.persistance;

import tse.java.dto.RubroDTO;
import tse.java.dto.TipoCargaDTO;
import tse.java.exception.RubroExisteException;
import tse.java.exception.TipoCargaExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITipoCargaDAO {

    void altaTipoCarga(TipoCargaDTO tdt) throws TipoCargaExisteException;
    TipoCargaDTO buscarTipoCargaPorId(Long id);
    TipoCargaDTO buscarTipoCargaPorNombre(String nombre);
    List<TipoCargaDTO> listarTipoCarga();
    void borrarTipoCarga(Long id);
    void modificarTipoCarga(TipoCargaDTO tdt) throws TipoCargaExisteException;
}
