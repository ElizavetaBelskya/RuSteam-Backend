package ru.itis.rusteam.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rusteam.dto.application.ActionDates;

import javax.persistence.Embedded;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый аккаунт/изменение аккаунта")
public class NewOrUpdateAccountDto {
    @Schema(description = "Электронная почта", example = "example@mail.ru")
    @NotNull(message = "{dto.null}")
    @Email(regexp = "^[\\w\\-\\.]+@[\\w-]+\\.[\\w-]{2,4}$", message = "{account.email.pattern}")
    private String email;

    @Schema(description = "Псевдоним", example = "PresidentCheese")
    @NotNull(message = "{dto.null}")
    @Size(min = 5, max = 32, message = "{account.nickname.size}")
    private String nickname;

    @Schema(description = "Пароль", example = "qwerty007")
    @NotNull(message = "{dto.null}")
    @Size(min = 8, max = 40, message = "{account.password.size}")
    private String password;

    @Schema(description = "Даты публикации и последнего обновления приложения")
    @Embedded
    private ActionDates dates;
}
