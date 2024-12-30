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

public class SupportLevelTests {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidSupportLevelSenior() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(4);
        supportLevel.setName("Sênior");
        supportLevel.setEmail("senior@example.com");

        // Act
        Set<ConstraintViolation<SupportLevel>> violations = validator.validate(supportLevel);

        // Assert
        assertTrue(violations.isEmpty(), "Expected no validation violations for Sênior");
    }

    @Test
    public void testNullLevel() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(null);
        supportLevel.setName("Júnior");
        supportLevel.setEmail("junior@example.com");

        // Act
        Set<ConstraintViolation<SupportLevel>> violations = validator.validate(supportLevel);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The level field cannot be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullName() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(1);
        supportLevel.setName(null);
        supportLevel.setEmail("junior@example.com");

        // Act
        Set<ConstraintViolation<SupportLevel>> violations = validator.validate(supportLevel);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The name field cannot be null.", violations.iterator().next().getMessage());
    }

    @Test
    public void testNullEmail() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(1);
        supportLevel.setName("Júnior");
        supportLevel.setEmail(null);

        // Act
        Set<ConstraintViolation<SupportLevel>> violations = validator.validate(supportLevel);

        // Assert
        assertEquals(1, violations.size());
        assertEquals("The email field cannot be null.", violations.iterator().next().getMessage());
    }
}