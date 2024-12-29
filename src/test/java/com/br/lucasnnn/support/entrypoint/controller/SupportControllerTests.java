package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.ResolveIssueUseCase;
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
    private ResolveIssueUseCase resolveIssueUseCase;

    @InjectMocks
    private SupportController supportController;

    public SupportControllerTests() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testProcess() {
        // Arrange
        SupportRequest request = new SupportRequest();
        request.setIssue("Test issue");
        String level = "1";

        // Act
        ResponseEntity<HttpStatus> response = supportController.process(request, level);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(resolveIssueUseCase).execute(request, level);
    }
}