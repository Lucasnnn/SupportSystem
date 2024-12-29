package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.ResolveIssueUseCase;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/support")
public class SupportController {
    private final ResolveIssueUseCase resolveIssueUseCase;

    @Autowired
    public SupportController(ResolveIssueUseCase resolveIssueUseCase) {
        this.resolveIssueUseCase = resolveIssueUseCase;
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> process(
            @RequestBody() SupportRequest body,
            @RequestParam(value = "level", required = false) String level
    ) {
        Logging.info("Received request to process support issue with level: " + level);
        Logging.info("SupportRequest body: " + body);

        resolveIssueUseCase.execute(body, level);

        Logging.info("Successfully processed support issue.");
        return ResponseEntity.ok().build();
    }
}