package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CurvePointTests {

    @Autowired
    private CurvePointRepository curvePointRepository;

    private CurvePoint curvePoint;

    @BeforeEach
    public void setup() {
        curvePoint = new CurvePoint(10, 10d, 30d);
        curvePoint = curvePointRepository.save(curvePoint);
    }

    @AfterEach
    public void cleanup() {
        curvePointRepository.deleteAll();
    }

    @Test
    public void testSaveCurvePoint() {
        assertNotNull(curvePoint.getId());
        assertEquals(10, curvePoint.getCurveId());
    }

    @Test
    public void testUpdateCurvePoint() {
        curvePoint.setCurveId(20);
        curvePoint = curvePointRepository.save(curvePoint);
        assertEquals(20, curvePoint.getCurveId());
    }

    @Test
    public void testFindAllCurvePoints() {
        List<CurvePoint> listResult = curvePointRepository.findAll();
        assertFalse(listResult.isEmpty());
    }

    @Test
    public void testDeleteCurvePoint() {
        Integer id = curvePoint.getId();
        curvePointRepository.delete(curvePoint);
        Optional<CurvePoint> curvePointList = curvePointRepository.findById(id);
        assertFalse(curvePointList.isPresent());
    }
}
