package com.br.lucasnnn.support.infra.config.exception;

import com.br.lucasnnn.support.infra.config.exception.strategies.ExceptionStrategy;
import com.br.lucasnnn.support.infra.config.exception.strategies.ExceptionStrategyFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class GlobalExceptionHandlerTests {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Mock
    private ExceptionStrategyFactory exceptionStrategyFactory;

    @Mock
    private ExceptionStrategy<Exception> exceptionStrategy;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleGenericException_withNoStrategy() {
        Exception ex = new Exception("Test exception");

        when(exceptionStrategyFactory.getStrategy(Exception.class)).thenReturn(null);

        ResponseEntity<String> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Error message: Test exception", response.getBody());
        verify(exceptionStrategyFactory).getStrategy(Exception.class);
    }

    @Test
    public void testHandleGenericException_withStrategy() {
        Exception ex = new Exception("Test exception");

        when(exceptionStrategyFactory.getStrategy(Exception.class)).thenReturn(exceptionStrategy);
        when(exceptionStrategy.constructMessage(ex)).thenReturn("Constructed message");
        when(exceptionStrategy.getStatusCode()).thenReturn(HttpStatus.BAD_REQUEST);

        ResponseEntity<String> response = globalExceptionHandler.handleGenericException(ex);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Error message: Constructed message", response.getBody());
        verify(exceptionStrategyFactory).getStrategy(Exception.class);
        verify(exceptionStrategy).constructMessage(ex);
        verify(exceptionStrategy).getStatusCode();
    }
}