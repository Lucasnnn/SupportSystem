package com.br.lucasnnn.support.infra.exception.factory;

import com.br.lucasnnn.support.infra.exception.factory.impl.DataViolation;
import com.br.lucasnnn.support.infra.exception.factory.impl.NotValid;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

@Component
public class ExceptionFactory {
    private static final Map<Class<? extends Exception>, Class<? extends IException>> registry = new HashMap<>();

    private ExceptionFactory() {
        registry.put(MethodArgumentNotValidException.class, NotValid.class);
        registry.put(DataIntegrityViolationException.class, DataViolation.class);
    }

    public static HttpStatus getCode(Exception ex) {
        Class<? extends IException> handlerClass = registry.get(ex.getClass());

        try {
            if (handlerClass != null) {
                IException handler = handlerClass.newInstance();

                return handler.getStatusCode();
            }
        } catch (Exception e) {
            //
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public static String getMessage(Exception ex) {
        Class<? extends IException> handlerClass = registry.get(ex.getClass());

        try {
            if (handlerClass != null) {
                IException handler = handlerClass.newInstance();

                return handler.constructMessage(ex);
            }
        } catch (Exception e) {
            //
        }

        return ex.getMessage();
    }
}
