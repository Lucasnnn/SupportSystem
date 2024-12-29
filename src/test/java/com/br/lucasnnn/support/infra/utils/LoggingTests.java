package com.br.lucasnnn.support.infra.utils;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class LoggingTests {

    @Test
    public void testInfoLogging() {
        // Arrange
        Logger mockLogger = Mockito.mock(Logger.class);

        try (var mockedStatic = mockStatic(LoggerFactory.class)) {
            mockedStatic.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);

            // Act
            Logging.info("Test info message");

            // Assert
            verify(mockLogger).info("Test info message");
        }
    }

    @Test
    public void testErrorLogging() {
        // Arrange
        Logger mockLogger = Mockito.mock(Logger.class);

        try (var mockedStatic = mockStatic(LoggerFactory.class)) {
            mockedStatic.when(() -> LoggerFactory.getLogger(any(Class.class))).thenReturn(mockLogger);

            // Act
            Exception testException = new Exception("Test exception");
            Logging.error("Test error message", testException);

            // Assert
            verify(mockLogger).error(eq("Test error message"), any(Exception.class));
        }
    }
}