package ru.itis.rusteam.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import ru.itis.rusteam.models.base.LongIdEntity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "categories")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Category extends LongIdEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer"})
    private List<Application> applications;

    @Override
    public String toString() {
        return name;
    }

}
