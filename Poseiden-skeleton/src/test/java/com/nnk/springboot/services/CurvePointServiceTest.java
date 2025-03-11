package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CurvePointServiceTest {

    @Mock
    private CurvePointRepository curvePointRepository;

    @InjectMocks
    private CurvePointService curvePointService;

    private CurvePoint curvePoint;

    @BeforeEach
    void setUp() {
        curvePoint = new CurvePoint();
        curvePoint.setId(1);
        curvePoint.setCurveId(10);
        curvePoint.setTerm(2.5);
        curvePoint.setValue(100.0);
    }

    @Test
    void testGetAllCurvePoints() {
        when(curvePointRepository.findAll()).thenReturn(Arrays.asList(curvePoint));

        List<CurvePoint> curvePoints = curvePointService.getAllCurvePoints();

        assertNotNull(curvePoints);
        assertEquals(1, curvePoints.size());
        assertEquals(10, curvePoints.get(0).getCurveId());

        verify(curvePointRepository, times(1)).findAll();
    }

    @Test
    void testGetCurvePointById_CurvePointFound() {
        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));

        CurvePoint foundCurvePoint = curvePointService.getCurvePointById(1);

        assertNotNull(foundCurvePoint);
        assertEquals(10, foundCurvePoint.getCurveId());

        verify(curvePointRepository, times(1)).findById(1);
    }

    @Test
    void testGetCurvePointById_CurvePointNotFound() {
        when(curvePointRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> curvePointService.getCurvePointById(2));

        assertEquals("CurvePoint non trouvé", exception.getMessage());
        verify(curvePointRepository, times(1)).findById(2);
    }

    @Test
    void testSaveCurvePoint() {
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(curvePoint);

        CurvePoint savedCurvePoint = curvePointService.saveCurvePoint(curvePoint);

        assertNotNull(savedCurvePoint);
        assertEquals(10, savedCurvePoint.getCurveId());

        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void testUpdateCurvePoint() {
        CurvePoint updatedCurvePointDetails = new CurvePoint();
        updatedCurvePointDetails.setCurveId(20);
        updatedCurvePointDetails.setTerm(5.0);
        updatedCurvePointDetails.setValue(200.0);

        when(curvePointRepository.findById(1)).thenReturn(Optional.of(curvePoint));
        when(curvePointRepository.save(any(CurvePoint.class))).thenReturn(updatedCurvePointDetails);

        CurvePoint updatedCurvePoint = curvePointService.updateCurvePoint(1, updatedCurvePointDetails);

        assertNotNull(updatedCurvePoint);
        assertEquals(20, updatedCurvePoint.getCurveId());
        assertEquals(5.0, updatedCurvePoint.getTerm());

        verify(curvePointRepository, times(1)).findById(1);
        verify(curvePointRepository, times(1)).save(curvePoint);
    }

    @Test
    void testDeleteCurvePoint() {
        doNothing().when(curvePointRepository).deleteById(1);

        curvePointService.deleteCurvePoint(1);

        verify(curvePointRepository, times(1)).deleteById(1);
    }
}
