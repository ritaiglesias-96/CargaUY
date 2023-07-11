package tse.java.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import tse.java.dto.TrackingDTO;
import tse.java.entity.Tracking;
import tse.java.persistance.ITrackingDAO;
import tse.java.service.ITrackingService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrackingServiceTest {

    @InjectMocks
    TrackingService trackingService;

    @Mock
    private ITrackingDAO trackingDAO;

    @Test
    public void testListAll() {
        List<Tracking> trackings = new ArrayList<>();
        Tracking tracking = new Tracking();
        Tracking tracking1 = new Tracking();
        trackings.add(tracking);
        trackings.add(tracking1);
        int a = 0, b=1;

        when(trackingDAO.listAll(a, b)).thenReturn(trackings);

        List<Tracking> trackingsResult = trackingService.listAll(a, b);

        assertEquals(trackings.size(),trackingsResult.size());
    }

    @Test
    public void testFind() {
        String matricula = "ADASD", pais = "Uru";
        TrackingDTO trackingDTO = new TrackingDTO();
        trackingDTO.setMatricula(matricula);
        trackingDTO.setPais(pais);
        when(trackingDAO.find(matricula,pais)).thenReturn(trackingDTO);

        TrackingDTO trackingDTOResult = trackingService.find(matricula, pais);

        assertEquals(trackingDTO.getPais(), trackingDTOResult.getPais());

    }

    @Test
    public void testCreate() {
        Tracking tracking = new Tracking();
        tracking.setPais("Uru");

        trackingService.create(tracking);

        Mockito.verify(trackingDAO).create(tracking);
    }

    @Test
    public void testUpdate() {
        Tracking tracking = new Tracking();
        trackingService.update(tracking);
        Mockito.verify(trackingDAO).update(tracking);
    }
}