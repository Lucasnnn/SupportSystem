package com.br.lucasnnn.support.infra.config.exception;

import com.br.lucasnnn.support.infra.config.exception.strategies.ExceptionStrategy;
import com.br.lucasnnn.support.infra.config.exception.strategies.ExceptionStrategyFactory;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {
    private final ExceptionStrategyFactory exceptionStrategyFactory;

    public GlobalExceptionHandler(ExceptionStrategyFactory exceptionStrategyFactory) {
        this.exceptionStrategyFactory = exceptionStrategyFactory;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String message = "Error message: ";

        Logging.info("Handling exception: " + ex.getClass().getName());

        ExceptionStrategy<?> strategy = exceptionStrategyFactory.getStrategy(ex.getClass());

        if (Objects.isNull(strategy)) {
            Logging.info("No exception strategy found for: " + ex.getClass().getName());

            message += ex.getMessage();

            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(message);
        } else {
            message += strategy.constructMessage(ex);

            Logging.info("Exception strategy found: " + strategy.getClass().getName());
            Logging.info("Constructed message: " + message);

            Logging.error(message, ex);

            HttpStatus status = strategy.getStatusCode();

            Logging.info("Returning HTTP status: " + status + " for exception");

            return ResponseEntity
                    .status(status)
                    .body(message);
        }
    }
}