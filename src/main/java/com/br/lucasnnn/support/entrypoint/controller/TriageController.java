package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.LevelTriageUseCase;
import com.br.lucasnnn.support.infra.utils.Logging;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/triage")
public class TriageController {
    private final LevelTriageUseCase levelTriageUseCase;

    @Autowired
    public TriageController(
            LevelTriageUseCase levelTriageUseCase
    ) {
        this.levelTriageUseCase = levelTriageUseCase;
    }

    @PatchMapping
    public ResponseEntity<String> supportTriage(
            @Valid @RequestBody() SupportRequest body,
            @RequestParam(value = "method", required = false) String method
    ) {
        Logging.info("Request received to triage the required level of support ");
        Logging.info("supportTriage body: " + body);

        String confirmation = levelTriageUseCase.execute(body, method);

        Logging.info("Successfully processed support triage.");

        return ResponseEntity
                .ok()
                .body(confirmation);
    }
}