package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый отзыв/Изменение отзыва")
public class NewOrUpdateReviewDto {

    @Schema(description = "Идентификатор приложения", example = "1642")
    @NotNull(message = "{applicationId.null}")
    private Long applicationId;

    @Schema(description = "Текст отзыва", example = "Отличное приложение!")
    @NotEmpty(message = "{review.text.empty}")
    @Size(max = 1000, message = "{review.text.size}")
    private String text;

    @Schema(description = "Рейтинг приложения от 1 до 5", example = "4")
    @NotNull(message = "{review.rating.null}")
    private int rating;
}
