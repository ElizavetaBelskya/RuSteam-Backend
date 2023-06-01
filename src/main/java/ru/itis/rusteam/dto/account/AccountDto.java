package ru.itis.rusteam.dto.account;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.account.user.UserDto;
import ru.itis.rusteam.dto.base.LongIdDto;
import ru.itis.rusteam.models.account.Account;

import java.util.List;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Аккаунт")
public class AccountDto extends LongIdDto {

    @Schema(description = "Электронная почта", example = "example@mail.ru")
    private String email;

    @Schema(description = "Псевдоним", example = "PresidentCheese")
    private String nickname;

    @Schema(description = "Роль", example = "USER")
    private String role;


    public static AccountDto from(Account account) {
        return AccountDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .role(account.getRole().name())
                .build();
    }

    public static List<AccountDto> from(List<Account> accounts) {
        return accounts.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }

}
