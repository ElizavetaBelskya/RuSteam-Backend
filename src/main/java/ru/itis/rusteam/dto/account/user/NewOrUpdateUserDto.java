package ru.itis.rusteam.dto.account.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.itis.rusteam.models.account.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый пользователь/изменение пользователя")
public class NewOrUpdateUserDto {


    @Schema(description = "Идентификатор аккаунта", example = "1642")
    @NotNull(message = "{accountId.null}")
    private Long accountId;

    @Schema(description = "Имя", example = "Иван")
    @Size(min = 1, max = 100, message = "{user.name.size}")
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    @Size(min = 1, max = 100, message = "{user.surname.size}")
    private String surname;

    @Schema(description = "Пол", example = "MALE")
    @Pattern(regexp = "^(MALE)|(FEMALE)$", message = "{user.gender.pattern}")
    private User.Gender gender;

    @Schema(description = "Дата рождения", example = "2000-01-01")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "{user.birthdayDate.pattern}")
    private LocalDate birthdayDate;

}
