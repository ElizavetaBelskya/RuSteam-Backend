package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.account.Developer;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationsRepository extends JpaRepository<Application, Long> {

    Page<Application> findAllByStateOrderById(Pageable pageable, Application.State state);

    Page<Application> findAllByDeveloper(Pageable pageable,Developer developer);

    Page<Application> findAllByPrice(Pageable pageable,Long price);

    @Query("from Application a where a.dates.publishDate >= :withinMonth and a.rating>= :rating and a.price = 0.0")
    Page<Application> findAllByPublishDateAndRatingAndFree(Pageable pageable,@Param("withinMonth") LocalDateTime time,@Param("rating") Double rating);

    @Query("from Application a where a.dates.publishDate >= :withinMonth and a.rating>= :rating")
    Page<Application> findAllByPublishDateAndRating(Pageable pageable,@Param("withinMonth") LocalDateTime time,@Param("rating") Double rating);

    @Query(nativeQuery = true, value =
            "SELECT * FROM applications WHERE applications.name ILIKE :content OR applications.description ILIKE :content")
    List<Application> findAllByContent(@Param("content") String content);


}
