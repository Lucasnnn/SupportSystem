package com.br.lucasnnn.support.infra.exception.factory;

import org.springframework.http.HttpStatus;

public interface IException<T extends Exception> {
    String constructMessage(T ex);

    HttpStatus getStatusCode();
}
