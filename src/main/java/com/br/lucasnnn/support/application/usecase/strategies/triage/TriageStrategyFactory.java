package com.br.lucasnnn.support.application.usecase.strategies.triage;

import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

@Component
public class TriageStrategyFactory {
    private final EnumMap<TriageMethod, TriageStrategy> strategies = new EnumMap<>(TriageMethod.class);

    public TriageStrategyFactory(List<TriageStrategy> strategyList) {
        Logging.info("Initializing TriageStrategyFactory with " + strategyList.size() + " strategies");

        for (TriageStrategy strategy : strategyList) {
            strategies.put(strategy.getMethod(), strategy);
        }

        Logging.info("TriageStrategyFactory initialized with " + strategies.size() + " strategies");
    }

    public TriageStrategy getStrategy(TriageMethod method) {
        Logging.info("Fetching strategy for method: " + method);

        TriageStrategy strategy = strategies.get(method);
        if (strategy == null) {
            Logging.info("No strategy found for method: " + method);
        } else {
            Logging.info("Found strategy: " + strategy.getClass().getSimpleName());
        }

        return strategy;
    }

    public TriageStrategy getStrategy(String method) {
        Logging.info("Fetching strategy for method: " + method);

        if (method == null) {
            method = TriageMethod.AVG.name();

            Logging.info("Method is null, using default: " + method);
        }

        TriageMethod triageMethod;

        try {
            triageMethod = TriageMethod.valueOf(method.toUpperCase());
            Logging.info("Successfully found triage method: " + triageMethod);
        } catch (IllegalArgumentException e) {
            String errorMessage = "The value '" + method + "' is not a valid triage method." +
                    " Allowed values are: " + String.join(", ", getValidTriageMethods());

            var error = new IllegalArgumentException(errorMessage, e);

            Logging.error(error.getMessage(), error);

            throw error;
        }

        return getStrategy(triageMethod);
    }

    private List<String> getValidTriageMethods() {
        return Arrays.stream(TriageMethod.values())
                .map(Enum::name)
                .toList();
    }
}
