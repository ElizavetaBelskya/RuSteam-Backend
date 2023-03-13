package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.review.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.dto.review.ReviewsPage;
import ru.itis.rusteam.exceptions.NotFoundException;
import ru.itis.rusteam.models.Review;
import ru.itis.rusteam.repositories.ReviewsRepository;
import ru.itis.rusteam.services.ReviewsService;

@RequiredArgsConstructor
@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;

    @Value("${default.page-size}")
    private int defaultPageSize;


    @Override
    public ReviewsPage getAllReviews(int page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Review> reviewsPage = reviewsRepository.findAllByStateOrderById(pageRequest, Review.State.ACTIVE);

        return ReviewsPage.builder()
                .reviews(ReviewDto.from(reviewsPage.getContent()))
                .totalPagesCount(reviewsPage.getTotalPages())
                .build();
    }

    @Override
    public ReviewDto addReview(NewOrUpdateReviewDto newReview) {
        Review review = Review.builder()
                .text(newReview.getReviewText())
                .applicationId(newReview.getApplicationId())
                .build();

        reviewsRepository.save(review);

        return ReviewDto.from(review);
    }

    @Override
    public ReviewDto getReviewById(Long applicationId) {
        Review review = getReviewOrThrow(applicationId);
        return ReviewDto.from(review);
    }

    @Override
    public ReviewDto updateReview(Long reviewId, NewOrUpdateReviewDto updatedReview) {
        Review reviewForUpdate = getReviewOrThrow(reviewId);

        reviewForUpdate.setText(updatedReview.getReviewText());
        reviewForUpdate.setRating(updatedReview.getRating());

        reviewsRepository.save(reviewForUpdate);
        return ReviewDto.from(reviewForUpdate);
    }

    @Override
    public void deleteReview(Long reviewId) {
        Review reviewForDelete = getReviewOrThrow(reviewId);

        reviewForDelete.setState(Review.State.DELETED);

        reviewsRepository.save(reviewForDelete);
    }

    @Override
    public ReviewDto publishReview(Long reviewId) {
        Review reviewToPublish = getReviewOrThrow(reviewId);

        reviewToPublish.setState(Review.State.ACTIVE);

        reviewsRepository.save(reviewToPublish);

        return ReviewDto.from(reviewToPublish);
    }


    private Review getReviewOrThrow(Long reviewId) {
        return reviewsRepository.findById(reviewId)
                .orElseThrow(() -> new NotFoundException("Отзыв с идентификатором <" + reviewId + "> не найдено"));
    }
}

