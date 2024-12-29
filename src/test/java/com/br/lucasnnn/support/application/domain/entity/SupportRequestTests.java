package com.br.lucasnnn.support.application.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SupportRequestTests {

    @Test
    public void testSetAndGetIssue() {
        // Arrange
        SupportRequest request = new SupportRequest();
        String expectedIssue = "Test issue";

        // Act
        request.setIssue(expectedIssue);
        String actualIssue = request.getIssue();

        // Assert
        assertEquals(expectedIssue, actualIssue, "The issue should be set and retrieved correctly.");
    }
}