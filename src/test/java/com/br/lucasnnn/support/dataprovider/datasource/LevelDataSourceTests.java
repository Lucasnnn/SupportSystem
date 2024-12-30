package com.br.lucasnnn.support.dataprovider.datasource;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.dataprovider.datasource.db.LevelDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LevelDataSourceTests {

    @InjectMocks
    private LevelDataSource levelDataSource;

    @Mock
    private LevelDB levelDB;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAll() {
        // Arrange
        List<com.br.lucasnnn.support.dataprovider.entity.Level> levelEntities = new ArrayList<>();
        com.br.lucasnnn.support.dataprovider.entity.Level levelEntity = new com.br.lucasnnn.support.dataprovider.entity.Level();
        levelEntity.setSkill(1);
        levelEntity.setName("Senior");
        levelEntity.setEmail("seniorteste.dev@example.com");
        levelEntities.add(levelEntity);

        when(levelDB.findAll()).thenReturn(levelEntities);

        // Act
        List<SupportLevel> supportLevels = levelDataSource.getAll();

        // Assert
        assertEquals(1, supportLevels.size());
        assertEquals("Senior", supportLevels.get(0).getName());
        assertEquals("seniorteste.dev@example.com", supportLevels.get(0).getEmail());
        verify(levelDB).findAll();
    }

    @Test
    public void testCreate() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(1);
        supportLevel.setName("Junior");
        supportLevel.setEmail("juniorteste.dev@example.com");

        // Act
        levelDataSource.create(supportLevel);

        // Assert
        verify(levelDB).saveAndFlush(any());
    }

    @Test
    public void testCreateNullSupportLevel() {
        // Act
        levelDataSource.create(null);

        // Assert
        verify(levelDB, never()).saveAndFlush(any());
    }
}