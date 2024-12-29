package com.br.lucasnnn.support.application.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SupportLevelsTests {

    @Test
    public void testIsValidWithValidLevels() {
        assertTrue(SupportLevels.isValid(SupportLevels.LEVEL_1), "LEVEL_1 should be valid");
        assertTrue(SupportLevels.isValid(SupportLevels.LEVEL_2), "LEVEL_2 should be valid");
        assertTrue(SupportLevels.isValid(SupportLevels.LEVEL_3), "LEVEL_3 should be valid");
    }

    @Test
    public void testIsValidWithInvalidLevel() {
        assertFalse(SupportLevels.isValid("INVALID_LEVEL"), "INVALID_LEVEL should not be valid");
        assertFalse(SupportLevels.isValid(""), "Empty string should not be valid");
        assertFalse(SupportLevels.isValid(null), "Null should not be valid");
    }
}