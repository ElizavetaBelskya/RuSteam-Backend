package ru.itis.rusteam.dto.account.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.User;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDate;



@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Пользователь")
public class UserDto extends AccountDto {


    @Schema(description = "Имя", example = "Иван")
    @Size(min = 2, max = 32, message = "{user.name.size}")
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    @Size(min = 2, max = 32, message = "{user.surname.size}")
    private String surname;

    @Schema(description = "Пол", example = "MALE")
    private User.Gender gender;

    @Schema(description = "Дата рождения", example = "2000-01-01")
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}|\\d{2}-\\d{2}-\\d{4}$", message = "{user.birthdayDate.pattern}")
    private LocalDate birthdayDate;


    public static UserDto from(User user) {
        Account account = user.getAccount();
        return UserDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .name(user.getName())
                .surname(user.getSurname())
                .gender(user.getGender())
                .birthdayDate(user.getBirthdayDate())
                .build();
    }

//    public static List<UserDto> from2(List<User> users) {
//        return users.stream()
//                .map(UserDto::from)
//                .collect(Collectors.toList());
//    }

}

