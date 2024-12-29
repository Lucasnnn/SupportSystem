package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.enums.SupportLevels;
import com.br.lucasnnn.support.application.usecase.strategies.issue.LevelStrategy;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ResolveIssueUseCase {
    private final Map<String, LevelStrategy> levelStrategies;

    @Autowired
    public ResolveIssueUseCase(Map<String, LevelStrategy> levelStrategies) {
        this.levelStrategies = levelStrategies;
    }

    public void execute(SupportRequest request, String minLevel) {
        Logging.info("Executing issue resolution for request: " + request);

        minLevel = checkLevel(minLevel);

        Logging.info("Minimum support level set to: " + minLevel);

        var strategies = sortStrategies(minLevel);

        processRequest(request, strategies);
    }

    private String checkLevel(String minLevel) {
        if (minLevel == null || minLevel.isBlank()) {
            minLevel = SupportLevels.LEVEL_1;

            Logging.info("Minimum level was null or blank. Defaulting to: " + minLevel);
        }

        if (!SupportLevels.isValid(minLevel)) {
            var error = new IllegalArgumentException("Invalid support level: " + minLevel);

            Logging.error("Invalid support level provided: " + minLevel, error);

            throw error;
        }

        Logging.info("Valid support level confirmed: " + minLevel);

        return minLevel;
    }

    private List<Map.Entry<String, LevelStrategy>> sortStrategies(String minLevel) {
        Logging.info("Sorting strategies based on minimum level: " + minLevel);

        return levelStrategies.entrySet()
                .stream()
                .filter(
                        entry ->
                                entry.getKey().compareTo(minLevel) >= 0
                )
                .sorted(Map.Entry.comparingByKey())
                .toList();
    }

    private void processRequest(SupportRequest request, List<Map.Entry<String, LevelStrategy>> sortedEntries) {
        String sortedLevels = sortedEntries.stream()
                .map(Map.Entry::getKey)
                .collect(Collectors.joining(", "));

        Logging.info("Processing request with sorted strategies: " + sortedLevels);

        for (int i = 0; i < sortedEntries.size(); i++) {
            Map.Entry<String, LevelStrategy> entry = sortedEntries.get(i);
            String level = entry.getKey();
            LevelStrategy strategy = entry.getValue();

            boolean isLastLevel = (i == sortedEntries.size() - 1);
            boolean resolved = strategy.resolveIssue(request);

            if (!resolved) {
                if (!isLastLevel) {
                    Logging.info("Level " + level + " failed. Moving to the next level.");

                    continue;
                }
            }

            Logging.info("Level " + level + " processed successfully.");
        }
    }
}