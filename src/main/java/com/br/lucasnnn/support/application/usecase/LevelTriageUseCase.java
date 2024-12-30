package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import com.br.lucasnnn.support.application.domain.respository.NotificationRepository;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelTriageUseCase {
    private final LevelRepository levelRepository;
    private final NotificationRepository notificationRepository;

    @Autowired
    LevelTriageUseCase(
            LevelRepository levelRepository,
            NotificationRepository notificationRepository
    ) {
        this.levelRepository = levelRepository;
        this.notificationRepository = notificationRepository;
    }

    public String execute(SupportRequest request) {
        Logging.info("Executing triage for support request: " + request);

        var levels = levelRepository.getAll();
        Logging.info("Found " + levels.size() + " levels of analysis");

        int levelIndex = calculateLevelIndex(request, levels.size());
        Logging.info("Assigned support request to level " + (levelIndex + 1));

        SupportLevel supportLevel = levels.get(levelIndex);

        return this.notificationRepository.send(request, supportLevel);
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