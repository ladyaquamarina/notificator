package notificator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("user")
public class UserEntity implements Persistable<String> {
    @Id
    @Column("id")
    private String id;

    @Column("email")
    private String email;

    @Column("phone")
    private String phone;

    @Column("push_token")
    private String pushToken;

    @Transient
    private boolean isNew = false;
}
