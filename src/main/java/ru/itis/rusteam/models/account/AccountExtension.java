package ru.itis.rusteam.models.account;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;

@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public abstract class AccountExtension {

    @Id
    @OneToOne(optional = false)
    private Account account;
}
