package com.br.lucasnnn.support.application.usecase.strategies.triage;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TriageMethodTests {

    @Test
    public void testEnumValues() {
        // Verifica se o enum contém o valor AVG
        TriageMethod method = TriageMethod.AVG;

        // Verifica se o valor não é nulo
        assertNotNull(method);

        // Verifica se o valor do enum é o esperado
        assertEquals("AVG", method.name());
        assertEquals(0, method.ordinal()); // AVG é o primeiro valor, então seu índice deve ser 0
    }

    @Test
    public void testEnumCount() {
        // Verifica se o número de valores no enum é o esperado
        TriageMethod[] methods = TriageMethod.values();
        assertEquals(1, methods.length); // Deve haver apenas um valor: AVG
    }
}