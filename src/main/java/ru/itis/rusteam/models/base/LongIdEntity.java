package ru.itis.rusteam.models.base;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@SuperBuilder
@Data
@MappedSuperclass
@NoArgsConstructor
public class LongIdEntity {

    @Id
    @GeneratedValue(generator = "unique_id_in_db")
    private Long id;

}
