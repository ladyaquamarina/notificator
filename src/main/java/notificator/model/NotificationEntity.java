package notificator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import notificator.enums.DeliveryStatus;
import notificator.enums.TargetType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("notification")
public class NotificationEntity implements Persistable<String> {
    @Id
    @Column("id")
    private String id;

    @Column("user_id")
    private String userId;

    @Column("message")
    private String message;

    @Column("target_type")
    private TargetType targetType;

    @Column("target_value")
    private String targetValue;

    @Column("status")
    private DeliveryStatus status;

    @Column("created_at")
    private LocalDateTime createdAt;

    @Transient
    private boolean isNew = false;
}
