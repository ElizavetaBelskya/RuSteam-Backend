package ru.itis.rusteam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.rusteam.models.deprecated.Review;

public interface ReviewsRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStateOrderById(Pageable pageable, Review.State state);
}
