package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.deprecated.Application;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByStateOrderById(Pageable pageable, Application.State state);

}
