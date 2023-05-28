package ru.itis.rusteam.dto.account.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import ru.itis.rusteam.dto.review.ReviewDto;
import ru.itis.rusteam.models.Review;
import ru.itis.rusteam.models.account.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Новый пользователь/изменение пользователя")
public class NewOrUpdateUserDto {

        @Schema(description = "Идентификатор аккаунта", example = "1642")
        @NotNull(message = "{dto.null}")
        private Long accountId;

        @Schema(description = "Имя", example = "Иван")
        @Size(max = 100, message = "{user.name.size}")
        private String name;

        @Schema(description = "Фамилия", example = "Иванов")
        @Size(max = 100, message = "{user.surname.size}")
        private String surname;

        @Schema(description = "Пол", example = "MALE")
        @Pattern(regexp = "^(MALE)|(FEMALE)$", message = "{user.gender.pattern}")
        private String gender;

        @Schema(description = "Дата рождения", example = "2000-01-01")
        private LocalDate birthdayDate;


}
