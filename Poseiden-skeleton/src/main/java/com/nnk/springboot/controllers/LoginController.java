package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Contrôleur de gestion de l'authentification et des erreurs.
 * <p>
 * Gère l'affichage de la page de connexion, la redirection après déconnexion
 * et l'affichage d'une page d'erreur personnalisée pour les accès non autorisés.
 * </p>
 */
@Controller
public class LoginController {

    /**
     * Affiche la page de connexion personnalisée.
     *
     * @return le nom de la vue Thymeleaf "login"
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * Redirige l'utilisateur vers la page de connexion après déconnexion.
     *
     * @return une redirection vers "/login?logout"
     */
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login?logout";
    }

    /**
     * Affiche une page d'erreur personnalisée en cas d'accès non autorisé.
     *
     * @return une vue ModelAndView avec un message d'erreur et le template "403"
     */
    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("errorMsg", "Vous n'êtes pas autorisé à accéder à cette page.");
        mav.setViewName("403");
        return mav;
    }
}
