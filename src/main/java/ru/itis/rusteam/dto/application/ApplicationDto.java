package ru.itis.rusteam.dto.application;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.rusteam.dto.base.LongIdDto;
import ru.itis.rusteam.models.Application;
import ru.itis.rusteam.models.Category;

import javax.persistence.ElementCollection;
import java.util.List;
import java.util.Set;
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

    @Schema(description = "Рейтинг приложения", example = "4.79")
    private Double rating;

    @Schema(description = "Категории приложения", example = "Шутер, Аркада")
    private List<String> categories;

    @Schema(description = "Идентификатор разработчика", example = "1642")
    private Long developerId;

    @Schema(description = "Даты публикации и последнего изменения приложения")
    private ActionDates dates;

    @Schema(description = "Ссылка на Youtube-трейлер приложения")
    private String youtubeUrl;

    @Schema(description = "Ссылка на скачивание приложения для Android")
    private String androidDownloadLink;

    @Schema(description = "Ссылка на скачивание приложения для Windows")
    private String windowsDownloadLink;

    @Schema(description = "Ссылка иконку приложения")
    private String iconUrl;

    @Schema(description = "Ссылки на изображения для приложения")
    private Set<String> imagesUrl;


    public static ApplicationDto from(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .name(application.getName())
                .description(application.getDescription())
                .price(application.getPrice())
                .rating(application.getRating())
                .dates(application.getDates())
                .youtubeUrl(application.getYoutubeUrl())
                .iconUrl(application.getIconUrl())
                .imagesUrl(application.getImagesUrl())
                .categories(application.getCategories().stream().map(x -> x.toString()).collect(Collectors.toList()))
                .developerId(application.getDeveloper().getId())
                .build();
    }

    public static List<ApplicationDto> from(List<Application> applications) {
        return applications
                .stream()
                .map(ApplicationDto::from)
                .collect(Collectors.toList());
    }

}
