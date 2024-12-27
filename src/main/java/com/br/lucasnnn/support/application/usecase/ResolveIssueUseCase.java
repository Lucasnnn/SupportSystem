package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.enums.SupportLevels;
import com.br.lucasnnn.support.application.usecase.strategies.issue.LevelStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ResolveIssueUseCase {
    private final Map<String, LevelStrategy> levelStrategies;

    @Autowired
    public ResolveIssueUseCase(Map<String, LevelStrategy> levelStrategies) {
        this.levelStrategies = levelStrategies;
    }

    public void execute(SupportRequest request, String minLevel) {
        minLevel = checkLevel(minLevel);

        var strategies = sortStrategies(minLevel);

        processRequest(request, strategies);
    }

    private String checkLevel(String minLevel) {
        if (minLevel == null || minLevel.isBlank()) {
            minLevel = SupportLevels.LEVEL_1;
        }

        if (!SupportLevels.isValid(minLevel)) {
            throw new IllegalArgumentException("Invalid support level: " + minLevel);
        }

        return minLevel;
    }

    private List<Map.Entry<String, LevelStrategy>> sortStrategies(String minLevel) {
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
        for (int i = 0; i < sortedEntries.size(); i++) {
            Map.Entry<String, LevelStrategy> entry = sortedEntries.get(i);
            String level = entry.getKey();
            LevelStrategy strategy = entry.getValue();

            boolean isLastLevel = (i == sortedEntries.size() - 1);
            boolean resolved = strategy.resolveIssue(request);

            if (!resolved) {
                if (!isLastLevel) {
                    System.out.println("Level " + level + " failed. Moving to the next level.");

                    continue;
                }
            }

            System.out.println("Level " + level + " processed successfully.");
        }
    }
}