package ru.itis.rusteam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.Category;

import java.util.Optional;


public interface CategoryRepository extends JpaRepository<Category, Long>  {

    Optional<Category> findByName(String name);

}
