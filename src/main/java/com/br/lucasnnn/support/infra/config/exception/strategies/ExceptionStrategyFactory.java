package com.br.lucasnnn.support.infra.config.exception.strategies;

import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ExceptionStrategyFactory {
    private final Map<Class<? extends Exception>, ExceptionStrategy<?>> strategyMap = new HashMap<>();

    public ExceptionStrategyFactory(List<ExceptionStrategy<?>> strategies) {
        Logging.info("Initializing ExceptionStrategyFactory with " + strategies.size() + " strategies");

        for (ExceptionStrategy<?> strategy : strategies) {
            var exceptionType = strategy.getExceptionType();

            strategyMap.put(exceptionType, strategy);

            Logging.info("Registered strategy for exception type: " + exceptionType.getName());
        }

        Logging.info("Total strategies registered: " + strategyMap.size());
    }

    @SuppressWarnings("unchecked")
    public <T extends Exception> ExceptionStrategy<T> getStrategy(Class<T> exceptionType) {
        Logging.info("Retrieving strategy for exception type: " + exceptionType.getName());

        ExceptionStrategy<T> strategy = (ExceptionStrategy<T>) strategyMap.get(exceptionType);

        if (strategy == null) {
            Logging.info("No strategy found for exception type: " + exceptionType.getName());
        } else {
            Logging.info("Strategy found for exception type: " + exceptionType.getName());
        }

        return strategy;
    }
}