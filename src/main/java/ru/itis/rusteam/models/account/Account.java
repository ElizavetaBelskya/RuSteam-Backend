package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.io.Serializable;



@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "accounts")
public class Account extends LongIdEntity implements Serializable {

    public enum State {
        NOT_CONFIRMED, CONFIRMED, DELETED, BANNED
    }

    public enum Role {
        USER, MODERATOR, ADMIN, ANON
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

    public boolean isConfirmed() {
        return this.state.equals(State.CONFIRMED);
    }

    public boolean isBanned() {
        return this.state.equals(State.BANNED);
    }

}
