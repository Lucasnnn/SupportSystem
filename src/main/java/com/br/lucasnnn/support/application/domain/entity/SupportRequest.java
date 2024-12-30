package com.br.lucasnnn.support.application.domain.entity;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class SupportRequest extends BaseEntity {
    @NotNull(message = "The id field cannot be null.")
    private String id;

    @NotNull(message = "The priority field cannot be null.")
    @Min(value = 1, message = "The priority must be at least 1.")
    @Max(value = 10, message = "The priority must be at most 10.")
    private Integer priority;

    @NotNull(message = "The complexity field cannot be null.")
    @Min(value = 1, message = "The complexity must be at least 1.")
    @Max(value = 10, message = "The complexity must be at most 10.")
    private Integer complexity;

    public Integer getComplexity() {
        return complexity;
    }

    public void setComplexity(Integer complexity) {
        this.complexity = complexity;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}