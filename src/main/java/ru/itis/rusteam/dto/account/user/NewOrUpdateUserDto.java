package ru.itis.rusteam.dto.account.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.itis.rusteam.models.account.User;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый пользователь/изменение пользователя")
public class NewOrUpdateUserDto {


    @Schema(description = "Идентификатор аккаунта", example = "1642")
    private Long accountId;

    @Schema(description = "Имя", example = "Иван")
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    private String surname;

    @Schema(description = "Пол", example = "Мужской, Женский")
    private User.Gender gender;

    @Schema(description = "Дата рождения", example = "01.01.2000")
    private Date birthdayDate;

}
