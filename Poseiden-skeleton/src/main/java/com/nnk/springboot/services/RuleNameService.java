package com.nnk.springboot.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.domain.RuleName;
import java.util.List;

@Service
public class RuleNameService {
    @Autowired
    private RuleNameRepository ruleNameRepository;

    public List<RuleName> getAllRules() {
        return ruleNameRepository.findAll();
    }

    public RuleName getRuleById(Integer id) {
        return ruleNameRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Rule non trouvée"));
    }

    public RuleName saveRule(RuleName ruleName) {
        return ruleNameRepository.save(ruleName);
    }

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

    public void deleteRule(Integer id) {
        ruleNameRepository.deleteById(id);
    }
}
