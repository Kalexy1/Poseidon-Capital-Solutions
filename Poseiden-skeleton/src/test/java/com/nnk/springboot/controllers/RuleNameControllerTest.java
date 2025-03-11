package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class RuleNameControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RuleNameService ruleNameService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RuleNameController ruleNameController;

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

        mockMvc = MockMvcBuilders.standaloneSetup(ruleNameController).build();
    }

    @Test
    void testHome_ShouldReturnRuleListPage() throws Exception {
        List<RuleName> rules = Arrays.asList(ruleName);
        when(ruleNameService.getAllRules()).thenReturn(rules);

        String viewName = ruleNameController.home(model);

        assertEquals("ruleName/list", viewName);
        verify(ruleNameService, times(1)).getAllRules();
        verify(model, times(1)).addAttribute("rules", rules);
    }

    @Test
    void testAddRuleForm_ShouldReturnAddPage() {
        String viewName = ruleNameController.addRuleForm(model);

        assertEquals("ruleName/add", viewName);
        verify(model, times(1)).addAttribute(eq("ruleName"), any(RuleName.class));
    }

    @Test
    void testValidate_ShouldSaveRuleAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ruleNameController.validate(ruleName, bindingResult, model);

        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).saveRule(ruleName);
    }

    @Test
    void testValidate_ShouldReturnAddPageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ruleNameController.validate(ruleName, bindingResult, model);

        assertEquals("ruleName/add", viewName);
        verify(ruleNameService, times(0)).saveRule(any(RuleName.class));
    }

    @Test
    void testShowUpdateForm_ShouldReturnUpdatePage() {
        when(ruleNameService.getRuleById(1)).thenReturn(ruleName);

        String viewName = ruleNameController.showUpdateForm(1, model);

        assertEquals("ruleName/update", viewName);
        verify(model, times(1)).addAttribute("ruleName", ruleName);
    }

    @Test
    void testUpdateRuleName_ShouldUpdateRuleAndRedirect() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = ruleNameController.updateRuleName(1, ruleName, bindingResult, model);

        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).updateRule(1, ruleName);
    }

    @Test
    void testUpdateRuleName_ShouldReturnUpdatePageIfErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);

        String viewName = ruleNameController.updateRuleName(1, ruleName, bindingResult, model);

        assertEquals("ruleName/update", viewName);
        verify(ruleNameService, times(0)).updateRule(anyInt(), any(RuleName.class));
    }

    @Test
    void testDeleteRuleName_ShouldDeleteRuleAndRedirect() {
        String viewName = ruleNameController.deleteRuleName(1, model);

        assertEquals("redirect:/ruleName/list", viewName);
        verify(ruleNameService, times(1)).deleteRule(1);
    }
}
