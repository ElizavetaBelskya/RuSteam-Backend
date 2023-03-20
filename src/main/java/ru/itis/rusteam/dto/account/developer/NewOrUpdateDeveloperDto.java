package ru.itis.rusteam.dto.account.developer;

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
@Schema(description = "Новый разработчик/Изменение разработчика")
public class NewOrUpdateDeveloperDto {

    @Schema(description = "Идентификатор аккаунта", example = "1642")
    @NotNull(message = "{dto.null}")
    private Long accountId;

    @Schema(description = "Наименование", example = "Oracle")
    @NotNull(message = "{dto.null}")
    @Size(min = 1, max = 100, message = "{developer.name.size}")
    private String name;

    @Schema(description = "Описание", example = "Разрабатываем БД")
    @NotEmpty(message = "{dto.null}")
    private String description;

}
