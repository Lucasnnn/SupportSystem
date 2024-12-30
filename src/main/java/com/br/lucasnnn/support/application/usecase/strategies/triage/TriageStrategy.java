package com.br.lucasnnn.support.application.usecase.strategies.triage;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;

import java.util.List;

public interface TriageStrategy {
    SupportLevel execute(List<SupportLevel> levels, SupportRequest request);

    TriageMethod getMethod();
}
