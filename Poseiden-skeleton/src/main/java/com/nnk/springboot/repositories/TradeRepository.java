package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.Trade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'entité {@link Trade}.
 * <p>
 * Fournit les opérations CRUD de base pour gérer les entités Trade,
 * représentant les transactions financières dans l'application.
 * </p>
 */
@Repository
public interface TradeRepository extends JpaRepository<Trade, Integer> {

}
