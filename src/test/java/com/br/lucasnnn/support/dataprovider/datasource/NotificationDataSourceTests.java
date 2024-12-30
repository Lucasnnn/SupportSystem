package com.br.lucasnnn.support.dataprovider.datasource;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NotificationDataSourceTests {

    @Test
    public void testSendNotification() {
        // Arrange
        NotificationDataSource notificationDataSource = new NotificationDataSource();
        SupportRequest request = new SupportRequest(); // Preencha com dados de teste, se necessário
        SupportLevel level = new SupportLevel();
        level.setName("Pleno II");
        level.setEmail("pleno2teste.dev@example.com");

        // Act
        String result = notificationDataSource.send(request, level);

        // Assert
        String expectedMessage = "Ticket de suporte anexado a fila de Pleno II; Email enviado para pleno2teste.dev@example.com";
        assertEquals(expectedMessage, result);
    }
}