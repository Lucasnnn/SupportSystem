package com.br.lucasnnn.support.application.domain.entity;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupportRequestTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSupportRequest() {
        // Arrange
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId("12345");
        supportRequest.setPriority(5);
        supportRequest.setComplexity(3);

        // Act
        Set<ConstraintViolation<SupportRequest>> violations = validator.validate(supportRequest);

        // Assert
        assertTrue(violations.isEmpty(), "Expected no validation violations for valid SupportRequest");
    }

    @Test
    public void testNullId() {
        // Arrange
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId(null);
        supportRequest.setPriority(5);
        supportRequest.setComplexity(3);

        // Act
        Set<ConstraintViolation<SupportRequest>> violations = validator.validate(supportRequest);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The id field cannot be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testPriorityOutOfRange() {
        // Arrange
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId("12345");
        supportRequest.setPriority(11); // Invalid priority
        supportRequest.setComplexity(3);

        // Act
        Set<ConstraintViolation<SupportRequest>> violations = validator.validate(supportRequest);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The priority must be at most 10.", violations.iterator().next().getMessage());
    }

    @Test
    public void testComplexityOutOfRange() {
        // Arrange
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId("12345");
        supportRequest.setPriority(5);
        supportRequest.setComplexity(0); // Invalid complexity

        // Act
        Set<ConstraintViolation<SupportRequest>> violations = validator.validate(supportRequest);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The complexity must be at least 1.", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullPriority() {
        // Arrange
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId("12345");
        supportRequest.setPriority(null);
        supportRequest.setComplexity(3);

        // Act
        Set<ConstraintViolation<SupportRequest>> violations = validator.validate(supportRequest);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The priority field cannot be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullComplexity() {
        // Arrange
        SupportRequest supportRequest = new SupportRequest();
        supportRequest.setId("12345");
        supportRequest.setPriority(5);
        supportRequest.setComplexity(null);

        // Act
        Set<ConstraintViolation<SupportRequest>> violations = validator.validate(supportRequest);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The complexity field cannot be null.", violations.iterator().next().getMessage());
    }
}