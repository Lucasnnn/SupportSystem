package com.br.lucasnnn.support.dataprovider.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LevelTests {

    @Test
    public void testGettersAndSetters() {
        // Arrange
        Level level = new Level();
        String expectedId = "123e4567-e89b-12d3-a456-426614174000";
        Integer expectedSkill = 5;
        String expectedName = "Junior";
        String expectedEmail = "juniorteste.dev@example.com";

        // Act
        level.setId(expectedId);
        level.setSkill(expectedSkill);
        level.setName(expectedName);
        level.setEmail(expectedEmail);

        // Assert
        assertEquals(expectedId, level.getId());
        assertEquals(expectedSkill, level.getSkill());
        assertEquals(expectedName, level.getName());
        assertEquals(expectedEmail, level.getEmail());
    }
}