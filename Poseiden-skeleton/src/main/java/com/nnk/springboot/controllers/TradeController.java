package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur Spring MVC pour la gestion des entités {@link Trade}.
 * <p>
 * Permet de gérer les opérations CRUD : affichage, ajout,
 * mise à jour et suppression des transactions.
 * </p>
 */
@Controller
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    /**
     * Affiche la liste des transactions.
     *
     * @param model le modèle contenant la liste des {@link Trade}
     * @return la vue "trade/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        List<Trade> trades = tradeService.getAllTrades();
        model.addAttribute("trades", trades);
        return "trade/list";
    }

    /**
     * Affiche le formulaire d’ajout d’une nouvelle transaction.
     *
     * @param model le modèle contenant un nouvel objet {@link Trade}
     * @return la vue "trade/add"
     */
    @GetMapping("/add")
    public String addTradeForm(Model model) {
        model.addAttribute("trade", new Trade());
        return "trade/add";
    }

    /**
     * Valide et enregistre une nouvelle transaction.
     *
     * @param trade  l’objet {@link Trade} à valider
     * @param result le résultat de la validation
     * @param model  le modèle utilisé pour la vue
     * @return redirection vers la liste ou formulaire si erreur
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("trade") Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/add";
        }
        tradeService.saveTrade(trade);
        return "redirect:/trade/list";
    }

    /**
     * Affiche le formulaire de mise à jour pour une transaction.
     *
     * @param id    l’identifiant de la transaction à modifier
     * @param model le modèle contenant la transaction
     * @return la vue "trade/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id);
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    /**
     * Valide et applique la mise à jour d’une transaction.
     *
     * @param id     l’identifiant de la transaction à mettre à jour
     * @param trade  l’objet modifié
     * @param result le résultat de la validation
     * @param model  le modèle
     * @return redirection vers la liste ou formulaire si erreur
     */
    @PostMapping("/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid @ModelAttribute("trade") Trade trade,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "trade/update";
        }
        tradeService.updateTrade(id, trade);
        return "redirect:/trade/list";
    }

    /**
     * Supprime une transaction.
     *
     * @param id    l’identifiant de la transaction à supprimer
     * @param model le modèle
     * @return redirection vers la vue "trade/list"
     */
    @GetMapping("/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        tradeService.deleteTrade(id);
        return "redirect:/trade/list";
    }
}
