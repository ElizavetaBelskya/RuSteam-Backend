package ru.itis.rusteam.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.account.User;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data

@Entity
@Table(name = "reviews")
public class Review extends LongIdEntity {
    public enum State {
        DRAFT, ACTIVE, DELETED
    }

    @ManyToOne(optional = false)
    private Application application;

    @ManyToOne
    private User user;


    @Column(columnDefinition = "text", nullable = true)
    private String text;

    @Column(nullable = true)
    private LocalDateTime publicationTime;

    @Column(columnDefinition = "int check (rating between 1 and 5)", nullable = true)
    private Integer rating;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;
}
