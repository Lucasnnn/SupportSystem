package com.br.lucasnnn.support.infra.exception.factory.impl;

import com.br.lucasnnn.support.infra.exception.factory.IException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

public class NotValid implements IException<MethodArgumentNotValidException> {
    public HttpStatus getStatusCode() {
        return HttpStatus.UNPROCESSABLE_ENTITY;
    }

    public String constructMessage(MethodArgumentNotValidException ex) {
        StringBuilder messageBuilder = new StringBuilder("\n");

        Map<String, String> errors = getErros(ex);

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
