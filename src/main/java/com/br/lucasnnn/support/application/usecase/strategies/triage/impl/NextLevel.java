package com.br.lucasnnn.support.application.usecase.strategies.triage.impl;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageMethod;
import com.br.lucasnnn.support.application.usecase.strategies.triage.TriageStrategy;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class NextLevel implements TriageStrategy {

    @Override
    public TriageMethod getMethod() {
        return TriageMethod.NEXT_LEVEL;
    }

    @Override
    public SupportLevel execute(List<SupportLevel> levels, SupportRequest request) {
        Logging.info("Request for higher level support");

        var scopeLevel = extractScopeLevel(levels);

        var nextLevel = findFirstHigherLevel(scopeLevel, levels);

        if (Objects.isNull(nextLevel)) {
            throw new IllegalArgumentException("Cannot escalate to a outher level support: already at the highest level.");
        }

        return nextLevel;
    }

    private SupportLevel extractScopeLevel(List<SupportLevel> levels) {
        //  User user = SecurityContextHolder.getContext().getAuthentication();
        //  String scopes = user.getScopes();

        List<String> scopes = new ArrayList<>();
        scopes.add("admin_internal");
        scopes.add("support");
        scopes.add("JUNIOR");
        scopes.add("graos_cia");

        return levels.stream()
                .filter(level -> scopes.contains(level.getName().trim()))
                .findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("User  not authorized: scope not found.")
                );
    }

    private SupportLevel findFirstHigherLevel(SupportLevel currentLevel, List<SupportLevel> levels) {
        return levels.stream()
                .filter(level -> level.getLevel() > currentLevel.getLevel())
                .findFirst()
                .orElse(null);
    }
}
