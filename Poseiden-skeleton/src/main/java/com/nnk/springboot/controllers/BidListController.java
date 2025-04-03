package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur pour la gestion des entités {@link BidList}.
 * Gère les opérations CRUD via des routes HTTP, et affiche les vues associées via Thymeleaf.
 */
@Controller
@RequestMapping("/bidList")
public class BidListController {

    @Autowired
    private BidListService bidListService;

    /**
     * Affiche la liste de toutes les enchères.
     *
     * @param model le modèle utilisé pour passer les données à la vue
     * @return la vue Thymeleaf "bidList/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        List<BidList> bids = bidListService.getAllBidLists();
        model.addAttribute("bidLists", bids); // ✅ Aligne avec la vue
        return "bidList/list";
    }

    /**
     * Affiche le formulaire pour ajouter une nouvelle enchère.
     *
     * @param model le modèle utilisé pour afficher le formulaire
     * @return la vue "bidList/add"
     */
    @GetMapping("/add")
    public String addBidForm(Model model) {
        model.addAttribute("bidList", new BidList());
        return "bidList/add";
    }

    /**
     * Valide et enregistre une nouvelle enchère.
     *
     * @param bidList l'objet {@link BidList} à valider
     * @param result  contient les erreurs de validation, le cas échéant
     * @param model   le modèle pour retourner au formulaire en cas d'erreur
     * @return redirection vers la liste des enchères ou retour au formulaire
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("bidList") BidList bidList, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.saveBidList(bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Affiche le formulaire de mise à jour pour une enchère existante.
     *
     * @param id    l'identifiant de l'enchère à modifier
     * @param model le modèle utilisé pour afficher les données existantes
     * @return la vue "bidList/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListService.getBidListById(id);
        model.addAttribute("bidList", bid);
        return "bidList/update";
    }

    /**
     * Valide et met à jour une enchère existante.
     *
     * @param id       l'identifiant de l'enchère à mettre à jour
     * @param bidList  les nouvelles données à enregistrer
     * @param result   les éventuelles erreurs de validation
     * @param model    le modèle pour retourner au formulaire en cas d'erreur
     * @return redirection vers la liste des enchères ou retour au formulaire
     */
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bidList") BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id); // Assure la mise à jour correcte de l'entité
        bidListService.updateBidList(id, bidList);
        return "redirect:/bidList/list";
    }

    /**
     * Supprime une enchère existante.
     *
     * @param id    l'identifiant de l'enchère à supprimer
     * @param model le modèle pour gérer la redirection
     * @return redirection vers la liste des enchères
     */
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
