package ru.itis.rusteam.models.deprecated;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;

/**
 * @author Elizaveta Belskaya
 */

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "users_depr")
public class UserDepr extends LongIdEntity {

    public enum State {
        ALIVE,
        BLOCKED,
        DELETED
    }

    private String nickname;
    private String email;
    private String status;
    @Enumerated(value = EnumType.STRING)
    private State state;

}
