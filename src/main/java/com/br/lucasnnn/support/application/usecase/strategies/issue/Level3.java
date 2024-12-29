package com.br.lucasnnn.support.application.usecase.strategies.issue;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.enums.SupportLevels;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.stereotype.Service;

@Service(SupportLevels.LEVEL_3)
public class Level3 implements LevelStrategy {

    @Override
    public boolean resolveIssue(SupportRequest request) {
        Logging.info("Level 3 support handling request: " + request.getIssue());

        // Aqui a resolução é considerada garantida.
        return false;
    }

}