package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur Spring MVC pour la gestion des entités {@link Rating}.
 * <p>
 * Permet de gérer les opérations CRUD : affichage de la liste,
 * ajout, mise à jour et suppression d'une notation financière.
 * </p>
 */
@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private RatingService ratingService;

    /**
     * Affiche la liste de toutes les notations (ratings).
     *
     * @param model le modèle contenant la liste des notations
     * @return la vue Thymeleaf "rating/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        List<Rating> ratings = ratingService.getAllRatings();
        model.addAttribute("ratings", ratings);
        return "rating/list";
    }

    /**
     * Affiche le formulaire d'ajout d'une nouvelle notation.
     *
     * @param model le modèle contenant un nouvel objet {@link Rating}
     * @return la vue "rating/add"
     */
    @GetMapping("/add")
    public String addRatingForm(Model model) {
        model.addAttribute("rating", new Rating());
        return "rating/add";
    }

    /**
     * Valide et enregistre une nouvelle notation.
     *
     * @param rating l'objet {@link Rating} soumis par le formulaire
     * @param result le résultat de la validation
     * @param model  le modèle
     * @return la redirection vers la liste ou le formulaire si erreurs
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("rating") Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/add";
        }
        ratingService.saveRating(rating);
        return "redirect:/rating/list";
    }

    /**
     * Affiche le formulaire de mise à jour d'une notation existante.
     *
     * @param id    l'identifiant de la notation à modifier
     * @param model le modèle contenant la notation existante
     * @return la vue "rating/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id);
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    /**
     * Valide et met à jour une notation existante.
     *
     * @param id     l'identifiant de la notation
     * @param rating la notation modifiée
     * @param result le résultat de la validation
     * @param model  le modèle
     * @return la redirection vers la liste ou le formulaire si erreurs
     */
    @PostMapping("/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid @ModelAttribute("rating") Rating rating,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "rating/update";
        }
        ratingService.updateRating(id, rating);
        return "redirect:/rating/list";
    }

    /**
     * Supprime une notation existante.
     *
     * @param id    l'identifiant de la notation à supprimer
     * @param model le modèle
     * @return la redirection vers la liste des notations
     */
    @GetMapping("/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        ratingService.deleteRating(id);
        return "redirect:/rating/list";
    }
}
