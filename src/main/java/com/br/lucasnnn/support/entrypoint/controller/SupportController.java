package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.usecase.ResolveIssueUseCase;
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
        resolveIssueUseCase.execute(body, level);

        return ResponseEntity.ok().build();
    }
}