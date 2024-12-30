package com.br.lucasnnn.support.application.usecase.strategies.triage.impl;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AvgTests {

    private Avg avgStrategy;

    @BeforeEach
    public void setUp() {
        avgStrategy = new Avg();
    }

    @Test
    public void testGetMethod() {
        // Act
        TriageMethod method = avgStrategy.getMethod();

        // Assert
        assertEquals(TriageMethod.AVG, method);
    }

    @Test
    public void testExecuteWithSingleLevel() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setPriority(5);
        request.setComplexity(5);

        List<SupportLevel> levels = new ArrayList<>();
        SupportLevel level1 = new SupportLevel();
        level1.setLevel(1);
        levels.add(level1);

        // Act
        SupportLevel assignedLevel = avgStrategy.execute(levels, request);

        // Assert
        assertEquals(level1, assignedLevel);
    }

    @Test
    public void testExecuteWithMultipleLevels() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setPriority(8);
        request.setComplexity(6);

        List<SupportLevel> levels = new ArrayList<>();

        SupportLevel level1 = new SupportLevel();
        level1.setLevel(1);

        SupportLevel level2 = new SupportLevel();
        level2.setLevel(2);

        SupportLevel level3 = new SupportLevel();
        level3.setLevel(3);

        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        // Act
        SupportLevel assignedLevel = avgStrategy.execute(levels, request);

        // Assert
        assertEquals(level3, assignedLevel);
    }

    @Test
    public void testExecuteWithHighValues() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setPriority(10);
        request.setComplexity(10);

        List<SupportLevel> levels = new ArrayList<>();
        SupportLevel level1 = new SupportLevel();
        level1.setLevel(1);
        SupportLevel level2 = new SupportLevel();
        level2.setLevel(2);
        SupportLevel level3 = new SupportLevel();
        level3.setLevel(3);
        levels.add(level1);
        levels.add(level2);
        levels.add(level3);

        // Act
        SupportLevel assignedLevel = avgStrategy.execute(levels, request);

        // Assert
        assertEquals(level3, assignedLevel); // Expecting level 3 to be assigned
    }
}