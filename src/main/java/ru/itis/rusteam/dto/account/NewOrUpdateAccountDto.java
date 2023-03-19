package ru.itis.rusteam.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый аккаунт/изменение аккаунта")
public class NewOrUpdateAccountDto {
    @Schema(description = "Электронная почта", example = "example@mail.ru")
    @Email(regexp = "^[\\w\\-\\.]+@[\\w-]+\\.[\\w-]{2,4}$", message = "{account.email.pattern}")
    private String email;

    @Schema(description = "Псевдоним", example = "PresidentCheese")
    @Size(min = 2, max = 32, message = "{account.nickname.size}")
    private String nickname;

    @Schema(description = "Пароль", example = "qwerty007")
    @Size(min = 8, message = "{account.password.size")
    private String password;

}
