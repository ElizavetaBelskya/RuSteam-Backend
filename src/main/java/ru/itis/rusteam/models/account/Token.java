package ru.itis.rusteam.models.account;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "tokens", schema = "auth")
public class Token extends AccountExtension {
    @Column(unique = true)
    private String value;
}
