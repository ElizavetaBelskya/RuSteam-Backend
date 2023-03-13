package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.base.LongIdDto;
import ru.itis.rusteam.models.Review;

import java.sql.Date;
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

    @Schema(description = "Дата публикации", example = "01.01.2000")
    private Date publicationTime;

    @Schema(description = "Рейтинг приложения от 1 до 5", example = "4")
    private int rating;

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

