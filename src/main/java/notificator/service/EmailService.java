package notificator.service;

import notificator.model.NotificationEntity;
import reactor.core.publisher.Mono;

public interface EmailService {
    Mono<NotificationEntity> sendEmail(NotificationEntity notification);
}
