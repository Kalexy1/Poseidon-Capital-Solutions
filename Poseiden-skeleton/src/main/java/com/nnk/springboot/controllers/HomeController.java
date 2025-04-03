package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Contrôleur pour gérer les pages d'accueil de l'application.
 * <p>
 * Fournit les routes pour la page d'accueil générale et la redirection de la page d'accueil administrateur.
 * </p>
 */
@Controller
public class HomeController {

    /**
     * Affiche la page d'accueil principale de l'application.
     *
     * @param model le modèle utilisé pour envoyer des données à la vue
     * @return le nom de la vue Thymeleaf "home"
     */
    @RequestMapping("/")
    public String home(Model model) {
        return "home";
    }

    /**
     * Redirige les administrateurs vers la page de la liste des BidList.
     *
     * @param model le modèle utilisé pour la redirection
     * @return une redirection vers "/bidList/list"
     */
    @RequestMapping("/admin/home")
    public String adminHome(Model model) {
        return "redirect:/bidList/list";
    }
}
