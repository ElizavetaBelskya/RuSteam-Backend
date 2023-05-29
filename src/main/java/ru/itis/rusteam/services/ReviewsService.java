package ru.itis.rusteam.services;

import ru.itis.rusteam.dto.review.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.dto.review.ReviewsPage;
import ru.itis.rusteam.models.Application;

public interface ReviewsService {
    ReviewsPage getAllReviewsForApplication(int page, Application application);

    ReviewDto getReviewById(Long id);

    ReviewDto addReview(NewOrUpdateReviewDto review);

    ReviewDto updateReview(Long id, NewOrUpdateReviewDto updatedReview);

    void deleteReview(Long id);

    ReviewDto publishReview(Long id);

    ReviewDto updateReviewStatus(Long id, String status);
}
