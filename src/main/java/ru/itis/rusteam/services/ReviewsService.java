package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.review.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.dto.review.ReviewsPage;

public interface ReviewsService {
    ReviewsPage getAllReviews(int page);

    ReviewDto getReviewById(Long id);

    ReviewDto addReview(NewOrUpdateReviewDto review);

    ReviewDto updateReview(Long id, NewOrUpdateReviewDto updatedReview);

    void deleteReview(Long id);

    ReviewDto publishReview(Long id);
}
