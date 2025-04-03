package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur Spring MVC pour la gestion des entités {@link RuleName}.
 * <p>
 * Permet de gérer les opérations CRUD : affichage de la liste,
 * ajout, mise à jour et suppression des règles de validation.
 * </p>
 */
@Controller
@RequestMapping("/ruleName")
public class RuleNameController {

    @Autowired
    private RuleNameService ruleNameService;

    /**
     * Affiche la liste de toutes les règles.
     *
     * @param model le modèle contenant les règles
     * @return la vue Thymeleaf "ruleName/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        List<RuleName> rules = ruleNameService.getAllRules();
        model.addAttribute("ruleNames", rules);
        return "ruleName/list";
    }

    /**
     * Affiche le formulaire d'ajout d'une nouvelle règle.
     *
     * @param model le modèle contenant une nouvelle instance de {@link RuleName}
     * @return la vue "ruleName/add"
     */
    @GetMapping("/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    /**
     * Valide et enregistre une nouvelle règle.
     *
     * @param ruleName l'objet {@link RuleName} à valider et enregistrer
     * @param result   les résultats de validation
     * @param model    le modèle
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.saveRule(ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Affiche le formulaire de mise à jour d'une règle existante.
     *
     * @param id    l'identifiant de la règle à modifier
     * @param model le modèle contenant la règle existante
     * @return la vue "ruleName/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    /**
     * Valide et met à jour une règle existante.
     *
     * @param id       l'identifiant de la règle
     * @param ruleName la nouvelle valeur de la règle
     * @param result   les résultats de validation
     * @param model    le modèle
     * @return redirection vers la liste ou retour au formulaire en cas d'erreur
     */
    @PostMapping("/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleName ruleName,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.updateRule(id, ruleName);
        return "redirect:/ruleName/list";
    }

    /**
     * Supprime une règle existante.
     *
     * @param id    l'identifiant de la règle à supprimer
     * @param model le modèle
     * @return redirection vers la liste des règles
     */
    @GetMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRule(id);
        return "redirect:/ruleName/list";
    }
}
