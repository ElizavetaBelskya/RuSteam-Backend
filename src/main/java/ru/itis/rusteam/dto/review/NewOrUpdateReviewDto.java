package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый отзыв/Изменение отзыва")
public class NewOrUpdateReviewDto {

    @Schema(description = "Идентификатор приложения", example = "1642")
    private Long applicationId;

    @Schema(description = "Текст ревью", example = "Отличное приложение!")
    @NotEmpty(message = "{review.text.empty}")
    private String text;

    @Schema(description = "Рейтинг приложения от 1 до 5", example = "4")
    private int rating;
}
