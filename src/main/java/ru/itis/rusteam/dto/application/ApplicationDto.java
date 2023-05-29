package ru.itis.rusteam.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.base.LongIdDto;
import ru.itis.rusteam.models.Application;

import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Schema(description = "Приложение")
public class ApplicationDto extends LongIdDto {


    @Schema(description = "Наименование", example = "Atomic Heart")
    private String name;

    @Schema(description = "Описание", example = "Игра")
    private String description;

    @Schema(description = "Стоимость", example = "1500 (цена в рублях)")
    private Double price;

    @Schema(description = "Идентификатор разработчика", example = "1642")
    private Long developerId;

    @Schema(description = "Даты публикации и последнего изменения приложения")
    private ActionDates dates;


    public static ApplicationDto from(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .price(application.getPrice())
                .dates(application.getDates())
                .developerId(application.getDeveloper().getAccount().getId())
                .build();
    }

    public static List<ApplicationDto> from(List<Application> applications) {
        return applications
                .stream()
                .map(ApplicationDto::from)
                .collect(Collectors.toList());
    }

}
