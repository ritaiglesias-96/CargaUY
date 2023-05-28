package tse.java.service;

import tse.java.dto.TipoCargaDTO;
import tse.java.exception.TipoCargaExisteException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ITipoCargaService {
    List<TipoCargaDTO> listarTipoCarga();
    void altaTipoCarga(TipoCargaDTO tcdt) throws TipoCargaExisteException;

    void modificarTipoCarga(TipoCargaDTO tcdt) throws TipoCargaExisteException;

    void eliminarTipoCarga(Long id );
    TipoCargaDTO buscarTipoCarga(Long id);
}
