package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.domain.CurvePoint;
import java.util.List;

@Service
public class CurvePointService {
    @Autowired
    private CurvePointRepository curvePointRepository;

    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CurvePoint non trouvé"));
    }

    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePointDetails) {
        CurvePoint curvePoint = getCurvePointById(id);
        curvePoint.setCurveId(curvePointDetails.getCurveId());
        curvePoint.setTerm(curvePointDetails.getTerm());
        curvePoint.setValue(curvePointDetails.getValue());
        return curvePointRepository.save(curvePoint);
    }

    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
