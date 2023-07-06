package tse.java.service.impl;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.service.IPesajesService;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PesajesService implements IPesajesService {
    @Override
    public List<PesajeDTO> listarPesajesDeGuia(GuiaDeViajeDTO g, LocalDate fecha) {
        List<PesajeDTO> result = new ArrayList<PesajeDTO>();
        for(PesajeDTO pesaje:g.getPesajes()){
            LocalDateTime fechaPesaje = pesaje.getFecha();
            if(fecha.getYear()==fechaPesaje.getYear() && fecha.getMonth().getValue()==fechaPesaje.getMonth().getValue() && fecha.getDayOfMonth()==fechaPesaje.getDayOfMonth())
                result.add(pesaje);
        }
        return result;
    }
}
