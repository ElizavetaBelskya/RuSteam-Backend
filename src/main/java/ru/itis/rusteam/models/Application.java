package ru.itis.rusteam.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.application.ActionDates;
import ru.itis.rusteam.models.account.Developer;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.util.List;
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

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Developer developer;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private State state;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "application_category",
            joinColumns = @JoinColumn(name = "application_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<Category> categories;

    @Embedded
    private ActionDates dates;

    @Column(nullable = true)
    private String youtubeUrl;

    @Column(nullable = true)
    private String androidDownloadLink;

    @Column(nullable = true)
    private String windowsDownloadLink;

    @Column(nullable = true)
    private String iconUrl;

    @ElementCollection
    private Set<String> imagesUrl;

}
