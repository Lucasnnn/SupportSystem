package com.br.lucasnnn.support.application.usecase.strategies.triage.impl;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageMethod;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageStrategy;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Avg implements TriageStrategy {

    @Override
    public TriageMethod getMethod() {
        return TriageMethod.AVG;
    }

    @Override
    public SupportLevel execute(List<SupportLevel> levels, SupportRequest request) {
        int levelIndex = calculateLevelIndex(request, levels.size());

        Logging.info("Assigned support request to level " + (levelIndex + 1));

        return levels.get(levelIndex);
    }

    private int calculateLevelIndex(SupportRequest request, int numberOfLevels) {
        double avgRequest = calculateAverageRequest(request);
        double rangePerLevel = 10.0 / numberOfLevels;

        Logging.info("Average priority and complexity: " + avgRequest);
        Logging.info("Each level will cover a range of: " + rangePerLevel);

        int levelIndex = (int) (avgRequest / rangePerLevel);

        if (levelIndex >= numberOfLevels) {
            levelIndex = numberOfLevels - 1;
        }

        return levelIndex;
    }

    private double calculateAverageRequest(SupportRequest request) {
        Integer priority = request.getPriority();
        Integer complexity = request.getComplexity();

        return (double) (priority + complexity) / 2;
    }
}
