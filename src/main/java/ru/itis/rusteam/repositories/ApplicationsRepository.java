package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.account.Developer;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByStateOrderById(Pageable pageable, Application.State state);

    Page<Application> findAllByDeveloper(Pageable pageable,Developer developer);

}
