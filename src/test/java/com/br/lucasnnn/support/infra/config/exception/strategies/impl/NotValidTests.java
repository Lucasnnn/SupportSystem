package com.br.lucasnnn.support.infra.config.exception.strategies.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NotValidTests {

    private NotValid notValid;

    @BeforeEach
    public void setUp() {
        notValid = new NotValid();
    }

    @Test
    public void testGetExceptionType() {
        assertEquals(MethodArgumentNotValidException.class, notValid.getExceptionType());
    }

    @Test
    public void testGetStatusCode() {
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, notValid.getStatusCode());
    }

    @Test
    public void testConstructMessage() {
        // Mockando o BindingResult
        BindingResult bindingResult = Mockito.mock(BindingResult.class);

        // Mockando o MethodArgumentNotValidException
        MethodArgumentNotValidException ex = Mockito.mock(MethodArgumentNotValidException.class);

        // Criando uma lista de FieldError simulados
        List<FieldError> fieldErrors = new ArrayList<>();
        FieldError fieldError1 = new FieldError("objectName", "field1", "Field 1 is invalid");
        FieldError fieldError2 = new FieldError("objectName", "field2", "Field 2 is invalid");

        fieldErrors.add(fieldError1);
        fieldErrors.add(fieldError2);

        // Simulando o comportamento do BindingResult
        when(bindingResult.getFieldErrors()).thenReturn(fieldErrors);
        when(ex.getBindingResult()).thenReturn(bindingResult);

        // Chamando o método constructMessage
        String message = notValid.constructMessage(ex);

        // Verificando a mensagem construída
        String expectedMessage = "field1: Field 1 is invalid\nfield2: Field 2 is invalid";
        assertEquals(expectedMessage, message);
    }
}