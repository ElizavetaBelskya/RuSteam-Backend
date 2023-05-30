package ru.itis.rusteam.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Application> applications;
}
