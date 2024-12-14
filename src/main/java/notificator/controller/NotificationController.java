package notificator.controller;

import lombok.RequiredArgsConstructor;
import notificator.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notificator")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping
    public Mono<String> sendNotification(@RequestParam String userId,
                                         @RequestBody String message) {
        return notificationService.sendEverywhere(userId, message);
    }

    @PostMapping("/email")
    public Mono<String> sendEmailNotification(@RequestParam String userId,
                                              @RequestBody String message) {
        return notificationService.sendEmail(userId, message);
    }

    @PostMapping("/push")
    public Mono<String> sendPushNotification(@RequestParam String userId,
                                             @RequestBody String message) {
        return notificationService.sendPush(userId, message);
    }

    @PostMapping("/phone")
    public Mono<String> sendPhoneNotification(@RequestParam String userId,
                                              @RequestBody String message) {
        return notificationService.sendPhone(userId, message);
    }
}
