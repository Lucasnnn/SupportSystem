package com.br.lucasnnn.support.infra.config.exception.strategies.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataViolationTests {

    private DataViolation dataViolation;

    @BeforeEach
    public void setUp() {
        dataViolation = new DataViolation();
    }

    @Test
    public void testGetExceptionType() {
        assertEquals(DataIntegrityViolationException.class, dataViolation.getExceptionType());
    }

    @Test
    public void testGetStatusCode() {
        assertEquals(HttpStatus.CONFLICT, dataViolation.getStatusCode());
    }

    @Test
    public void testConstructMessage() {
        Exception ex = new DataIntegrityViolationException("Unique key violation");
        String message = dataViolation.constructMessage(ex);
        assertEquals("Creation attempt failed due to unique key violation.", message);
    }
}