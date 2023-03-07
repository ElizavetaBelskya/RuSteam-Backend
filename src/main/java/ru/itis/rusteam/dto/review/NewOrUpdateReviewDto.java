package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый отзыв/Изменение отзыва")
public class NewOrUpdateReviewDto {

    @Schema(description = "Текст ревью", example = "The best game ever!")
    private String reviewText;

    @Schema(description = "Идентификатор приложения к которому был написан ревью", example = "8")
    private Long applicationId;

    @Schema(description = "Рейтинг приложения", example = "4")
    private int rating;
}
