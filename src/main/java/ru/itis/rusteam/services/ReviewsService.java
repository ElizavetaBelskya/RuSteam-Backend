package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.ReviewDto;
import ru.itis.rusteam.dto.ReviewsPage;

public interface ReviewsService {
    ReviewsPage getAllReviews(int page);

    ReviewDto addReview(NewOrUpdateReviewDto newReview);

    ReviewDto getReview(Long reviewId);

    ReviewDto updateReview(Long reviewId, NewOrUpdateReviewDto updatedReview);

    void deleteReview(Long reviewId);

    ReviewDto publishReview(Long reviewId);
}
