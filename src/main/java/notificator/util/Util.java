package notificator.util;

import org.springframework.beans.factory.annotation.Value;

public class Util {
    @Value("${spring.mail.username}")
    private static String emailOfSender;
    @Value("$phone")
    private static String phoneOfSender;

    public static final String SUCCESS = "Success";
    public static final String EMAIL_NOTIFICATION_SUBJECT = "Уведомление от Pocket Finance";
    public static final String POCKET_FINANCE_NOREPLY_EMAIL = emailOfSender;
    public static final String POCKET_FINANCE_PHONE_NUMBER = phoneOfSender;
}
