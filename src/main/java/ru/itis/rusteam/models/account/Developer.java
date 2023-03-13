package ru.itis.rusteam.models.account;


import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "developers")
public class Developer extends AccountExtension {

    private String name;
    @Column(columnDefinition = "text")
    private String description;
}
