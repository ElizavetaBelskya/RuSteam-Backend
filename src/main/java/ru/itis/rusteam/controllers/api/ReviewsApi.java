package ru.itis.rusteam.controllers.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rusteam.dto.exception.ExceptionDto;
import ru.itis.rusteam.dto.review.NewOrUpdateReviewDto;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.dto.review.ReviewsPage;

@Tags(value = {
        @Tag(name = "Reviews")
})
@RequestMapping("/reviews")
public interface ReviewsApi {

    @Operation(summary = "Получение списка отзывов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с отзывами",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ReviewsPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<ReviewsPage> getAllReviews(
            @Parameter(description = "Номер страницы") @RequestParam("page") int page);


    @Operation(summary = "Добавление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный отзыв",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<ReviewDto> addReview(
            @RequestBody NewOrUpdateReviewDto newReview);


    @Operation(summary = "Получение отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об отзыве",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{review-id}")
    ResponseEntity<ReviewDto> getReview(
            @Parameter(description = "Идентификатор отзыва", example = "1")
            @PathVariable("review-id") Long reviewId);


    @Operation(summary = "Обновление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленный отзыв",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{review-id}")
    ResponseEntity<ReviewDto> updateReview(
            @Parameter(description = "Идентификатор отзыва", example = "1") @PathVariable("review-id") Long reviewId,
            @RequestBody NewOrUpdateReviewDto updatedReview);


    @Operation(summary = "Удаление отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Отзыв удален"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{review-id}")
    ResponseEntity<?> deleteReview(
            @Parameter(description = "Идентификатор отзыва", example = "1") @PathVariable("review-id") Long reviewId);


    @Operation(summary = "Публикация отзыва")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Опубликованный отзыв",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ReviewDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{review-id}/publish")
    ResponseEntity<ReviewDto> publishApplication(
            @Parameter(description = "Идентификатор отзыва", example = "1") @PathVariable("review-id") Long reviewId);


}