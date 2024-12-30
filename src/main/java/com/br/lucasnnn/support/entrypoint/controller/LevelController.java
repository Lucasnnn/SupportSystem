package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.usecase.CreateLevelUseCase;
import com.br.lucasnnn.support.infra.utils.Logging;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level")
public class LevelController {
    private final CreateLevelUseCase createLevelUseCase;

    @Autowired
    public LevelController(
            CreateLevelUseCase createLevelUseCase
    ) {
        this.createLevelUseCase = createLevelUseCase;
    }

    @PostMapping
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