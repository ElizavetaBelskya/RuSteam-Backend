package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "users")
public class User extends AccountExtension {


    public enum Gender {
        MALE,
        FEMALE
    }

    private String name;
    private String surname;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;
    private LocalDate birthdayDate;

}
