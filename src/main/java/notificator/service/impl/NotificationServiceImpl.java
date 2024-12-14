package notificator.service.impl;

import lombok.RequiredArgsConstructor;
import notificator.enums.DeliveryStatus;
import notificator.enums.TargetType;
import notificator.model.NotificationEntity;
import notificator.model.UserEntity;
import notificator.repository.NotificationRepository;
import notificator.service.EmailService;
import notificator.service.NotificationService;
import notificator.service.PhoneService;
import notificator.service.UserService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

import static notificator.util.Util.SUCCESS;

@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final UserService userService;
    private final EmailService emailService;
    private final PhoneService phoneService;

    @Override
    public Mono<String> sendEverywhere(String userId, String meessage) {
        return Mono.just(createNotification(userId, userId, meessage))
                .flatMap(this::sendPush)
                .flatMap(this::sendEmail)
                .flatMap(this::sendPhone)
                .map(entity -> SUCCESS);
    }

    @Override
    public Mono<String> sendPush(String userId, String message) {
        return Mono.just(createNotification(userId, userId, message))
                .flatMap(this::sendPush)
                .map(entity -> SUCCESS);
    }

    @Override
    public Mono<String> sendEmail(String userId, String message) {
        return userService.getById(userId)
                .map(UserEntity::getEmail)
                .map(email -> createNotification(userId, email, message))
                .flatMap(this::sendEmail)
                .map(entity -> SUCCESS);
    }

    @Override
    public Mono<String> sendPhone(String userId, String message) {
        return userService.getById(userId)
                .map(UserEntity::getPhone)
                .map(phone -> createNotification(userId, phone, message))
                .flatMap(this::sendPhone)
                .map(entity -> SUCCESS);
    }

    private Mono<NotificationEntity> sendPush(NotificationEntity notification) {
        return Mono.just(setTargetType(notification, TargetType.PUSH))
                .map(this::setDelivered)
                .flatMap(notificationRepository::save);
    }

    private Mono<NotificationEntity> sendEmail(NotificationEntity notification) {
        return notificationRepository.save(setTargetType(notification, TargetType.EMAIL))
                .flatMap(emailService::sendEmail)
                .map(this::setDelivered)
                .flatMap(notificationRepository::save);
    }

    private Mono<NotificationEntity> sendPhone(NotificationEntity notification) {
        return notificationRepository.save(setTargetType(notification, TargetType.PHONE))
                .flatMap(phoneService::sendPhone)
                .map(this::setDelivered)
                .flatMap(notificationRepository::save);
    }

    private NotificationEntity createNotification(String userId, String targetValue, String message) {
        NotificationEntity notification = new NotificationEntity();
        notification.setId(UUID.randomUUID().toString());
        notification.setUserId(userId);
        notification.setMessage(message);
        notification.setTargetValue(targetValue);
        notification.setStatus(DeliveryStatus.SENT);
        notification.setCreatedAt(LocalDateTime.now());
        notification.setNew(true);
        return notification;
    }

    private NotificationEntity setTargetType(NotificationEntity notification, TargetType targetType) {
        notification.setTargetType(targetType);
        return notification;
    }

    private NotificationEntity setDelivered(NotificationEntity notification) {
        notification.setStatus(DeliveryStatus.DELIVERED);
        notification.setNew(false);
        return notification;
    }
}
