package ru.itis.rusteam.models.deprecated;

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
@Table(name = "reviews_depr")
public class Review extends LongIdEntity {
    public enum State {
        ACTIVE,
        DRAFT,
        DELETED
    }

    private Long applicationId;
    private String reviewText;
    private int rating;
    @Enumerated(value = EnumType.STRING)
    private State state;
}
