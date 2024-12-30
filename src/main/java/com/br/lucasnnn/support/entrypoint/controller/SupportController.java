package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.CreateLevelUseCase;
import com.br.lucasnnn.support.application.usecase.LevelTriageUseCase;
import com.br.lucasnnn.support.infra.utils.Logging;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/support")
public class SupportController {
    private final LevelTriageUseCase levelTriageUseCase;
    private final CreateLevelUseCase createLevelUseCase;

    @Autowired
    public SupportController(
            LevelTriageUseCase levelTriageUseCase,
            CreateLevelUseCase createLevelUseCase
    ) {
        this.levelTriageUseCase = levelTriageUseCase;
        this.createLevelUseCase = createLevelUseCase;
    }

    @PatchMapping("/triage")
    public ResponseEntity<String> supportTriage(
            @Valid @RequestBody() SupportRequest body
    ) {
        Logging.info("Request received to triage the required level of support ");
        Logging.info("supportTriage body: " + body);

        String confirmation = levelTriageUseCase.execute(body);

        Logging.info("Successfully processed support triage.");

        return ResponseEntity
                .ok()
                .body(confirmation);
    }

    @PostMapping("/level")
    public ResponseEntity<SupportLevel> createSupportLevel(
            @Valid @RequestBody() SupportLevel body
    ) {
        Logging.info("Request received to create new level of support ");
        Logging.info("createSupportLevel body: " + body);

        createLevelUseCase.execute(body);

        Logging.info("Successfully create level of support.");

        return ResponseEntity
                .ok()
                .body(body);
    }
}