package com.br.lucasnnn.support.infra.config.exception.strategies;

import org.springframework.http.HttpStatus;

public interface ExceptionStrategy<T extends Exception> {
    Class<T> getExceptionType();

    HttpStatus getStatusCode();

    String constructMessage(Exception ex);
}