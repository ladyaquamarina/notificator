package notificator.service;

import reactor.core.publisher.Mono;

public interface NotificationService {

    Mono<String> sendEverywhere(String userId, String meessage);
    Mono<String> sendPush(String userId, String message);
    Mono<String> sendEmail(String userId, String message);
    Mono<String> sendPhone(String userId, String message);
}
