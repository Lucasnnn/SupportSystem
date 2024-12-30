package com.br.lucasnnn.support.application.domain.respository;

import com.br.lucasnnn.support.application.domain.entity.SupportLevel;
import com.br.lucasnnn.support.application.domain.entity.SupportRequest;

public interface NotificationRepository {
    String send(SupportRequest request, SupportLevel level);
}
