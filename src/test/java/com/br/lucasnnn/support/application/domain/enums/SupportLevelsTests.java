package com.br.lucasnnn.support.application.domain.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    public void testInstantiationThrowsException() {
        // Verifica se a exceção UnsupportedOperationException é lançada ao tentar instanciar a classe
        // Tenta instanciar a classe SupportLevels
        UnsupportedOperationException thrown = assertThrows(UnsupportedOperationException.class, SupportLevels::new);

        // Verifica se a mensagem da exceção é a esperada
        assertEquals("This class cannot be instantiated.", thrown.getMessage());
    }
}