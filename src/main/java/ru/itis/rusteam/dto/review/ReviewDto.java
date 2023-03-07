package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rusteam.models.Review;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Review")
public class ReviewDto {
    @Schema(description = "Id of review", example = "1")
    private Long id;
    @Schema(description = "Id of application", example = "8")
    private Long applicationId;
    @Schema(description = "Text of review", example = "Good application!")
    private String reviewText;

    @Schema(description = "Rating of the application from 1 to 5", example = "3")
    private int rating;

    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .applicationId(review.getApplicationId())
                .reviewText(review.getReviewText())
                .rating(review.getRating())
                .build();
    }

    public static List<ReviewDto> from(List<Review> users) {
        return users.stream()
                .map(ReviewDto::from)
                .collect(Collectors.toList());
    }

}

