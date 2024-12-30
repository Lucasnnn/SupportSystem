package com.br.lucasnnn.support.dataprovider.datasource;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import com.br.lucasnnn.support.dataprovider.datasource.db.LevelDB;
import com.br.lucasnnn.support.dataprovider.mapper.LevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LevelDataSource implements LevelRepository {

    private final LevelDB levelDB;

    @Autowired
    public LevelDataSource(LevelDB levelDB) {
        this.levelDB = levelDB;
    }

    @Override
    public List<SupportLevel> getAll() {
        return levelDB.findAll().stream()
                .map(LevelMapper::map)
                .toList();
    }

    @Override
    public void create(SupportLevel supportLevel) {
        levelDB.saveAndFlush(LevelMapper.map(supportLevel));
    }
}