package com.br.lucasnnn.support.dataprovider.mapper;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.dataprovider.entity.Level;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LevelMapperTests {

    @Test
    public void testMapLevelToSupportLevel() {
        // Arrange
        Level level = new Level();
        level.setSkill(2);
        level.setName("Estagiario");
        level.setEmail("estagiarioteste.dev@example.com");

        // Act
        SupportLevel supportLevel = LevelMapper.map(level);

        // Assert
        assertEquals(2, supportLevel.getLevel());
        assertEquals("Estagiario", supportLevel.getName());
        assertEquals("estagiarioteste.dev@example.com", supportLevel.getEmail());
    }

    @Test
    public void testMapNullLevelToSupportLevel() {
        // Act
        SupportLevel supportLevel = LevelMapper.map((Level) null);

        // Assert
        assertNull(supportLevel);
    }

    @Test
    public void testMapSupportLevelToLevel() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(10);
        supportLevel.setName("Especialista");
        supportLevel.setEmail("especteste.dev@example.com");

        // Act
        Level level = LevelMapper.map(supportLevel);

        // Assert
        assertEquals(10, level.getSkill());
        assertEquals("Especialista", level.getName());
        assertEquals("especteste.dev@example.com", level.getEmail());
    }

    @Test
    public void testMapNullSupportLevelToLevel() {
        // Act
        Level level = LevelMapper.map((SupportLevel) null);

        // Assert
        assertNull(level);
    }
}