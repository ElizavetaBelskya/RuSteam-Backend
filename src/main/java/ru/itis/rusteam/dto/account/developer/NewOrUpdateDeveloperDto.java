package ru.itis.rusteam.dto.account.developer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый разработчик/Изменение разработчика")
public class NewOrUpdateDeveloperDto {

    @Schema(description = "Идентификатор аккаунта", example = "1642")
    private Long accountId;

    @Schema(description = "Наименование", example = "Oracle")
    private String name;
    @Schema(description = "Описание", example = "Разрабатываем БД")
    private String description;

}
