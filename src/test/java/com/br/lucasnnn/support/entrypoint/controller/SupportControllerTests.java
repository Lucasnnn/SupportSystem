package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.CreateLevelUseCase;
import com.br.lucasnnn.support.application.usecase.LevelTriageUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SupportControllerTests {

    @InjectMocks
    private SupportController supportController;

    @Mock
    private LevelTriageUseCase levelTriageUseCase;

    @Mock
    private CreateLevelUseCase createLevelUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSupportTriage() {
        // Arrange
        SupportRequest request = new SupportRequest(); // Preencha com dados de teste
        String method = "testMethod";
        String expectedConfirmation = "Triage successful";

        when(levelTriageUseCase.execute(request, method)).thenReturn(expectedConfirmation);

        // Act
        ResponseEntity<String> response = supportController.supportTriage(request, method);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(expectedConfirmation, response.getBody());
        verify(levelTriageUseCase).execute(request, method);
    }

    @Test
    public void testCreateSupportLevel() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel(); // Preencha com dados de teste

        // Act
        ResponseEntity<SupportLevel> response = supportController.createSupportLevel(supportLevel);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(supportLevel, response.getBody());
        verify(createLevelUseCase).execute(supportLevel);
    }
}