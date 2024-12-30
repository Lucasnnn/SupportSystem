package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import com.br.lucasnnn.support.application.domain.respository.NotificationRepository;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageStrategy;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class LevelTriageUseCaseTests {

    @InjectMocks
    private LevelTriageUseCase levelTriageUseCase;

    @Mock
    private LevelRepository levelRepository;

    @Mock
    private NotificationRepository notificationRepository;

    @Mock
    private TriageStrategyFactory triageStrategyFactory;

    @Mock
    private TriageStrategy triageStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecuteWithNoActiveSupportLevels() {
        // Arrange
        SupportRequest request = new SupportRequest();
        String method = "someMethod";

        when(levelRepository.getAll()).thenReturn(new ArrayList<>());

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            levelTriageUseCase.execute(request, method);
        });

        assertEquals("No active support levels.", exception.getMessage());
        verify(levelRepository).getAll();
    }

    @Test
    public void testExecuteWithValidSupportLevels() {
        // Arrange
        SupportRequest request = new SupportRequest();
        String method = "someMethod";

        List<SupportLevel> levels = new ArrayList<>();
        SupportLevel level1 = new SupportLevel();
        level1.setLevel(1);
        SupportLevel level2 = new SupportLevel();
        level2.setLevel(2);
        levels.add(level1);
        levels.add(level2);

        when(levelRepository.getAll()).thenReturn(levels);
        when(triageStrategyFactory.getStrategy(method)).thenReturn(triageStrategy);
        when(triageStrategy.execute(levels, request)).thenReturn(level1);
        when(notificationRepository.send(request, level1)).thenReturn("Notification sent");

        // Act
        String result = levelTriageUseCase.execute(request, method);

        // Assert
        assertEquals("Notification sent", result);
        verify(levelRepository).getAll();
        verify(triageStrategyFactory).getStrategy(method);
        verify(triageStrategy).execute(levels, request);
        verify(notificationRepository).send(request, level1);
    }
}