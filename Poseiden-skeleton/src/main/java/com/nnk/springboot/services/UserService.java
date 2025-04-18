package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.UserRepository;
import com.nnk.springboot.domain.User;
import java.util.List;
import java.util.Optional;

/**
 * Service de gestion des utilisateurs.
 * <p>
 * Fournit les opérations de récupération, création, mise à jour, suppression
 * et encodage du mot de passe des utilisateurs.
 * </p>
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Récupère tous les utilisateurs.
     *
     * @return la liste de tous les utilisateurs
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Récupère un utilisateur par son identifiant.
     *
     * @param id identifiant de l'utilisateur
     * @return l'utilisateur correspondant
     * @throws RuntimeException si aucun utilisateur n'est trouvé
     */
    public User getUserById(Integer id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
    }

    /**
     * Vérifie si un nom d'utilisateur est déjà utilisé.
     *
     * @param username le nom d'utilisateur à vérifier
     * @return {@code true} si le nom existe, sinon {@code false}
     */
    public boolean usernameExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    /**
     * Recherche un utilisateur à partir de son nom d'utilisateur.
     *
     * @param username le nom d'utilisateur
     * @return un {@link Optional} contenant l'utilisateur s'il existe
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Enregistre un nouvel utilisateur avec un mot de passe encodé.
     *
     * @param user l'utilisateur à enregistrer
     * @return l'utilisateur enregistré
     */
    public User saveUser(User user) {
    	System.out.println("Mot de passe avant encodage : " + user.getPassword());
    	user.setPassword(passwordEncoder.encode(user.getPassword()));
    	System.out.println("Mot de passe après encodage : " + user.getPassword());

        return userRepository.save(user);
    }

    /**
     * Met à jour les informations d'un utilisateur existant.
     *
     * @param id identifiant de l'utilisateur à mettre à jour
     * @param userDetails les nouvelles données utilisateur
     * @return l'utilisateur mis à jour
     */
    public User updateUser(Integer id, User userDetails) {
        User user = getUserById(id);
        user.setUsername(userDetails.getUsername());
        user.setRole(userDetails.getRole());
        if (!userDetails.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(userDetails.getPassword()));
        }
        return userRepository.save(user);
    }

    /**
     * Supprime un utilisateur à partir de son identifiant.
     *
     * @param id identifiant de l'utilisateur à supprimer
     */
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
