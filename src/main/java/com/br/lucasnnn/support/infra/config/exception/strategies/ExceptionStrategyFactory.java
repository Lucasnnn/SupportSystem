package com.br.lucasnnn.support.infra.config.exception.strategies;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExceptionStrategyFactory {
    private final Map<Class<? extends Exception>, ExceptionStrategy<?>> strategyMap = new HashMap<>();

    public ExceptionStrategyFactory(List<ExceptionStrategy<?>> strategies) {
        for (ExceptionStrategy<?> strategy : strategies) {
            strategyMap.put(strategy.getExceptionType(), strategy);
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Exception> ExceptionStrategy<T> getStrategy(Class<T> exceptionType) {
        return (ExceptionStrategy<T>) strategyMap.get(exceptionType);
    }
}