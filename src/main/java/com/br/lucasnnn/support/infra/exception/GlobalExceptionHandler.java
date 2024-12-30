package com.br.lucasnnn.support.infra.exception;

import com.br.lucasnnn.support.infra.exception.factory.ExceptionFactory;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    GlobalExceptionHandler(ExceptionFactory exceptionFactory) {
        //
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        String message = "Error message= ";

        message += ExceptionFactory.getMessage(ex);

        Logging.error(message, ex);

        HttpStatus status = ExceptionFactory.getCode(ex);

        return ResponseEntity
                .status(status)
                .body(message);
    }
}