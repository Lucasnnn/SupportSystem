package com.br.lucasnnn.support.application.domain.entity;

import jakarta.validation.constraints.NotNull;

public class SupportLevel extends BaseEntity {
    @NotNull(message = "The level field cannot be null.")
    private Integer level;

    @NotNull(message = "The name field cannot be null.")
    private String name;

    @NotNull(message = "The email field cannot be null.")
    private String email;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}