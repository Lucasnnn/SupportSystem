package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.enums.SupportLevels;
import com.br.lucasnnn.support.application.usecase.strategies.issue.LevelStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ResolveIssueUseCaseTests {

    private ResolveIssueUseCase resolveIssueUseCase;
    private Map<String, LevelStrategy> levelStrategies;

    @BeforeEach
    public void setUp() {
        levelStrategies = new HashMap<>();
        resolveIssueUseCase = new ResolveIssueUseCase(levelStrategies);
    }

    @Test
    public void testExecuteWithValidLevel() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setIssue("Test issue");

        LevelStrategy mockStrategy1 = mock(LevelStrategy.class);
        LevelStrategy mockStrategy2 = mock(LevelStrategy.class);
        levelStrategies.put(SupportLevels.LEVEL_1, mockStrategy1);
        levelStrategies.put(SupportLevels.LEVEL_2, mockStrategy2);

        when(mockStrategy1.resolveIssue(request)).thenReturn(false);
        when(mockStrategy2.resolveIssue(request)).thenReturn(true);

        // Act
        resolveIssueUseCase.execute(request, SupportLevels.LEVEL_1);

        // Assert
        verify(mockStrategy1).resolveIssue(request);
        verify(mockStrategy2).resolveIssue(request);
    }

    @Test
    public void testExecuteWithInvalidLevel() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setIssue("Test issue");

        // Act
        IllegalArgumentException thrown = org.junit.jupiter.api.Assertions.assertThrows(IllegalArgumentException.class, () -> {
            resolveIssueUseCase.execute(request, "INVALID_LEVEL");
        });

        // Assert
        assertEquals("Invalid support level: INVALID_LEVEL", thrown.getMessage());
    }

    @Test
    public void testExecuteWithNullLevel() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setIssue("Test issue");

        LevelStrategy mockStrategy1 = mock(LevelStrategy.class);
        levelStrategies.put(SupportLevels.LEVEL_1, mockStrategy1);
        when(mockStrategy1.resolveIssue(request)).thenReturn(true);

        // Act
        resolveIssueUseCase.execute(request, null);

        // Assert
        verify(mockStrategy1).resolveIssue(request);
    }
}