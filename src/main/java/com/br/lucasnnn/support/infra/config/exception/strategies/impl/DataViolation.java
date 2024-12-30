package com.br.lucasnnn.support.infra.config.exception.strategies.impl;

import com.br.lucasnnn.support.infra.config.exception.strategies.ExceptionStrategy;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class DataViolation implements ExceptionStrategy<DataIntegrityViolationException> {
    @Override
    public Class<DataIntegrityViolationException> getExceptionType() {
        return DataIntegrityViolationException.class;
    }

    @Override
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

    @Override
    public String constructMessage(Exception ex) {
        return "Creation attempt failed due to unique key violation.";
    }
}
