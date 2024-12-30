package com.br.lucasnnn.support.infra.config.exception.strategies.impl;

import com.br.lucasnnn.support.infra.config.exception.strategies.ExceptionStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class NotValid implements ExceptionStrategy<MethodArgumentNotValidException> {
    @Override
    public Class<MethodArgumentNotValidException> getExceptionType() {
        return MethodArgumentNotValidException.class;
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    @Override
    public String constructMessage(Exception ex) {
        StringBuilder messageBuilder = new StringBuilder("\n");

        Map<String, String> errors = getErros((MethodArgumentNotValidException) ex);

        for (Map.Entry<String, String> entry : errors.entrySet()) {
            messageBuilder.append(entry.getKey())
                    .append(": ")
                    .append(entry.getValue())
                    .append("\n");
        }

        return messageBuilder.toString().trim();
    }

    private Map<String, String> getErros(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return errors;
    }

}
