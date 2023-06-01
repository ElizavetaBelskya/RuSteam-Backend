package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.Review;
import ru.itis.rusteam.models.account.User;

import java.util.Optional;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStateAndApplicationOrderById(Pageable pageable, Review.State state, Application application);

    Long countByApplication(Application application);
    Optional<Review> findByUserAndApplication(User user, Application application);
}
