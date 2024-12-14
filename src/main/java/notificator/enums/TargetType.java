package notificator.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TargetType {
    PUSH("push"),
    EMAIL("email"),
    PHONE("телефон");

    private final String value;
}
