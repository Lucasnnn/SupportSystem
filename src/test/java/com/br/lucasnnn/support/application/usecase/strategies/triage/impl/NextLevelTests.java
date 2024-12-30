package com.br.lucasnnn.support.application.usecase.strategies.triage.impl;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NextLevelTests {

    @InjectMocks
    private NextLevel nextLevel;

    @Mock
    private SupportRequest supportRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute_WithValidScope_ReturnsNextLevel() {
        // Arrange
        SupportLevel level1 = new SupportLevel();
        level1.setName("estagiario");
        level1.setLevel(1);
        level1.setEmail("estagiario@example.com");

        SupportLevel level2 = new SupportLevel();
        level2.setName("JUNIOR");
        level2.setLevel(2);
        level2.setEmail("junior@example.com");

        SupportLevel level3 = new SupportLevel();
        level3.setName("Pleno");
        level3.setLevel(3);
        level3.setEmail("pleno@example.com");

        SupportLevel level4 = new SupportLevel();
        level4.setName("Senior");
        level4.setLevel(4);
        level4.setEmail("senior@example.com");

        List<SupportLevel> levels = Arrays.asList(level1, level2, level3, level4);

        // Act
        SupportLevel result = nextLevel.execute(levels, supportRequest);

        // Assert
        assertNotNull(result);
        assertEquals(3, result.getLevel());
    }

    @Test
    public void testExecute_WithNoHigherLevel_ThrowsException() {
        // Arrange
        SupportLevel level1 = new SupportLevel();
        level1.setName("estagiario");
        level1.setLevel(1);
        level1.setEmail("estagiario@example.com");

        SupportLevel level2 = new SupportLevel();
        level2.setName("JUNIOR");
        level2.setLevel(2);
        level2.setEmail("junior@example.com");

        List<SupportLevel> levels = Arrays.asList(level1, level2);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            nextLevel.execute(levels, supportRequest);
        });

        assertEquals("Cannot escalate to a outher level support: already at the highest level.", exception.getMessage());
    }

    @Test
    public void testExecute_WithNoValidScope() {
        // Arrange
        SupportLevel level1 = new SupportLevel();
        level1.setName("estagiario");
        level1.setLevel(1);
        level1.setEmail("estagiario@example.com");

        SupportLevel level3 = new SupportLevel();
        level3.setName("Pleno");
        level3.setLevel(3);
        level3.setEmail("pleno@example.com");

        SupportLevel level4 = new SupportLevel();
        level4.setName("Senior");
        level4.setLevel(4);
        level4.setEmail("senior@example.com");

        List<SupportLevel> levels = Arrays.asList(level1, level3, level4);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            nextLevel.execute(levels, supportRequest);
        });

        assertEquals("User  not authorized: scope not found.", exception.getMessage());
    }
}
