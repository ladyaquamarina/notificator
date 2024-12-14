package notificator.service.impl;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.RequiredArgsConstructor;
import notificator.model.NotificationEntity;
import notificator.service.PhoneService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static notificator.util.Util.POCKET_FINANCE_PHONE_NUMBER;
import static notificator.util.Util.SUCCESS;

@Service
@RequiredArgsConstructor
public class PhoneServiceImpl implements PhoneService {
    @Override
    public Mono<NotificationEntity> sendPhone(NotificationEntity notification) {
        return Mono.just(Message.creator(
                new PhoneNumber(notification.getTargetValue()),
                new PhoneNumber(POCKET_FINANCE_PHONE_NUMBER),
                notification.getMessage()).create())
                .map(message -> notification);
    }
}
