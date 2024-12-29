package com.br.lucasnnn.support.application.usecase.strategies.issue;

import com.br.lucasnnn.support.application.domain.enums.SupportLevels;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class LevelStrategyTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    public void testLevelStrategyNamesAreValid() {
        // Obter todos os beans do tipo LevelStrategy
        String[] levelStrategyBeans = applicationContext.getBeanNamesForType(LevelStrategy.class);

        // Verifica se cada nome de bean é um nível válido
        for (String beanName : levelStrategyBeans) {
            boolean isValidLevel = SupportLevels.isValid(beanName);

            assertTrue(isValidLevel, "Bean name '" + beanName + "' does not match any valid support level.");
        }
    }

    @Test
    public void testNoDuplicateLevelStrategyNames() {
        // Obter todos os beans do tipo LevelStrategy
        String[] levelStrategyBeans = applicationContext.getBeanNamesForType(LevelStrategy.class);

        Set<String> levelNames = new HashSet<>();
        Set<String> duplicateNames = new HashSet<>();

        // Verifica se há duplicatas
        for (String beanName : levelStrategyBeans) {
            if (!levelNames.add(beanName)) {
                duplicateNames.add(beanName);
            }
        }

        assertTrue(duplicateNames.isEmpty(), "Duplicate level strategy names found: " + duplicateNames);
    }

    @Test
    public void testAllLevelStrategiesAreRegistered() {
        // Obter todos os beans do tipo LevelStrategy
        String[] levelStrategyBeans = applicationContext.getBeanNamesForType(LevelStrategy.class);

        var strategyBeans = Arrays.stream(levelStrategyBeans).toList();

        List<String> levels = SupportLevels.getAll();

        // Verifica se cada nível tem um bean implementado
        for (String level : levels) {
            boolean implementedBean = strategyBeans.contains(level);

            assertTrue(implementedBean, "Level '" + level + "' does not match any implemented bean.");
        }
    }
}