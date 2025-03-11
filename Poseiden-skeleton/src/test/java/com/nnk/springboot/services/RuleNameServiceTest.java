package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameServiceTest {

    @Mock
    private RuleNameRepository ruleNameRepository;

    @InjectMocks
    private RuleNameService ruleNameService;

    private RuleName ruleName;

    @BeforeEach
    void setUp() {
        ruleName = new RuleName();
        ruleName.setId(1);
        ruleName.setName("Test Rule");
        ruleName.setDescription("Test Description");
        ruleName.setJson("Test JSON");
        ruleName.setTemplate("Test Template");
        ruleName.setSqlStr("SELECT * FROM test");
        ruleName.setSqlPart("WHERE id=1");
    }

    @Test
    void testGetAllRules() {
        when(ruleNameRepository.findAll()).thenReturn(Arrays.asList(ruleName));

        List<RuleName> rules = ruleNameService.getAllRules();

        assertNotNull(rules);
        assertEquals(1, rules.size());
        assertEquals("Test Rule", rules.get(0).getName());

        verify(ruleNameRepository, times(1)).findAll();
    }

    @Test
    void testGetRuleById_RuleFound() {
        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));

        RuleName foundRule = ruleNameService.getRuleById(1);

        assertNotNull(foundRule);
        assertEquals("Test Rule", foundRule.getName());

        verify(ruleNameRepository, times(1)).findById(1);
    }

    @Test
    void testGetRuleById_RuleNotFound() {
        when(ruleNameRepository.findById(2)).thenReturn(Optional.empty());

        Exception exception = assertThrows(RuntimeException.class, () -> ruleNameService.getRuleById(2));

        assertEquals("Rule non trouvée", exception.getMessage());
        verify(ruleNameRepository, times(1)).findById(2);
    }

    @Test
    void testSaveRule() {
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(ruleName);

        RuleName savedRule = ruleNameService.saveRule(ruleName);

        assertNotNull(savedRule);
        assertEquals("Test Rule", savedRule.getName());

        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    void testUpdateRule() {
        RuleName updatedRuleDetails = new RuleName();
        updatedRuleDetails.setName("Updated Rule");
        updatedRuleDetails.setDescription("Updated Description");
        updatedRuleDetails.setJson("Updated JSON");
        updatedRuleDetails.setTemplate("Updated Template");
        updatedRuleDetails.setSqlStr("SELECT * FROM updated");
        updatedRuleDetails.setSqlPart("WHERE id=2");

        when(ruleNameRepository.findById(1)).thenReturn(Optional.of(ruleName));
        when(ruleNameRepository.save(any(RuleName.class))).thenReturn(updatedRuleDetails);

        RuleName updatedRule = ruleNameService.updateRule(1, updatedRuleDetails);

        assertNotNull(updatedRule);
        assertEquals("Updated Rule", updatedRule.getName());
        assertEquals("Updated Description", updatedRule.getDescription());

        verify(ruleNameRepository, times(1)).findById(1);
        verify(ruleNameRepository, times(1)).save(ruleName);
    }

    @Test
    void testDeleteRule() {
        doNothing().when(ruleNameRepository).deleteById(1);

        ruleNameService.deleteRule(1);

        verify(ruleNameRepository, times(1)).deleteById(1);
    }
}
