package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateLevelUseCase {
    private final LevelRepository levelRepository;

    @Autowired
    CreateLevelUseCase(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    public void execute(SupportLevel level) {
        Logging.info("Executing creating for new support level: " + level);

        levelRepository.create(level);

        Logging.info("New support created ");
    }
}