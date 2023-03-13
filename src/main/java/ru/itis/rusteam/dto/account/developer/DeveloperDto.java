package ru.itis.rusteam.dto.account.developer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.account.AccountDto;
import ru.itis.rusteam.models.account.Account;
import ru.itis.rusteam.models.account.Developer;


import java.util.List;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Разработчик")
public class DeveloperDto extends AccountDto {

    @Schema(description = "Наименование", example = "Oracle")
    private String name;
    @Schema(description = "Описание", example = "Разрабатываем БД")
    private String description;


    public static DeveloperDto from(Developer developer) {
        Account account = developer.getAccount();
        return DeveloperDto.builder()
                .id(account.getId())
                .email(account.getEmail())
                .nickname(account.getNickname())
                .name(developer.getName())
                .description(developer.getDescription())
                .build();
    }

    public static List<DeveloperDto> from2(List<Developer> developers) {
        return developers.stream()
                .map(DeveloperDto::from)
                .collect(Collectors.toList());
    }

}

