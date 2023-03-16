package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "accounts")
public class Account extends LongIdEntity {

    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    }

    public enum Role {
        USER, MODERATOR, ADMIN
    }


    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String passwordHash;

    @Column(unique = true, nullable = false)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

}
