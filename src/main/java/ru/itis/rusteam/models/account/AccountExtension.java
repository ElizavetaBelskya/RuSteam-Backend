package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AccountExtension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    //TODO: тут бы объединить столбцы
    private Account account;
}
