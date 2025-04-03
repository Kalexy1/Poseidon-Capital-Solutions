package com.nnk.springboot.services;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Implémentation personnalisée du service {@link UserDetailsService} pour l'authentification utilisateur.
 * <p>
 * Cette classe est utilisée par Spring Security pour récupérer les informations
 * d'un utilisateur à partir de son nom d'utilisateur (username) et construire un objet {@link UserDetails}.
 * </p>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    /**
     * Repository pour accéder aux données des utilisateurs.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Charge un utilisateur à partir de son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur utilisé pour l'authentification
     * @return un objet {@link UserDetails} contenant les informations de l'utilisateur
     * @throws UsernameNotFoundException si aucun utilisateur n'est trouvé avec le nom d'utilisateur fourni
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Tentative de connexion pour l'utilisateur : " + username);

        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            System.out.println("Utilisateur non trouvé !");
            throw new UsernameNotFoundException("Utilisateur non trouvé : " + username);
        }

        User foundUser = userOpt.get();
        System.out.println("Utilisateur trouvé : " + foundUser.getUsername() + " | Rôle : " + foundUser.getRole());
        System.out.println("Mot de passe enregistré (hashé) : " + foundUser.getPassword());

        // Spring Security ajoute automatiquement le préfixe "ROLE_" au rôle défini ici.
        return org.springframework.security.core.userdetails.User.builder()
                .username(foundUser.getUsername())
                .password(foundUser.getPassword())
                .roles(foundUser.getRole()) // Exemple : "ADMIN" devient "ROLE_ADMIN"
                .build();
    }
}
