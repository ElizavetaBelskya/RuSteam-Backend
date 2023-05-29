package ru.itis.rusteam.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.itis.rusteam.dto.review.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.dto.review.ReviewsPage;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.Review;
import ru.itis.rusteam.repositories.ApplicationsRepository;
import ru.itis.rusteam.repositories.ReviewsRepository;
import ru.itis.rusteam.services.ReviewsService;


import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static ru.itis.rusteam.dto.review.ReviewDto.from;
import static ru.itis.rusteam.utils.ServicesUtils.getOrThrow;

@RequiredArgsConstructor
@Service
public class ReviewsServiceImpl implements ReviewsService {

    private final ReviewsRepository reviewsRepository;
    private final ApplicationsRepository applicationsRepository;

    @Value("${default.page-size}")
    private int defaultPageSize;


    @Override
    public ReviewsPage getAllReviewsForApplication(int page, Application application) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Review> reviewsPage = reviewsRepository.findAllByStateAndApplicationOrderById(pageRequest, Review.State.ACTIVE, application);

        return ReviewsPage.builder()
                .reviews(ReviewDto.from(reviewsPage.getContent()))
                .totalPagesCount(reviewsPage.getTotalPages())
                .build();
    }

    private Long getApplicationReviewsCount(Application application) {
        return reviewsRepository.countByApplication(application);
    }

    @Override
    @Transactional
    public ReviewDto addReview(NewOrUpdateReviewDto review) {
        Review reviewToSave = Review.builder()
                .application(getApplicationOrThrow(review.getApplicationId()))
                .text(review.getText())
                .publicationTime(LocalDateTime.now())
                .rating(review.getRating())
                .state(Review.State.DRAFT)
                .build();

        //TODO - сделать проверку корректности данных
        reviewsRepository.save(reviewToSave);
        Application application = reviewToSave.getApplication();
        long reviewsCount = getApplicationReviewsCount(application);
        double newRating = (reviewsCount * application.getRating() + review.getRating()) / (reviewsCount + 1);
        application.setRating(newRating);
        applicationsRepository.save(application);

        return from(reviewToSave);
    }

    @Override
    public ReviewDto getReviewById(Long applicationId) {
        return from(getReviewOrThrow(applicationId));
    }

    @Override
    @Transactional
    public ReviewDto updateReview(Long id, NewOrUpdateReviewDto updatedReview) {
        Review reviewForUpdate = getReviewOrThrow(id);
        Application application = reviewForUpdate.getApplication();

        long reviewsCount = getApplicationReviewsCount(application);
        double newRating = (reviewsCount * application.getRating() - reviewForUpdate.getRating() + updatedReview.getRating()) / reviewsCount;

        reviewForUpdate.setText(updatedReview.getText());
        //TODO - подумать над датой изменения
        reviewForUpdate.setRating(updatedReview.getRating());

        //TODO - сделать проверку корректности данных
        reviewsRepository.save(reviewForUpdate);

        application.setRating(newRating);
        applicationsRepository.save(application);

        return ReviewDto.from(reviewForUpdate);
    }

    @Override
    @Transactional
    public void deleteReview(Long id) {
        Review reviewForDelete = getReviewOrThrow(id);

        reviewForDelete.setState(Review.State.DELETED);

        reviewsRepository.save(reviewForDelete);

        Application application = reviewForDelete.getApplication();
        long reviewsCount = getApplicationReviewsCount(application);
        double newRating = (reviewsCount * application.getRating() - reviewForDelete.getRating()) / (reviewsCount - 1);
        application.setRating(newRating);
        applicationsRepository.save(application);
    }

    @Override
    public ReviewDto publishReview(Long id) {
        Review reviewToPublish = getReviewOrThrow(id);

        reviewToPublish.setState(Review.State.ACTIVE);

        reviewsRepository.save(reviewToPublish);

        return ReviewDto.from(reviewToPublish);
    }

    @Override
    public ReviewDto updateReviewStatus(Long id, String status) {
        Review review = getReviewOrThrow(id);
        review.setState(Review.State.valueOf(status));
        reviewsRepository.save(review);
        return ReviewDto.from(review);
    }


    private Review getReviewOrThrow(Long id) {
        return getOrThrow(id, reviewsRepository, "Review");
    }

    private Application getApplicationOrThrow(Long id) {
        return getOrThrow(id, applicationsRepository, "Application");
    }

}

