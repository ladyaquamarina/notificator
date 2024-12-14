package notificator.service.impl;

import lombok.RequiredArgsConstructor;
import notificator.model.UserEntity;
import notificator.repository.UserRepository;
import notificator.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Mono<UserEntity> getById(String id) {
        return userRepository.findById(id);
    }
}
