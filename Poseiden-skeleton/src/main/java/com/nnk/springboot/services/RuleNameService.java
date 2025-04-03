package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.domain.RuleName;
import java.util.List;

/**
 * Service pour la gestion des entités {@link RuleName}.
 * <p>
 * Fournit les opérations CRUD liées aux règles métiers de l'application.
 * </p>
 */
@Service
public class RuleNameService {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    /**
     * Récupère toutes les règles présentes en base.
     *
     * @return une liste d'objets {@link RuleName}
     */
    public List<RuleName> getAllRules() {
        return ruleNameRepository.findAll();
    }

    /**
     * Récupère une règle spécifique par son identifiant.
     *
     * @param id identifiant de la règle
     * @return la règle trouvée
     * @throws RuntimeException si aucune règle n'est trouvée
     */
    public RuleName getRuleById(Integer id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule non trouvée"));
    }

    /**
     * Enregistre une nouvelle règle en base.
     *
     * @param ruleName la règle à sauvegarder
     * @return la règle sauvegardée
     */
    public RuleName saveRule(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Met à jour une règle existante avec les nouvelles valeurs fournies.
     *
     * @param id identifiant de la règle à mettre à jour
     * @param ruleDetails objet contenant les nouvelles valeurs
     * @return la règle mise à jour
     */
    public RuleName updateRule(Integer id, RuleName ruleDetails) {
        RuleName ruleName = getRuleById(id);
        ruleName.setName(ruleDetails.getName());
        ruleName.setDescription(ruleDetails.getDescription());
        ruleName.setJson(ruleDetails.getJson());
        ruleName.setTemplate(ruleDetails.getTemplate());
        ruleName.setSqlStr(ruleDetails.getSqlStr());
        ruleName.setSqlPart(ruleDetails.getSqlPart());
        return ruleNameRepository.save(ruleName);
    }

    /**
     * Supprime une règle de la base à partir de son identifiant.
     *
     * @param id identifiant de la règle à supprimer
     */
    public void deleteRule(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
