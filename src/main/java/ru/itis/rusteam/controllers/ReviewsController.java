package ru.itis.rusteam.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.rusteam.controllers.api.ReviewsApi;
import ru.itis.rusteam.dto.review.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.dto.review.ReviewsPage;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.repositories.ApplicationsRepository;
import ru.itis.rusteam.services.ReviewsService;

import java.util.Optional;


@RequiredArgsConstructor
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ReviewsController implements ReviewsApi {

    private final ReviewsService reviewsService;

    private final ApplicationsRepository applicationsRepository;

    @Override
    public ResponseEntity<ReviewsPage> getAllReviews(int page, Long applicationId) {
        // TODO: сделать тут нормально через сервисы или еще что-то поправить
        Optional<Application> application = applicationsRepository.findById(applicationId);
        return ResponseEntity
                .ok(reviewsService.getAllReviewsForApplication(page, application.get()));

    }

    @Override
    public ResponseEntity<ReviewDto> addReview(NewOrUpdateReviewDto newReview) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reviewsService.addReview(newReview));
    }

    @Override
    public ResponseEntity<ReviewDto> getReview(Long reviewId) {
        return ResponseEntity
                .ok(reviewsService.getReviewById(reviewId));
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

    @Override
    public ResponseEntity<ReviewDto> updateReviewStatus(Long reviewId, String status) {
        return ResponseEntity.accepted().body(reviewsService.updateReviewStatus(reviewId, status));
    }
}
