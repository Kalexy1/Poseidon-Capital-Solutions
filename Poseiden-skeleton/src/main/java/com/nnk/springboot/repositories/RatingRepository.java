package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'entité {@link Rating}.
 * <p>
 * Fournit les opérations CRUD de base via {@link JpaRepository}
 * pour manipuler les objets {@link Rating} en base de données.
 * </p>
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, Integer> {

}
