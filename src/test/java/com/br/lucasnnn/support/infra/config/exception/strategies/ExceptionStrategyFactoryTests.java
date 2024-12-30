package com.br.lucasnnn.support.infra.config.exception.strategies;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

public class ExceptionStrategyFactoryTests {

    private ExceptionStrategyFactory exceptionStrategyFactory;

    @MockBean
    private ExceptionStrategy<IllegalArgumentException> illegalArgumentExceptionStrategy;

    @MockBean
    private ExceptionStrategy<NullPointerException> nullPointerExceptionStrategy;

    @BeforeEach
    public void setUp() {
        illegalArgumentExceptionStrategy = Mockito.mock(ExceptionStrategy.class);
        nullPointerExceptionStrategy = Mockito.mock(ExceptionStrategy.class);

        when(illegalArgumentExceptionStrategy.getExceptionType()).thenReturn(IllegalArgumentException.class);
        when(nullPointerExceptionStrategy.getExceptionType()).thenReturn(NullPointerException.class);

        List<ExceptionStrategy<?>> strategies = Arrays.asList(
                illegalArgumentExceptionStrategy,
                nullPointerExceptionStrategy
        );

        exceptionStrategyFactory = new ExceptionStrategyFactory(strategies);
    }

    @Test
    public void testGetStrategy_forRegisteredException() {
        ExceptionStrategy<IllegalArgumentException> strategy = exceptionStrategyFactory.getStrategy(IllegalArgumentException.class);

        assertEquals(illegalArgumentExceptionStrategy, strategy);
        verify(illegalArgumentExceptionStrategy).getExceptionType();
    }

    @Test
    public void testGetStrategy_forUnregisteredException() {
        ExceptionStrategy<RuntimeException> strategy = exceptionStrategyFactory.getStrategy(RuntimeException.class);

        assertNull(strategy);
    }

    @Test
    public void testGetStrategy_forNullPointerException() {
        ExceptionStrategy<NullPointerException> strategy = exceptionStrategyFactory.getStrategy(NullPointerException.class);

        assertEquals(nullPointerExceptionStrategy, strategy);
        verify(nullPointerExceptionStrategy).getExceptionType();
    }
}