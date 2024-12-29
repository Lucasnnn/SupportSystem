package com.br.lucasnnn.support.application.usecase.strategies.issue;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

public class Level1Tests {

    @Test
    public void testResolveIssue() {
        // Arrange
        Level1 level1 = new Level1();
        SupportRequest request = new SupportRequest();
        request.setIssue("Test issue");

        // Mocking the Logging class
        try (var mockedStatic = mockStatic(Logging.class)) {
            // Act
            boolean result = level1.resolveIssue(request);

            // Assert
            mockedStatic.verify(() -> Logging.info("Level 1 support handling request: " + request.getIssue()));
            assertFalse(result, "Expected the issue to not be resolved.");
        }
    }
}