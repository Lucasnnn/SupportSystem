package com.br.lucasnnn.support.dataprovider.datasource;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import com.br.lucasnnn.support.dataprovider.datasource.db.LevelDB;
import com.br.lucasnnn.support.dataprovider.mapper.LevelMapper;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class LevelDataSource implements LevelRepository {
    private final LevelDB levelDB;

    @Autowired
    public LevelDataSource(LevelDB levelDB) {
        this.levelDB = levelDB;
    }

    @Override
    public List<SupportLevel> getAll() {
        Logging.info("Fetching all SupportLevel records from the database.");

        List<SupportLevel> supportLevels = levelDB.findAll().stream()
                .map(LevelMapper::map)
                .toList();

        Logging.info("Fetched " + supportLevels.size() + " SupportLevel records.");

        return supportLevels;
    }

    @Override
    public void create(SupportLevel supportLevel) {
        if (supportLevel == null) {
            Logging.info("Attempted to create a null SupportLevel record.");

            return;
        }

        Logging.info("Creating SupportLevel record: " + supportLevel);

        var levelEntity = LevelMapper.map(supportLevel);

        levelDB.saveAndFlush(Objects.requireNonNull(levelEntity));

        Logging.info("SupportLevel record created successfully: " + supportLevel);
    }
}
