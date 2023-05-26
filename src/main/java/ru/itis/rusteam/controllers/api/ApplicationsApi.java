package ru.itis.rusteam.controllers.api;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itis.rusteam.dto.application.ApplicationDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.exception.ExceptionDto;
import ru.itis.rusteam.dto.application.NewOrUpdateApplicationDto;
import ru.itis.rusteam.models.account.Developer;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Applications")
})
@RequestMapping("/applications")
public interface ApplicationsApi {

    @Operation(summary = "Получение списка приложений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с приложениями",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationsPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<ApplicationsPage> getAllApplications(
            @Parameter(description = "Номер страницы", example = "1") @RequestParam("page") int page);

    @Operation(summary = "Получение списка приложений разработчика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с приложениями разработчика",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationsPage.class))
                    })
    })
    @GetMapping
    ResponseEntity<ApplicationsPage> getAllApplicationsByDeveloper(
            @Parameter(description = "Разработчик", example = "Valve") @RequestParam("developer")Developer developer,
            @Parameter(description = "Номер страницы", example = "1") @RequestParam("page") int page);


    @Operation(summary = "Добавление приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленное приложение",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<ApplicationDto> addApplication(
            @Valid @RequestBody NewOrUpdateApplicationDto newApplication);


    @Operation(summary = "Получение приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация о приложении",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{application-id}")
    ResponseEntity<ApplicationDto> getApplication(
            @Parameter(description = "Идентификатор приложения", example = "1642")
            @PathVariable("application-id") Long applicationId);


    @Operation(summary = "Обновление приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Обновленное приложение",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{application-id}")
    ResponseEntity<ApplicationDto> updateApplication(
            @Parameter(description = "Идентификатор приложения", example = "1642") @PathVariable("application-id") Long applicationId,
            @Valid @RequestBody NewOrUpdateApplicationDto updatedApplication);


    @Operation(summary = "Удаление приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Приложение удалено"),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @DeleteMapping("/{application-id}")
    ResponseEntity<?> deleteApplication(
            @Parameter(description = "Идентификатор приложения", example = "1642") @PathVariable("application-id") Long applicationId);


    @Operation(summary = "Публикация приложения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Опубликованное приложение",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ApplicationDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @PutMapping("/{application-id}/publish")
    ResponseEntity<ApplicationDto> publishApplication(
            @Parameter(description = "Идентификатор приложения", example = "1642") @PathVariable("application-id") Long applicationId);


}
