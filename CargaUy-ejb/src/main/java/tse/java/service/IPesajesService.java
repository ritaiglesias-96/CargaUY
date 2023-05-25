package tse.java.service;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;

import javax.ejb.Local;
import java.util.Date;
import java.util.List;

@Local
public interface IPesajesService {
    List<PesajeDTO> listarPesajesDeGuia(GuiaDeViajeDTO g, Date fecha);
}
