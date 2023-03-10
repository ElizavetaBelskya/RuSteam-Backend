package ru.itis.rusteam.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.sql.Date;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "users")
public class User extends LongIdEntity {

    public enum Role {
        USER, MODERATOR, ADMIN
    }

    public enum Gender {
        MALE, FEMALE
    }

    @OneToOne(optional = false)
    private Account account;

    private String name;
    private String surname;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private Date birthdayDate;

    @Enumerated(value = EnumType.STRING)
    private Role role;

}
