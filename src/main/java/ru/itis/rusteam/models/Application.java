package ru.itis.rusteam.models;

import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.application.ActionDates;
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.util.Set;

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
        DRAFT, ACTIVE, HIDDEN, DELETED
    }

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "text")
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Double rating;

    @ManyToOne(optional = false)
    private Developer developer;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @ManyToMany
    @JoinTable(name = "application_category",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories;

    @Embedded
    private ActionDates dates;
}
