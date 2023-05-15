package tse.java.service.impl;

import tse.java.dto.GuiaDeViajeDTO;
import tse.java.dto.PesajeDTO;
import tse.java.service.IPesajesService;

import javax.ejb.Stateless;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class PesajesService implements IPesajesService {
    @Override
    public List<PesajeDTO> listarPesajesDeGuia(GuiaDeViajeDTO g, Date fecha) {
        List<PesajeDTO> result = new ArrayList<PesajeDTO>();
        LocalDate fechaparam = fecha.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        for(PesajeDTO pesaje:g.getPesajes()){
            LocalDateTime fechaPesaje = pesaje.getFecha();
            if(fechaparam.getYear()==fechaPesaje.getYear() && fechaparam.getMonth().getValue()==fechaPesaje.getMonth().getValue() && fechaparam.getDayOfMonth()==fechaPesaje.getDayOfMonth())
                result.add(pesaje);
        }
        return result;
    }
}
