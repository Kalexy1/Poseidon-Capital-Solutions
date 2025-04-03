package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.BidList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'entité {@link BidList}.
 * <p>
 * Elle fournit les opérations CRUD (Create, Read, Update, Delete) sur la table bidlist
 * en héritant de {@link JpaRepository}.
 * </p>
 */
@Repository
public interface BidListRepository extends JpaRepository<BidList, Integer> {

}
