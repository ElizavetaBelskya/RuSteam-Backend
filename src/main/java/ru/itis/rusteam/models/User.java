package ru.itis.rusteam.models;

import lombok.*;

import javax.persistence.*;

/**
 * @author Elizaveta Belskaya
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    public enum State {
        ALIVE,
        BLOCKED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nickname;
    private String email;
    private String status;
    @Enumerated(value = EnumType.STRING)
    private State state;

}
