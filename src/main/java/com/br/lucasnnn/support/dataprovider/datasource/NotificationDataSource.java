package com.br.lucasnnn.support.dataprovider.datasource;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;
import com.br.lucasnnn.support.application.domain.respository.NotificationRepository;
import com.br.lucasnnn.support.infra.utils.Logging;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDataSource implements NotificationRepository {

    @Override
    public String send(SupportRequest request, SupportLevel level) {
        Logging.info("Send notification to level " + level.getName());

        Logging.info("Notification to email " + level.getEmail());

        return "Ticket de suporte anexado a fila de " +
                level.getName() +
                "; Email enviado para " + level.getEmail();
    }
}