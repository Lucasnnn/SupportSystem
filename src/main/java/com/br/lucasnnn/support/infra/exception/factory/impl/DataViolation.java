package com.br.lucasnnn.support.infra.exception.factory.impl;

import com.br.lucasnnn.support.infra.exception.factory.IException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class DataViolation implements IException<DataIntegrityViolationException> {
    public HttpStatus getStatusCode() {
        return HttpStatus.CONFLICT;
    }

    public String constructMessage(DataIntegrityViolationException ex) {
        return " Tentativa de criação falhou devido à violação de chave única.  ";
    }
}
