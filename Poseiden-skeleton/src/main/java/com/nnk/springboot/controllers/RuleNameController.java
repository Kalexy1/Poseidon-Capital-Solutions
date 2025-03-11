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

@Controller
@RequestMapping("/ruleName")
public class RuleNameController {
    @Autowired
    private RuleNameService ruleNameService;

    // Affichage de la liste des règles
    @GetMapping("/list")
    public String home(Model model) {
        List<RuleName> rules = ruleNameService.getAllRules();
        model.addAttribute("rules", rules);
        return "ruleName/list";
    }

    // Formulaire d'ajout
    @GetMapping("/add")
    public String addRuleForm(Model model) {
        model.addAttribute("ruleName", new RuleName());
        return "ruleName/add";
    }

    // Validation et enregistrement d'un nouveau RuleName
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("ruleName") RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/add";
        }
        ruleNameService.saveRule(ruleName);
        return "redirect:/ruleName/list";
    }

    // Affichage du formulaire de mise à jour
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleById(id);
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    // Validation et mise à jour
    @PostMapping("/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid @ModelAttribute("ruleName") RuleName ruleName,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "ruleName/update";
        }
        ruleNameService.updateRule(id, ruleName);
        return "redirect:/ruleName/list";
    }

    // Suppression d'un RuleName
    @GetMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        ruleNameService.deleteRule(id);
        return "redirect:/ruleName/list";
    }
}
