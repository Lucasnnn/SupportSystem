package com.br.lucasnnn.support.application.usecase;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.respository.LevelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class CreateLevelUseCaseTests {

    @InjectMocks
    private CreateLevelUseCase createLevelUseCase;

    @Mock
    private LevelRepository levelRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testExecute() {
        // Arrange
        SupportLevel supportLevel = new SupportLevel();
        supportLevel.setLevel(1);
        supportLevel.setName("Java Developer");
        supportLevel.setEmail("java.dev@example.com");

        // Act
        createLevelUseCase.execute(supportLevel);

        // Assert
        verify(levelRepository).create(supportLevel);
    }
}