package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur pour la gestion des erreurs.
 * Gère l'affichage de la page d'erreur d'accès refusé.
 */
@Controller
public class ErrorController {

    /**
     * Méthode pour afficher la page d'erreur d'accès refusé.
     * @return le nom de la vue pour l'erreur d'accès refusé
     */
    @RequestMapping("/403")
    public String accessDenied() {
        return "403";
    }
}
