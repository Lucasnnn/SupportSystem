package com.br.lucasnnn.support.dataprovider.mapper;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.dataprovider.entity.Level;

public class LevelMapper {

    private LevelMapper() {
        //
    }

    public static SupportLevel map(Level level) {
        var supportLevel = new SupportLevel();

        supportLevel.setLevel(level.getSkill());
        supportLevel.setName(level.getName());
        supportLevel.setEmail(level.getEmail());

        return supportLevel;
    }

    public static Level map(SupportLevel supportLevel) {
        var level = new Level();

        level.setName(supportLevel.getName());
        level.setSkill(supportLevel.getLevel());
        level.setEmail(supportLevel.getEmail());

        return level;
    }

}
