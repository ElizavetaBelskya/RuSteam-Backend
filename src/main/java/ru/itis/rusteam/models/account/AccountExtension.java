package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
@MappedSuperclass
public abstract class AccountExtension extends LongIdEntity {

    @OneToOne(optional = false)
    @PrimaryKeyJoinColumn
    private Account account;
}
