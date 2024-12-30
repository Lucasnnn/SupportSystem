package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.LevelTriageUseCase;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SupportControllerTests {

    @Mock
    private LevelTriageUseCase levelTriageUseCase;

    @InjectMocks
    private SupportController supportController;

    public SupportControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setId("f47ac10b-58cc-4372-a567-0e02b2c3d479");
        request.setComplexity(5);
        request.setPriority(3);

        // Act
        ResponseEntity<String> response = supportController.supportTriage(request);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(levelTriageUseCase).execute(request);
    }
}