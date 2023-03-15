package ru.itis.rusteam.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.sql.Date;

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
        DRAFT, ACTIVE, HIDDEN, DELETED
    }

    @ManyToOne(optional = false)
    private Application application;


    @Column(nullable = false,
            columnDefinition = "text")
    private String text;
    @Column(nullable = false)
    private Date publicationTime;
    @Column(nullable = false,
            columnDefinition = "int check (rating between 1 and 5)")
    private int rating;
    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;
}
