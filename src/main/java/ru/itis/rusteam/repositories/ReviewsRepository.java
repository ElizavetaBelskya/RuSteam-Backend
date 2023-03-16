package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.Review;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStateAndApplicationOrderById(Pageable pageable, Review.State state, Application application);
}
