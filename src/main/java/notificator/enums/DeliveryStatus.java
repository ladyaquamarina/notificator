package notificator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    SENT("Отправлено"),
    DELIVERED("Доставлено");

    private final String value;
}
