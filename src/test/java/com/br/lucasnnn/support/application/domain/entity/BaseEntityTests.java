package com.br.lucasnnn.support.application.domain.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseEntityTests {

    // Classe de teste que estende BaseEntity
    private static class TestEntity extends BaseEntity {
        private String name = "Test Name";
        private int age = 30;
        private boolean active = true;

        // Construtor
        public TestEntity() {
        }
    }

    @Test
    public void testToString() {
        // Arrange
        TestEntity entity = new TestEntity();

        // Act
        String result = entity.toString();

        // Assert
        String expected = "TestEntity { name='Test Name', age='30', active='true' } ";
        assertEquals(expected, result, "The toString() method should return the correct string representation.");
    }

    @Test
    public void testToStringWithNullField() {
        // Classe de teste que estende BaseEntity
        class TestEntityWithNullField extends BaseEntity {
            private String name = null;
            private int age = 30;

            // Construtor
            public TestEntityWithNullField() {
            }
        }

        // Arrange
        TestEntityWithNullField entity = new TestEntityWithNullField();

        // Act
        String result = entity.toString();

        // Assert
        String expected = "TestEntityWithNullField { name='null', age='30' } ";
        assertEquals(expected, result, "The toString() method should handle null fields correctly.");
    }
}