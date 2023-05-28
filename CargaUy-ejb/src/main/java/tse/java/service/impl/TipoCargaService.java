package tse.java.service.impl;

import tse.java.dto.TipoCargaDTO;
import tse.java.exception.TipoCargaExisteException;
import tse.java.persistance.ITipoCargaDAO;
import tse.java.service.ITipoCargaService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;

@Stateless
@Named("tipoCargaService")
public class TipoCargaService implements ITipoCargaService {

    @EJB
    ITipoCargaDAO tc;


    @Override
    public List<TipoCargaDTO> listarTipoCarga() {
        return tc.listarTipoCarga();
    }

    @Override
    public void altaTipoCarga(TipoCargaDTO dttc) throws TipoCargaExisteException {
        tc.altaTipoCarga(dttc);
    }

    @Override
    public void modificarTipoCarga(TipoCargaDTO dttc) throws TipoCargaExisteException {
        tc.modificarTipoCarga(dttc);
    }

    @Override
    public void eliminarTipoCarga(Long id) {
        tc.borrarTipoCarga(id);
    }

    @Override
    public TipoCargaDTO buscarTipoCarga(Long id) {
        return tc.buscarTipoCargaPorId(id);
    }



}
