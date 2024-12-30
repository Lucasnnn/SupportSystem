package com.br.lucasnnn.support.application.domain.respository;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;

import java.util.List;

public interface LevelRepository {
    List<SupportLevel> getAll();

    void create(SupportLevel level);
}
