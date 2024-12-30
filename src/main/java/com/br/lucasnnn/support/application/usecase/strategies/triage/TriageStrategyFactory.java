package com.br.lucasnnn.support.application.usecase.strategies.triage;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

@Component
public class TriageStrategyFactory {
    private final EnumMap<TriageMethod, TriageStrategy> strategies = new EnumMap<>(TriageMethod.class);

    public TriageStrategyFactory(List<TriageStrategy> strategyList) {
        for (TriageStrategy strategy : strategyList) {
            strategies.put(strategy.getMethod(), strategy);
        }
    }

    public TriageStrategy getStrategy(TriageMethod method) {
        return strategies.get(method);
    }

    public TriageStrategy getStrategy(String method) {
        if (method == null) {
            method = TriageMethod.AVG.name();
        }

        TriageMethod triageMethod;

        try {
            triageMethod = TriageMethod.valueOf(method.toUpperCase());
        } catch (IllegalArgumentException e) {
            String errorMessage = "The value '" + method + "' not a valid triage method." +
                    " Allowed values are: " + String.join(", ", getValidTriageMethods());

            throw new IllegalArgumentException(
                    errorMessage
            );
        }

        return getStrategy(triageMethod);
    }

    private List<String> getValidTriageMethods() {
        return Arrays.stream(TriageMethod.values())
                .map(Enum::name)
                .toList();
    }
}
