package com.br.lucasnnn.support.dataprovider.datasource.db;

import com.br.lucasnnn.support.dataprovider.entity.Level;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class LevelDBTests {

    @Autowired
    private LevelDB levelDB;

    @BeforeEach
    public void setUp() {
        // Configurações iniciais, se necessário
    }

    @Test
    @Sql(scripts = "/test-data.sql") // Carrega dados de teste, se necessário
    public void testFindAll() {
        // Act
        List<Level> levels = levelDB.findAll();

        // Assert
        assertEquals(2, levels.size()); // Verifique se o número de registros é o esperado
    }

    @Test
    public void testSaveAndFlush() {
        // Arrange
        Level level = new Level();
        level.setSkill(1);
        level.setName("Pleno");
        level.setEmail("plenoteste.dev@example.com");

        // Act
        Level savedLevel = levelDB.saveAndFlush(level);

        // Assert
        assertEquals(level.getName(), savedLevel.getName());
        assertEquals(level.getEmail(), savedLevel.getEmail());
    }
}