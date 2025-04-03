package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.RuleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'entité {@link RuleName}.
 * <p>
 * Fournit les opérations CRUD de base pour accéder et gérer
 * les règles métier (RuleName) dans la base de données.
 * </p>
 */
@Repository
public interface RuleNameRepository extends JpaRepository<RuleName, Integer> {

}
