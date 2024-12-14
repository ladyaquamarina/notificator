package notificator.service;

import notificator.model.NotificationEntity;
import reactor.core.publisher.Mono;

public interface PhoneService {
    Mono<NotificationEntity> sendPhone(NotificationEntity notification);
}
