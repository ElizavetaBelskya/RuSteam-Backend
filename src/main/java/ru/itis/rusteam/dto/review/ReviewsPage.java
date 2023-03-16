package ru.itis.rusteam.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Страница с отзывами и общее количество страниц")
public class ReviewsPage {
    @Schema(description = "Cписок отзывов")
    private List<ReviewDto> reviews;
    @Schema(description = "общее количество страниц", example = "5")
    private Integer totalPagesCount;

}