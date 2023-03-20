package ru.itis.rusteam.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotNull(message = "{account.email.null}")
    @Email(regexp = "^[\\w\\-\\.]+@[\\w-]+\\.[\\w-]{2,4}$", message = "{account.email.pattern}")
    private String email;

    @Schema(description = "Псевдоним", example = "PresidentCheese")
    @NotNull(message = "{account.nickname.null}")
    @Size(min = 5, max = 32, message = "{account.nickname.size}")
    //TODO - сделать регулярное выражение на псевдоним
    private String nickname;

    @Schema(description = "Пароль", example = "qwerty007")
    @NotNull(message = "{account.password.null}")
    @Size(min = 8, max = 100, message = "{account.password.size}")
    private String password;

}
