package ru.itis.rusteam.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Review {
    public enum State {
        ACTIVE,
        DRAFT,
        DELETED
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long applicationId;
    private String reviewText;
    private int rating;
    @Enumerated(value = EnumType.STRING)
    private State state;
}
