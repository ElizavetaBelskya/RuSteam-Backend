package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;


@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AccountExtension  {

    @Id
    private Long id;

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private Account account;
}
