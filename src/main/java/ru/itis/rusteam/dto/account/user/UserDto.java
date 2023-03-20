package ru.itis.rusteam.dto.account.user;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.User;

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
    private String name;

    @Schema(description = "Фамилия", example = "Иванов")
    private String surname;

    @Schema(description = "Пол", example = "MALE")
    private User.Gender gender;

    @Schema(description = "Дата рождения", example = "2000-01-01")
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

