package notificator.service;

import notificator.model.UserEntity;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<UserEntity> getById(String id);
}
