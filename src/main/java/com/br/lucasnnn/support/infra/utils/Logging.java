package com.br.lucasnnn.support.infra.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {

    private Logging() {
        //
    }

    static Class<?> getCaller() {
        return StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                .walk(fms -> fms.skip(2).findFirst())
                .map(StackWalker.StackFrame::getDeclaringClass)
                .orElseThrow(() -> new IllegalStateException("Class not found"));
    }

    public static void info(String message) {
        Logger log = LoggerFactory.getLogger(getCaller());

        log.info(message);
    }

    public static void error(String message, Throwable throwable) {
        Logger log = LoggerFactory.getLogger(getCaller());

        log.error(message, throwable);
    }
}
