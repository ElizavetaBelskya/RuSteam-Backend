package ru.itis.rusteam.dto.account.developer;

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
@Schema(description = "Новый разработчик/Изменение разработчика")
public class NewOrUpdateDeveloperDto {

    @Schema(description = "Идентификатор аккаунта", example = "1642")
    private Long accountId;

    @Schema(description = "Наименование", example = "Oracle")
    @Size(min = 2, max = 32, message = "{developer.name.size}")
    private String name;

    @Schema(description = "Описание", example = "Разрабатываем БД")
    @NotEmpty(message = "{developer.description.empty}")
    private String description;

}
