package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import com.br.lucasnnn.support.application.domain.respository.NotificationRepository;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageStrategyFactory;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class LevelTriageUseCase {
    private final LevelRepository levelRepository;
    private final NotificationRepository notificationRepository;
    private final TriageStrategyFactory triageStrategyFactory;

    @Autowired
    LevelTriageUseCase(
            LevelRepository levelRepository,
            NotificationRepository notificationRepository,
            TriageStrategyFactory triageStrategyFactory
    ) {
        this.levelRepository = levelRepository;
        this.notificationRepository = notificationRepository;
        this.triageStrategyFactory = triageStrategyFactory;
    }

    public String execute(SupportRequest request, String method) {
        Logging.info("Executing triage for support request: " + request);

        var levels = levelRepository.getAll();
        int size = levels.size();

        if (size == 0) {
            var error = new IllegalStateException("No active support levels.");

            Logging.error(error.getMessage(), error);

            throw error;
        }

        Logging.info("Found " + size + " levels of analysis");

        levels = levels.stream()
                .sorted(Comparator.comparing(SupportLevel::getLevel))
                .toList();

        Logging.info("Sorted support levels by skill.");

        var strategy = triageStrategyFactory.getStrategy(method);

        Logging.info("Using triage strategy: " + strategy.getClass().getSimpleName());

        SupportLevel supportLevel = strategy.execute(levels, request);

        Logging.info("Triage completed, sending notification for support level: " + supportLevel);

        String result = this.notificationRepository.send(request, supportLevel);

        Logging.info("Notification sent with result: " + result);

        return result;
    }
}
