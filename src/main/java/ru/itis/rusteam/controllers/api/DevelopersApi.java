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
import ru.itis.rusteam.dto.account.developer.DeveloperDto;
import ru.itis.rusteam.dto.account.developer.NewOrUpdateDeveloperDto;
import ru.itis.rusteam.dto.account.user.NewOrUpdateUserDto;
import ru.itis.rusteam.dto.account.user.UserDto;
import ru.itis.rusteam.dto.application.ApplicationsPage;
import ru.itis.rusteam.dto.exception.ExceptionDto;
import ru.itis.rusteam.models.account.Developer;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Applications")
})
@RequestMapping("/developers")
public interface DevelopersApi {
    @Operation(summary = "Получение списка приложений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Страница с приложениями",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ApplicationsPage.class))
                    })
    })
    @GetMapping("/{id}/applications")
    ResponseEntity<ApplicationsPage> getAllApplicationsByDeveloper(
            @Parameter(description = "Идентификатор разработчика", example = "42") @PathVariable("id") Long developerId,
            @Parameter(description = "Номер страницы", example = "1") @RequestParam("page") int page);


    @Operation(summary = "Добавление разработчика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Добавленный разработчик",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DeveloperDto.class))
                    }
            )
    })
    @PostMapping
    ResponseEntity<DeveloperDto> addDeveloper(
            @Valid @RequestBody NewOrUpdateDeveloperDto newDeveloper);

    @Operation(summary = "Получение разработчика")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация об аккаунте",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DeveloperDto.class))
                    }
            ),
            @ApiResponse(responseCode = "404", description = "Сведения об ошибке",
                    content = {
                            @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ExceptionDto.class))
                    }
            )
    })
    @GetMapping("/{account-id}")
    ResponseEntity<DeveloperDto> getDeveloperByAccountId(
            @Parameter(description = "Идентификатор аккаунта", example = "1642")
            @PathVariable("account-id") Long accountId);

}
