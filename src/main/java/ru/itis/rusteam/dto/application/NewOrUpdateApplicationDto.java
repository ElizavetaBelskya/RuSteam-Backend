package ru.itis.rusteam.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новое приложение/Изменение приложения")
public class NewOrUpdateApplicationDto {

    @Schema(description = "Название приложения", example = "Atomic Heart")
    @Size(min = 2, max = 32, message = "{application.name.size}")
    private String name;

    @Schema(description = "Описание", example = "Игра")
    @NotEmpty(message = "{application.description.empty}")
    private String description;

    @Schema(description = "Идентификатор разработчика", example = "1642")
    private Long developerId;
}
