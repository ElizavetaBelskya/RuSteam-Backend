package ru.itis.rusteam.dto.application;

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
@Schema(description = "Страница с приложениями и общее количество страниц")
public class ApplicationsPage {

    @Schema(description = "Список приложений")
    private List<ApplicationDto> applications;

    @Schema(description = "Общее количество страниц", example = "5")
    private Integer totalPagesCount;
}
