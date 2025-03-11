package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.repositories.RuleNameRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RuleTests {

    @Autowired
    private RuleNameRepository ruleNameRepository;

    private RuleName rule;

    @BeforeEach
    public void setup() {
        rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");
        rule = ruleNameRepository.save(rule);
    }

    @AfterEach
    public void cleanup() {
        ruleNameRepository.deleteAll();
    }

    @Test
    public void testSaveRule() {
        assertNotNull(rule.getId());
        assertEquals("Rule Name", rule.getName());
    }

    @Test
    public void testUpdateRule() {
        rule.setName("Rule Name Update");
        rule = ruleNameRepository.save(rule);
        assertEquals("Rule Name Update", rule.getName());
    }

    @Test
    public void testFindAllRules() {
        List<RuleName> listResult = ruleNameRepository.findAll();
        assertFalse(listResult.isEmpty());
    }

    @Test
    public void testDeleteRule() {
        Integer id = rule.getId();
        ruleNameRepository.delete(rule);
        Optional<RuleName> ruleList = ruleNameRepository.findById(id);
        assertFalse(ruleList.isPresent());
    }
}
