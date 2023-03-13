package ru.itis.rusteam.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый аккаунт/изменение аккаунта")
public class NewOrUpdateAccountDto {
    @Schema(description = "Электронная почта", example = "example@mail.ru")
    private String email;

    @Schema(description = "Псевдоним", example = "PresidentCheese")
    private String nickname;

    @Schema(description = "Пароль", example = "qwerty007")
    private String password;

}
