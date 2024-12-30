package com.br.lucasnnn.support.dataprovider.mapper;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.dataprovider.entity.Level;
import com.br.lucasnnn.support.infra.utils.Logging;

public class LevelMapper {

    private LevelMapper() {
        //
    }

    public static SupportLevel map(Level level) {
        if (level == null) {
            Logging.info("Attempted to map a null Level object to SupportLevel.");

            return null;
        }

        Logging.info("Mapping Level object to SupportLevel: " + level);

        var supportLevel = new SupportLevel();
        supportLevel.setLevel(level.getSkill());
        supportLevel.setName(level.getName());
        supportLevel.setEmail(level.getEmail());

        Logging.info("Mapped SupportLevel: " + supportLevel);

        return supportLevel;
    }

    public static Level map(SupportLevel supportLevel) {
        if (supportLevel == null) {
            Logging.info("Attempted to map a null SupportLevel object to Level.");
            return null;
        }

        Logging.info("Mapping SupportLevel object to Level: " + supportLevel);

        var level = new Level();
        level.setName(supportLevel.getName());
        level.setSkill(supportLevel.getLevel());
        level.setEmail(supportLevel.getEmail());

        Logging.info("Mapped Level: " + level);

        return level;
    }

}
