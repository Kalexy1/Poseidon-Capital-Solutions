package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.CurvePoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'entité {@link CurvePoint}.
 * <p>
 * Elle hérite de {@link JpaRepository} pour fournir toutes les opérations CRUD
 * sur les objets {@link CurvePoint}.
 * </p>
 */
@Repository
public interface CurvePointRepository extends JpaRepository<CurvePoint, Integer> {

}
