package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.account.Developer;

import java.util.List;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByStateOrderById(Pageable pageable, Application.State state);

    Page<Application> findAllByDeveloper(Pageable pageable,Developer developer);

    @Query(nativeQuery = true, value =
            "SELECT * FROM applications WHERE applications.name ILIKE :content OR applications.description ILIKE :content")
    List<Application> findAllByContent(@Param("content") String content);


}
