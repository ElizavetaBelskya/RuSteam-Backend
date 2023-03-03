package ru.itis.rusteam.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.rusteam.models.Application;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Приложение")
public class ApplicationDto {

    @Schema(description = "идентификатор приложения", example = "1")
    private Long id;

    @Schema(description = "название приложения", example = "Atomic Heart")
    private String name;

    @Schema(description = "идентификатор компании разработчика", example = "1")
    private Long companyId;


    public static ApplicationDto from(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .name(application.getName())
                .companyId(application.getCompanyId())
                .build();
    }

    public static List<ApplicationDto> from(List<Application> applications) {
        return applications
                .stream()
                .map(ApplicationDto::from)
                .collect(Collectors.toList());
    }

}
