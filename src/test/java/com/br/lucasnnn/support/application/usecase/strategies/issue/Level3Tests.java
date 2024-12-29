package com.br.lucasnnn.support.application.usecase.strategies.issue;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mockStatic;

public class Level3Tests {

    @Test
    public void testResolveIssue() {
        // Arrange
        Level3 level3 = new Level3();
        SupportRequest request = new SupportRequest();
        request.setIssue("Test issue");

        // Mocking the Logging class
        try (var mockedStatic = mockStatic(Logging.class)) {
            // Act
            boolean result = level3.resolveIssue(request);

            // Assert
            mockedStatic.verify(() -> Logging.info("Level 3 support handling request: " + request.getIssue()));
            assertFalse(result, "Expected the issue to not be resolved.");
        }
    }
}