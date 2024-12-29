package com.br.lucasnnn.support.application.usecase.strategies.issue;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;

public interface LevelStrategy {
    boolean resolveIssue(SupportRequest request);
}
