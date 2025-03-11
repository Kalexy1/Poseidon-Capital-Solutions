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

@Controller
@RequestMapping("/bidList")
public class BidListController {
    @Autowired
    private BidListService bidListService;

    // Affichage de la liste des enchères
    @GetMapping("/list")
    public String home(Model model) {
        List<BidList> bids = bidListService.getAllBidLists();
        model.addAttribute("bids", bids);
        return "bidList/list";
    }

    // Formulaire d'ajout
    @GetMapping("/add")
    public String addBidForm(Model model) {
        model.addAttribute("bid", new BidList());
        return "bidList/add";
    }

    // Validation et enregistrement d'un nouveau BidList
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("bid") BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListService.saveBidList(bid);
        return "redirect:/bidList/list";
    }

    // Affichage du formulaire de mise à jour
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bid = bidListService.getBidListById(id);
        model.addAttribute("bid", bid);
        return "bidList/update";
    }

    // Validation et mise à jour
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid @ModelAttribute("bid") BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidListService.updateBidList(id, bidList);
        return "redirect:/bidList/list";
    }

    // Suppression d'un BidList
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidList(id);
        return "redirect:/bidList/list";
    }
}
