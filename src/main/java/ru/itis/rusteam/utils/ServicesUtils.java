package ru.itis.rusteam.utils;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.exceptions.NotFoundException;

public class ServicesUtils {

    public static <T> T getOrThrow(Long id, JpaRepository<T, Long> rep, String entityName) {
        return rep.findById(id)
                .orElseThrow(() -> new NotFoundException(entityName + " with id <" + id + "> not found"));
    }


}