package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.ReviewsApi;
import ru.itis.rusteam.dto.*;
import ru.itis.rusteam.services.ReviewsService;

@RequiredArgsConstructor
@RestController
public class ReviewsController implements ReviewsApi {

    private final ReviewsService reviewsService;

    @Override
    public ResponseEntity<ReviewsPage> getAllReviews(int page) {
        return ResponseEntity
                .ok(reviewsService.getAllReviews(page));
    }

    @Override
    public ResponseEntity<ReviewDto> addReview(NewOrUpdateReviewDto newReview) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewsService.addReview(newReview));
    }

    @Override
    public ResponseEntity<ReviewDto> getReview(Long reviewId) {
        return ResponseEntity
                .ok(reviewsService.getReview(reviewId));
    }

    @Override
    public ResponseEntity<ReviewDto> updateReview(Long applicationId, NewOrUpdateReviewDto updatedReview) {
        return ResponseEntity.accepted()
                .body(reviewsService.updateReview(applicationId, updatedReview));
    }

    @Override
    public ResponseEntity<?> deleteReview(Long reviewId) {
        reviewsService.deleteReview(reviewId);
        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<ReviewDto> publishApplication(Long reviewId) {
        return ResponseEntity.accepted().body(reviewsService.publishReview(reviewId));
    }
}
