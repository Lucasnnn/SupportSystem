package com.br.lucasnnn.support.entrypoint.controller;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.usecase.CreateLevelUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class LevelControllerTests {

    @InjectMocks
    private LevelController levelController;

    @Mock
    private CreateLevelUseCase createLevelUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateSupportLevel() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel(); // Preencha com dados de teste

        // Act
        ResponseEntity<SupportLevel> response = levelController.createSupportLevel(supportLevel);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(supportLevel, response.getBody());
        verify(createLevelUseCase).execute(supportLevel);
    }
}