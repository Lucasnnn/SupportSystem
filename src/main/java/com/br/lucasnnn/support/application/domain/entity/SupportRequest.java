package com.br.lucasnnn.support.application.domain.entity;

public class SupportRequest extends BaseEntity {
    private String issue;

    public void setIssue(String issue) {
        this.issue = issue;
    }

    public String getIssue() {
        return issue;
    }
}