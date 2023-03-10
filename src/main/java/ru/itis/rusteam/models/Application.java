package ru.itis.rusteam.models;

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
@Table(name = "applications")
public class Application extends LongIdEntity {

    public enum State {
        ACTIVE,
        DRAFT,
        DELETED
    }

    private String name;

    private Long companyId;


    @Enumerated(value = EnumType.STRING)
    private State state;
}
