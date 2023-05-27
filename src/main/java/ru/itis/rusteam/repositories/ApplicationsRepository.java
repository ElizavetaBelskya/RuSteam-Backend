package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.account.Developer;

import java.util.List;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByStateOrderById(Pageable pageable, Application.State state);

    Page<Application> findAllByDeveloper(Pageable pageable,Developer developer);

    @Query("SELECT a FROM Application a WHERE a.name LIKE %:content% OR a.description LIKE %:content%")
    List<Application> findAllByContent(String content);


}
