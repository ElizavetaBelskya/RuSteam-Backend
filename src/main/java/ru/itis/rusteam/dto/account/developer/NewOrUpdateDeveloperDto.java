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
    @NotNull(message = "{accountId.null}")
    private Long accountId;

    @Schema(description = "Наименование", example = "Oracle")
    @NotNull(message = "{developer.name.null}")
    @Size(min = 2, max = 100, message = "{developer.name.size}")
    private String name;

    @Schema(description = "Описание", example = "Разрабатываем БД")
    @NotEmpty(message = "{developer.description.empty}")
    private String description;

}
