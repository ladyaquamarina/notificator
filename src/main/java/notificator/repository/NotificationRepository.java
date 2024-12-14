package notificator.repository;

import notificator.model.NotificationEntity;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends R2dbcRepository<NotificationEntity, String> {
}
