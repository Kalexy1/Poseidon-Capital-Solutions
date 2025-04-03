package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.domain.CurvePoint;

import java.util.List;

/**
 * Service pour la gestion des entités {@link CurvePoint}.
 * <p>
 * Fournit les opérations de base pour manipuler les courbes dans l'application.
 * </p>
 */
@Service
public class CurvePointService {

    @Autowired
    private CurvePointRepository curvePointRepository;

    /**
     * Récupère la liste de tous les {@link CurvePoint} en base de données.
     *
     * @return une liste d'objets CurvePoint
     */
    public List<CurvePoint> getAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    /**
     * Récupère un {@link CurvePoint} à partir de son identifiant.
     *
     * @param id identifiant du CurvePoint
     * @return le CurvePoint correspondant
     * @throws RuntimeException si le CurvePoint n'est pas trouvé
     */
    public CurvePoint getCurvePointById(Integer id) {
        return curvePointRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CurvePoint non trouvé"));
    }

    /**
     * Enregistre un nouveau {@link CurvePoint}.
     *
     * @param curvePoint l'objet à sauvegarder
     * @return le CurvePoint sauvegardé
     */
    public CurvePoint saveCurvePoint(CurvePoint curvePoint) {
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Met à jour un {@link CurvePoint} existant avec de nouvelles valeurs.
     *
     * @param id identifiant du CurvePoint à mettre à jour
     * @param curvePointDetails nouvelles données du CurvePoint
     * @return le CurvePoint mis à jour
     */
    public CurvePoint updateCurvePoint(Integer id, CurvePoint curvePointDetails) {
        CurvePoint curvePoint = getCurvePointById(id);
        curvePoint.setCurveId(curvePointDetails.getCurveId());
        curvePoint.setTerm(curvePointDetails.getTerm());
        curvePoint.setValue(curvePointDetails.getValue());
        return curvePointRepository.save(curvePoint);
    }

    /**
     * Supprime un {@link CurvePoint} à partir de son identifiant.
     *
     * @param id identifiant du CurvePoint à supprimer
     */
    public void deleteCurvePoint(Integer id) {
        curvePointRepository.deleteById(id);
    }
}
