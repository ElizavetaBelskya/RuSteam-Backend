package ru.itis.rusteam.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rusteam.models.Category;

import javax.validation.constraints.*;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новое приложение/Изменение приложения")
public class NewOrUpdateApplicationDto {

    @Schema(description = "Название приложения", example = "Atomic Heart")
    @NotNull(message = "{dto.null}")
    @Size(min = 10, max = 200, message = "{application.name.size}")
    private String name;

    @Schema(description = "Описание", example = "Игра")
    @NotEmpty(message = "{dto.null}")
    private String description;

    @Schema(description = "Стоимость", example = "1500 (цена в рублях)")
    @NotEmpty(message = "{dto.null}")
    @Min(value = 0, message = "{application.price.min}")
    private Double price;

    @Schema(description = "Категории", example = "Шутер, Аркада")
    @NotNull(message = "{dto.null}")
    private List<Category> categories;

    @Schema(description = "Идентификатор разработчика", example = "1642")
    @NotNull(message = "{dto.null}")
    private Long developerId;
}
