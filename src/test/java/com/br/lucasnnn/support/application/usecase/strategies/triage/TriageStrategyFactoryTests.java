package com.br.lucasnnn.support.application.usecase.strategies.triage;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TriageStrategyFactoryTests {

    @Mock
    private TriageStrategy avgStrategy;

    private TriageStrategyFactory triageStrategyFactory;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(avgStrategy.getMethod()).thenReturn(TriageMethod.AVG);
        triageStrategyFactory = new TriageStrategyFactory(Collections.singletonList(avgStrategy));
    }

    @Test
    public void testGetStrategyWithValidMethod() {
        // Act
        TriageStrategy strategy = triageStrategyFactory.getStrategy(TriageMethod.AVG);

        // Assert
        assertNotNull(strategy);
        assertEquals(avgStrategy, strategy);
    }

    @Test
    public void testGetStrategyWithNullMethod() {
        // Act
        TriageStrategy strategy = triageStrategyFactory.getStrategy((String) null);

        // Assert
        assertNotNull(strategy);
        assertEquals(avgStrategy, strategy);
    }

    @Test
    public void testGetStrategyWithInvalidMethod() {
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            triageStrategyFactory.getStrategy("INVALID_METHOD");
        });

        assertEquals("The value 'INVALID_METHOD' is not a valid triage method. Allowed values are: AVG, NEXT_LEVEL", exception.getMessage());
    }

    @Test
    public void testGetValidTriageMethods() {
        // Act
        List<String> validMethods = triageStrategyFactory.getValidTriageMethods();

        // Assert
        assertEquals(2, validMethods.size());
        assertEquals("AVG", validMethods.get(0));
    }
}