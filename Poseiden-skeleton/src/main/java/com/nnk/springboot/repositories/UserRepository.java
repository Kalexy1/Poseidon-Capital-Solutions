package com.nnk.springboot.repositories;

import com.nnk.springboot.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de repository pour l'entité {@link User}.
 * <p>
 * Fournit les opérations de persistance pour gérer les utilisateurs de l'application,
 * incluant une méthode personnalisée pour retrouver un utilisateur par son nom d'utilisateur.
 * </p>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    /**
     * Recherche un utilisateur par son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur
     * @return un {@link Optional} contenant l'utilisateur s'il est trouvé, ou vide sinon
     */
    Optional<User> findByUsername(String username);
}
