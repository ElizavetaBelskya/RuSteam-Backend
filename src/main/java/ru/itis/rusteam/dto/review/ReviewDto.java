package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.base.LongIdDto;
import ru.itis.rusteam.models.Review;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Отзыв")
public class ReviewDto extends LongIdDto {


    @Schema(description = "Идентификатор приложения", example = "1642")
    private Long applicationId;

    @Schema(description = "Текст", example = "Отличное приложение!")
    private String text;

    @Schema(description = "Дата публикации", example = "2000-01-01T00:00:00.0000000")
    private LocalDateTime publicationTime;

    @Schema(description = "Рейтинг приложения от 1 до 5", example = "4")
    private Integer rating;

    public static ReviewDto from(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .applicationId(review.getApplication().getId())
                .text(review.getText())
                .publicationTime(review.getPublicationTime())
                .rating(review.getRating())
                .build();
    }

    public static List<ReviewDto> from(List<Review> users) {
        return users.stream()
                .map(ReviewDto::from)
                .collect(Collectors.toList());
    }

}

