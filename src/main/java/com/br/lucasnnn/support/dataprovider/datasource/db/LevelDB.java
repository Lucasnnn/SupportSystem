package com.br.lucasnnn.support.dataprovider.datasource.db;

import com.br.lucasnnn.support.dataprovider.entity.Level;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LevelDB extends JpaRepository<Level, Long> {
    @Override
    List<Level> findAll();

    @Override
    Level saveAndFlush(Level level);
}
