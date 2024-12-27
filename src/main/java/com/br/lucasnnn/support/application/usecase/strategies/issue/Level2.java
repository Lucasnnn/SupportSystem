package com.br.lucasnnn.support.application.usecase.strategies.issue;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.enums.SupportLevels;
import org.springframework.stereotype.Service;

@Service(SupportLevels.LEVEL_2)
public class Level2 implements LevelStrategy {

    @Override
    public boolean resolveIssue(SupportRequest request) {
        System.out.println("Level 2 support handling request: " +
                request.getIssue());
        // Simulate problem-solving attempt
        return false; // Simulação: não resolvido.
    }

}