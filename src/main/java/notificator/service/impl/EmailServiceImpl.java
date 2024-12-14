package notificator.service.impl;

import lombok.RequiredArgsConstructor;
import notificator.model.NotificationEntity;
import notificator.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static notificator.util.Util.EMAIL_NOTIFICATION_SUBJECT;
import static notificator.util.Util.POCKET_FINANCE_NOREPLY_EMAIL;
import static notificator.util.Util.SUCCESS;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public Mono<NotificationEntity> sendEmail(NotificationEntity notification) {
        SimpleMailMessage email = createEmail(notification);
        javaMailSender.send(email);
        return Mono.just(notification);
    }

    private SimpleMailMessage createEmail(NotificationEntity notification) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(notification.getTargetValue());
        simpleMailMessage.setSubject(EMAIL_NOTIFICATION_SUBJECT);
        simpleMailMessage.setFrom(POCKET_FINANCE_NOREPLY_EMAIL);
        simpleMailMessage.setText(notification.getMessage());
        return simpleMailMessage;
    }
}
