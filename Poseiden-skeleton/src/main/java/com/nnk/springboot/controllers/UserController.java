package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur Spring MVC pour la gestion des utilisateurs.
 * <p>
 * Permet les opérations CRUD sur les entités {@link User} :
 * affichage, création, mise à jour et suppression.
 * </p>
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Affiche la liste de tous les utilisateurs.
     *
     * @param model le modèle dans lequel injecter les utilisateurs
     * @return la vue "user/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user/list";
    }

    /**
     * Affiche le formulaire pour ajouter un nouvel utilisateur.
     *
     * @param model le modèle contenant un nouvel objet {@link User}
     * @return la vue "user/add"
     */
    @GetMapping("/add")
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "user/add";
    }

    /**
     * Valide et enregistre un nouvel utilisateur.
     * Le mot de passe est encodé avant enregistrement.
     *
     * @param user   l'objet utilisateur à enregistrer
     * @param result le résultat de la validation
     * @param model  le modèle
     * @return redirection vers la liste ou retour au formulaire si erreur
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/add";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "redirect:/user/list";
    }

    /**
     * Affiche le formulaire de mise à jour pour un utilisateur.
     *
     * @param id    l'identifiant de l'utilisateur à modifier
     * @param model le modèle contenant les données utilisateur
     * @return la vue "user/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        User user = userService.getUserById(id);
        user.setPassword(""); // on vide le champ pour ne pas afficher le mot de passe hashé
        model.addAttribute("user", user);
        return "user/update";
    }

    /**
     * Valide et met à jour les informations d’un utilisateur.
     * Si le mot de passe est renseigné, il est encodé avant mise à jour.
     * Sinon, l’ancien mot de passe est conservé.
     *
     * @param id     l'identifiant de l'utilisateur
     * @param user   les données mises à jour
     * @param result le résultat de la validation
     * @param model  le modèle
     * @return redirection vers la liste ou retour au formulaire si erreur
     */
    @PostMapping("/update/{id}")
    public String updateUser(@PathVariable("id") Integer id, @Valid @ModelAttribute("user") User user,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "user/update";
        }

        // Récupérer l'utilisateur existant pour conserver le mot de passe si vide
        User existingUser = userService.getUserById(id);

        if (user.getPassword() == null || user.getPassword().isBlank()) {
            user.setPassword(existingUser.getPassword()); // conserver l'ancien mot de passe
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword())); // encoder le nouveau
        }

        userService.updateUser(id, user);
        return "redirect:/user/list";
    }

    /**
     * Supprime un utilisateur existant.
     *
     * @param id    l'identifiant de l'utilisateur à supprimer
     * @param model le modèle
     * @return redirection vers la liste des utilisateurs
     */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id, Model model) {
        userService.deleteUser(id);
        return "redirect:/user/list";
    }
}
