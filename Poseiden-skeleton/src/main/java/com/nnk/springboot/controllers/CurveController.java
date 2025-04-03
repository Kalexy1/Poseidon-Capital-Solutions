package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Contrôleur Spring MVC pour gérer les opérations CRUD sur les entités {@link CurvePoint}.
 * <p>
 * Ce contrôleur permet d'afficher la liste des points de courbe, d'ajouter, de modifier
 * ou de supprimer des enregistrements via des vues Thymeleaf.
 * </p>
 */
@Controller
@RequestMapping("/curvePoint")
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    /**
     * Affiche la liste de tous les CurvePoints enregistrés.
     *
     * @param model le modèle utilisé pour transmettre les données à la vue
     * @return le nom de la vue Thymeleaf "curvePoint/list"
     */
    @GetMapping("/list")
    public String home(Model model) {
        List<CurvePoint> curvePoints = curvePointService.getAllCurvePoints();
        model.addAttribute("curvePoints", curvePoints);
        return "curvePoint/list";
    }

    /**
     * Affiche le formulaire pour ajouter un nouveau CurvePoint.
     *
     * @param model le modèle pour injecter un objet vide dans le formulaire
     * @return le nom de la vue "curvePoint/add"
     */
    @GetMapping("/add")
    public String addCurveForm(Model model) {
        model.addAttribute("curvePoint", new CurvePoint());
        return "curvePoint/add";
    }

    /**
     * Valide les données du formulaire et enregistre un nouveau CurvePoint s'il n'y a pas d'erreurs.
     *
     * @param curvePoint l'objet à valider
     * @param result     le résultat de la validation
     * @param model      le modèle utilisé pour afficher le formulaire en cas d'erreurs
     * @return redirection vers "/curvePoint/list" ou retour au formulaire s'il y a des erreurs
     */
    @PostMapping("/validate")
    public String validate(@Valid @ModelAttribute("curvePoint") CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/add";
        }
        curvePointService.saveCurvePoint(curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Affiche le formulaire de mise à jour pour un CurvePoint existant.
     *
     * @param id    l'identifiant du CurvePoint à modifier
     * @param model le modèle contenant l'entité à afficher
     * @return la vue "curvePoint/update"
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    /**
     * Valide et met à jour un CurvePoint existant.
     *
     * @param id         l'identifiant du CurvePoint à mettre à jour
     * @param curvePoint les nouvelles données soumises depuis le formulaire
     * @param result     le résultat de la validation
     * @param model      le modèle pour afficher le formulaire en cas d'erreurs
     * @return redirection vers "/curvePoint/list" ou retour au formulaire en cas d'erreurs
     */
    @PostMapping("/update/{id}")
    public String updateCurve(@PathVariable("id") Integer id,
                              @Valid @ModelAttribute("curvePoint") CurvePoint curvePoint,
                              BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        curvePoint.setId(id); // Pour s'assurer que l'ID ne change pas
        curvePointService.updateCurvePoint(id, curvePoint);
        return "redirect:/curvePoint/list";
    }

    /**
     * Supprime un CurvePoint à partir de son identifiant.
     *
     * @param id    l'identifiant du CurvePoint à supprimer
     * @param model le modèle pour gérer les redirections
     * @return redirection vers "/curvePoint/list"
     */
    @GetMapping("/delete/{id}")
    public String deleteCurve(@PathVariable("id") Integer id, Model model) {
        curvePointService.deleteCurvePoint(id);
        return "redirect:/curvePoint/list";
    }
}
